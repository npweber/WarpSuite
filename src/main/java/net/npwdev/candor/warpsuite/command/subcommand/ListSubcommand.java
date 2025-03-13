package net.npwdev.candor.warpsuite.command.subcommand;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.npwdev.candor.warpsuite.WarpSuite;
import net.npwdev.candor.warpsuite.warps.Warp;

// ListSubcommand: Handles the /warpsuite list sub-command

public class ListSubcommand {
    
    public static void execute(CommandSender sender, String[] args) {
        String playerName = ((Player)sender).getPlayerListName();

        // If the player has no warps, send a message and return
        if (WarpSuite.getInstance().getWarpManager().getPlayerWarpCount(playerName) == 0) {
            sender.sendMessage(WarpSuite.MSG_PREFIX + "You have no warps to teleport to.");
            return;
        }

        // Get the player's warps
        List<Warp> warps = WarpSuite.getInstance().getWarpManager().getPlayerWarps(playerName);

        // Send a message to the player listing all of their warps
        sender.sendMessage(WarpSuite.MSG_PREFIX + "Warps: " + String.join(", ", warps.stream().map(Warp::getName).collect(Collectors.toList())));
    }
}
