package net.npwdev.candor.warpsuite.command.subcommand;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.npwdev.candor.warpsuite.WarpSuite;
import net.npwdev.candor.warpsuite.warps.Warp;

public class SetSubcommand {

    private static final WarpSuite plugin = WarpSuite.getInstance();

    public static void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(plugin.getWarpManager().getPlayerWarpCount(player.getPlayerListName()) >= 3) {
            sender.sendMessage("You have reached the maximum number of warps. Delete an existing warp to create more.");
            return;
        }
        
        if (args.length == 1) {
            sender.sendMessage("Usage: /warpsuite set <warpName> [world] [x] [y] [z]");
            return;
        }
        else if (args.length == 2) {
            String warpName = args[1];
            
            World world = player.getWorld();
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();

            plugin.getWarpManager().addPlayerWarp(player.getPlayerListName(), new Warp(warpName, world, x, y, z));
            player.sendMessage("Warp created: " + warpName);
            return;
        }   
        else if (args.length > 2) {
            String warpName = args[1];
            World world = Bukkit.getWorld(args[2]);
            double x = Double.parseDouble(args[3]);
            double y = Double.parseDouble(args[4]);
            double z = Double.parseDouble(args[5]);

            plugin.getWarpManager().addPlayerWarp(player.getPlayerListName(), new Warp(warpName, world, x, y, z));
            player.sendMessage("Warp created: " + warpName);
        }
    }
}