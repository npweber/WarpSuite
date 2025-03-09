package net.npwdev.candor.warpsuite.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.npwdev.candor.warpsuite.WarpSuite;

public class SetDefaultCooldownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setdefaultcooldown")) {
            if (args.length == 0) {
                sender.sendMessage("Usage: /setdefaultcooldown <seconds>");
                return true;
            }

            try {
                int seconds = Integer.parseInt(args[0]);
                WarpSuite.getInstance().setWarpCooldown(seconds);
                sender.sendMessage("Default cooldown set to " + seconds + " seconds");
            } catch (NumberFormatException e) {
                sender.sendMessage("Invalid cooldown value. Please enter a valid number.");
            }
            return true;
        }
        return false;
    }
}