package net.npwdev.candor.warpsuite.command.subcommand;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.npwdev.candor.warpsuite.WarpSuite;
import net.npwdev.candor.warpsuite.warps.Warp;

public class ListSubcommand {
    
    public static void execute(CommandSender sender, String[] args) {
        String playerName = ((Player)sender).getPlayerListName();
        List<Warp> warps = WarpSuite.getInstance().getWarpManager().getPlayerWarps(playerName);
        sender.sendMessage("Warps: " + String.join(", ", warps.stream().map(Warp::getName).collect(Collectors.toList())));
    }
}
