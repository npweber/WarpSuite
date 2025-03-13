package net.npwdev.candor.warpsuite.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.npwdev.candor.warpsuite.command.subcommand.*;

// WarpSuiteCommand: Handles the /warpsuite command

public class WarpSuiteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("warpsuite")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("warpsuite.use")) {
                    if (args.length == 0) {
                        sender.sendMessage("Usage: /warpsuite <set|del|warp|list>");
                        return true;
                    }

                    String subCommand = args[0];
                    switch (subCommand) {
                        case "set": {
                            SetSubcommand.execute(sender, args);
                            return true;
                        }
                        case "del": {
                            DelSubcommand.execute(sender, args);   
                            return true;
                        }
                        case "warp": {
                            WarpSubcommand.execute(sender, args);
                            return true;
                        }
                        case "list": {
                            ListSubcommand.execute(sender, args);
                            return true;
                        }
                        default: {
                            sender.sendMessage("Usage: /warpsuite <set|del|warp|list>");
                            return true;
                        }
                    }
                }
                else {
                    sender.sendMessage("You do not have permission to use warps.");
                    return true;
                }
            }
            else {
                sender.sendMessage("This command can only be used by players.");
                return true;
            }
        }
        return false;
    }
}