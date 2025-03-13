package net.npwdev.candor.warpsuite.command.subcommand;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.npwdev.candor.warpsuite.WarpSuite;
import net.npwdev.candor.warpsuite.warps.Warp;

// SetSubcommand: Handles the /warpsuite set sub-command

public class SetSubcommand {

    private static final WarpSuite plugin = WarpSuite.getInstance();

    public static void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        // If the player has reached the maximum number of warps, send a message and return
        if(plugin.getWarpManager().getPlayerWarpCount(player.getPlayerListName()) >= 3) {
            sender.sendMessage(WarpSuite.MSG_PREFIX + "You have reached the maximum number of warps. Delete an existing warp to create more.");
            return;
        }

        // If the player doesn't provide a warp name, send a message and return
        if (args.length == 1) {
            sender.sendMessage(WarpSuite.MSG_PREFIX + "Usage: /warpsuite set <warpName> [world] [x] [y] [z]");
            return;
        }
        // If the player doesn't provide a location, use the player's current location
        else if (args.length == 2) {
            // Get the warp name
            String warpName = args[1];

            // Get the player's location
            World world = player.getWorld();
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            float yaw = player.getLocation().getYaw();
            float pitch = player.getLocation().getPitch();

            // Add the warp to the player's warps list
            plugin.getWarpManager().addPlayerWarp(player.getPlayerListName(), new Warp(warpName, world, x, y, z, yaw, pitch));

            // Send a message to the player stating that the warp has been created
            player.sendMessage(WarpSuite.MSG_PREFIX + "Warp created: " + warpName);
            return;
        }   
        // If the player provides a location, use the provided location
        else if (args.length > 2) {
            // Get the warp name
            String warpName = args[1];

            // Get the provided world
            World world = Bukkit.getWorld(args[2]);

            // Get the provided x, y, z coordinates
            double x = Double.parseDouble(args[3]);
            double y = Double.parseDouble(args[4]);
            double z = Double.parseDouble(args[5]);

            // Add the warp to the player's warps list
            plugin.getWarpManager().addPlayerWarp(player.getPlayerListName(), new Warp(warpName, world, x, y, z, 0.0f, 0.0f));

            // Send a message to the player stating that the warp has been created
            player.sendMessage(WarpSuite.MSG_PREFIX + "Warp created: " + warpName);
        }
    }
}