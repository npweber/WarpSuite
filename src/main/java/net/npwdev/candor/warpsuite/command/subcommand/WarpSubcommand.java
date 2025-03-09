package net.npwdev.candor.warpsuite.command.subcommand;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.npwdev.candor.warpsuite.WarpSuite;
import net.npwdev.candor.warpsuite.warps.Warp;

public class WarpSubcommand {

    private static final WarpSuite plugin = WarpSuite.getInstance();

    public static void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (plugin.getCooldownManager().playerIsOnCooldown(player.getPlayerListName())) {
            sender.sendMessage("You are on cooldown. Please wait " + plugin.getWarpCooldown() + " seconds before warping again.");
            return;
        }

        List<Warp> playerWarps;

        if(plugin.getWarpManager().getPlayerWarpCount(player.getPlayerListName()) == 0) {
            sender.sendMessage("You have no warps to teleport to.");
            return;
        } else {
            playerWarps = plugin.getWarpManager().getPlayerWarps(player.getPlayerListName());
        }

        if (args.length == 1) {
            sender.sendMessage("Usage: /warpsuite warp <warpName>");
            return;
        }
        
        String warpName = args[1];
        Warp warp = playerWarps.stream().filter(w -> w.getName().equals(warpName)).findFirst().orElse(null);

        if (warp == null) {
            sender.sendMessage("Warp not found: " + warpName);
            return;
        }

        player.teleport(warp.getLocation());
        plugin.getCooldownManager().addPlayerCooldown(player.getPlayerListName());
        sender.sendMessage("Teleported to warp: " + warpName);
    }
}