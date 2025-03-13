package net.npwdev.candor.warpsuite.warps;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;

// WarpManager: Manages all warp points in memory

public class WarpManager {

    // The map of all warp points in memory (playerName -> List<Warp>)
    private final Map<String, List<Warp>> warps;

    public WarpManager() {
        this.warps = new HashMap<>();
    }

    // Add a warp to a player
    public void addPlayerWarp(String playerName, Warp warp) {
        // Get the player's warps
        List<Warp> playerWarps = this.getPlayerWarps(playerName);

        // If the player's warps list is null, create a new one
        if (playerWarps == null) 
            playerWarps = new ArrayList<>();

        // Add the warp to the player's warps list
        playerWarps.add(warp);

        // Update or add the player's warps list to the map
        this.warps.put(playerName, playerWarps);
    }

    // Get the player's warps
    public List<Warp> getPlayerWarps(String playerName) {
        return this.warps.get(playerName);
    }

    // Get all warps
    public Map<String, List<Warp>> getAllWarps() {
        return this.warps;
    }

    // Get the number of warps a player has
    public int getPlayerWarpCount(String playerName) {
        return this.warps.get(playerName) == null ? 0 : this.warps.get(playerName).size();
    }

    // Delete a warp from a player
    public void deleteWarp(String warpName, Player player) {
        // Get the player's name
        String playerName = player.getPlayerListName();

        // If the player has no warps, send a message and return
        if (this.getPlayerWarpCount(playerName) == 0) {
            player.sendMessage("You have no warps to delete.");
            return;
        }

        // Get the player's warps
        List<Warp> playerWarps = this.getPlayerWarps(playerName);

        // If the warp doesn't exist, send a message and return
        if(playerWarps.stream().noneMatch(warp -> warp.getName().equals(warpName))) {
            player.sendMessage("Warp not found.");
            return;
        }
        else {
            // Remove the warp from the player's warps list
            playerWarps.removeIf(warp -> warp.getName().equals(warpName));

            // Update or add the player's warps list to the map without the deleted warp
            this.warps.put(playerName, playerWarps);

            // Send a message to the player stating that the warp has been deleted
            player.sendMessage("Warp deleted.");
        }
    }
}
