package net.npwdev.candor.warpsuite.command.subcommand;

import org.bukkit.command.CommandSender;

public class WarpSubcommand {

    public static void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("Usage: /warpsuite warp <warpName>");
            return;
        }
        
        String warpName = args[1];
        /*
        Warp warp = plugin.getWarpManager().getWarp(warpName);
        if (warp == null) {
            sender.sendMessage("Warp not found: " + warpName);
        }

        Player player = (Player) sender;
        player.teleport(warp.getLocation());
        */
        sender.sendMessage("Teleported to warp: " + warpName);
    }
}