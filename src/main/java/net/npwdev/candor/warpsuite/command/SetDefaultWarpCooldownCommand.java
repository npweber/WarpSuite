package net.npwdev.candor.warpsuite.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.npwdev.candor.warpsuite.WarpSuite;

// SetDefaultWarpCooldownCommand: Handles the /setdefaultwarpcooldown command
public class SetDefaultWarpCooldownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setdefaultwarpcooldown")) {
            if (sender.hasPermission("warpsuite.setdefaultwarpcooldown")) {
                if (args.length == 0) {
                    sender.sendMessage("Usage: /setdefaultwarpcooldown <seconds>");
                    return true;
                }

                // Try to set the warp cooldown
                try {
                    // Parse the cooldown value
                    int seconds = Integer.parseInt(args[0]);

                    // Set the warp cooldown if the value is a number
                    WarpSuite.getInstance().setWarpCooldown(seconds);

                    // Send a message to the sender stating that the warp cooldown has been set
                    sender.sendMessage("Default warp cooldown set to " + seconds + " seconds");
                } catch (NumberFormatException e) {
                    // Send a message to the sender if the cooldown value is not a number
                    sender.sendMessage("Invalid warp cooldown value. Please enter a valid number.");
                }
                return true;
            }
            else {
                sender.sendMessage("You do not have permission to set the default warp cooldown.");
                return true;
            }
        }
        return false;
    }
}