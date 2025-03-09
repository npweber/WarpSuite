package net.npwdev.candor.warpsuite.warps;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public class WarpManager {

    private final HashMap<String, List<Warp>> warps;

    public WarpManager() {
        this.warps = new HashMap<>();
    }

    public void addPlayerWarp(String playerName, Warp warp) {
        List<Warp> playerWarps = this.getPlayerWarps(playerName);
        if (playerWarps == null) 
            playerWarps = new ArrayList<>();

        playerWarps.add(warp);
        this.warps.put(playerName, playerWarps);
    }

    public List<Warp> getPlayerWarps(String playerName) {
        return this.warps.get(playerName);
    }

    public int getPlayerWarpCount(String playerName) {
        return this.warps.get(playerName) == null ? 0 : this.warps.get(playerName).size();
    }

    public void deleteWarp(String warpName, Player player) {
        String playerName = player.getPlayerListName();

        if (this.getPlayerWarpCount(playerName) == 0) {
            player.sendMessage("You have no warps to delete.");
            return;
        }

        List<Warp> playerWarps = this.getPlayerWarps(playerName);
        if(playerWarps.stream().noneMatch(warp -> warp.getName().equals(warpName))) {
            player.sendMessage("Warp not found.");
            return;
        }
        else {
            playerWarps.removeIf(warp -> warp.getName().equals(warpName));
            this.warps.put(playerName, playerWarps);
            player.sendMessage("Warp deleted.");
        }
    }
}
