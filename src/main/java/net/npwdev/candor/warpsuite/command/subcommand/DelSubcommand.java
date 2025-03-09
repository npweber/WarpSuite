package net.npwdev.candor.warpsuite.command.subcommand;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.npwdev.candor.warpsuite.WarpSuite;

public class DelSubcommand {
    public static void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("Usage: /warpsuite del <warpName>");
            return;
        }   
        
        String warpName = args[1];
        WarpSuite.getInstance().getWarpManager().deleteWarp(warpName, (Player) sender);
    }
}