package net.npwdev.candor.warpsuite.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.npwdev.candor.warpsuite.WarpSuite;

// DelSubcommand: Handles the /warpsuite del sub-command

public class DelSubcommand {
    public static void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(WarpSuite.MSG_PREFIX + "Usage: /warpsuite del <warpName>");
            return;
        }   
        
        // Get the warp name
        String warpName = args[1];

        // Delete the warp
        WarpSuite.getInstance().getWarpManager().deleteWarp(warpName, (Player) sender);
    }
}