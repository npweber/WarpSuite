package net.npwdev.candor.warpsuite.command.subcommand;

import org.bukkit.command.CommandSender;

public class DelSubcommand {
    public static void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("Usage: /warpsuite del <warpName>");
            return;
        }   
        
        String warpName = args[1];
        //plugin.getWarpManager().deleteWarp(warpName);
        sender.sendMessage("Warp deleted: " + warpName);
    }
}