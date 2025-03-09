package net.npwdev.candor.warpsuite.command.subcommand;

import org.bukkit.command.CommandSender;

public class ListSubcommand {
    
    public static void execute(CommandSender sender, String[] args) {
        /*
        List<Warp> warps = WarpSuite.getWarpManager().getWarps();
        sender.sendMessage("Warps: " + String.join(", ", warps.stream().map(Warp::getName).collect(Collectors.toList())));
        */
        sender.sendMessage("Warps: ");
    }
}
