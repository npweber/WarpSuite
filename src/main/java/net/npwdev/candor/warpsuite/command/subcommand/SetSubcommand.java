package net.npwdev.candor.warpsuite.command.subcommand;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSubcommand {

    public static void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage("Usage: /warpsuite set <warpName> [world] [x] [y] [z]");
            return;
        }
        else if (args.length == 2) {
            String warpName = args[1];
            Player player = (Player) sender;
            World world = player.getWorld();
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            //plugin.getWarpManager().setWarp(warpName, worldName, x, y, z);
            return;
        }   
        else if (args.length > 2) {
            String warpName = args[1];
            String worldName = args[2];
            double x = Double.parseDouble(args[3]);
            double y = Double.parseDouble(args[4]);
            double z = Double.parseDouble(args[5]);

            //plugin.getWarpManager().setWarp(warpName, worldName, x, y, z);
        }
    }
}