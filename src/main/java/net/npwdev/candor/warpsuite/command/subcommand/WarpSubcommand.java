package net.npwdev.candor.warpsuite.command.subcommand;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.npwdev.candor.warpsuite.WarpSuite;
import net.npwdev.candor.warpsuite.warps.Warp;

// WarpSubcommand: Handles the /warpsuite warp sub-command
public class WarpSubcommand {

    private static final WarpSuite plugin = WarpSuite.getInstance();

    public static void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // If the player is on cooldown, send a message and return
        if (plugin.getCooldownManager().playerIsOnCooldown(player.getPlayerListName())) {
            sender.sendMessage("You are on cooldown. Please wait " + plugin.getWarpCooldown() + " seconds before warping again.");
            return;
        }

        List<Warp> playerWarps;

        // If the player has no warps, send a message and return
        if(plugin.getWarpManager().getPlayerWarpCount(player.getPlayerListName()) == 0) {
            sender.sendMessage("You have no warps to teleport to.");
            return;
        // Otherwise, Get the player's warps
        } else {
            playerWarps = plugin.getWarpManager().getPlayerWarps(player.getPlayerListName());
        }

        // If the player doesn't provide a warp name, send a message and return
        if (args.length == 1) {
            sender.sendMessage("Usage: /warpsuite warp <warpName>");
            return;
        }
        
        // Get the warp name
        String warpName = args[1];

        // Get the warp
        Warp warp = playerWarps.stream().filter(w -> w.getName().equals(warpName)).findFirst().orElse(null);

        // If the warp doesn't exist, send a message and return
        if (warp == null) {
            sender.sendMessage("Warp not found: " + warpName);
            return;
        }

        // Teleport the player to the warp
        player.teleport(warp.getLocation());

        // Add a cooldown to the player
        plugin.getCooldownManager().addPlayerCooldown(player.getPlayerListName());

        // Send a message to the player stating that they have been teleported to the warp
        sender.sendMessage("Teleported to warp: " + warpName);
    }
}