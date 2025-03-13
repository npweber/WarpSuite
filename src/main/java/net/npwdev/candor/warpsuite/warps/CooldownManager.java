package net.npwdev.candor.warpsuite.warps;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

import net.npwdev.candor.warpsuite.WarpSuite;

// CooldownManager: Manages cooldowns for warps

public class CooldownManager {
    private Map<String, Boolean> cooldowns = new HashMap<>();
    
    public CooldownManager() {
        this.cooldowns = new HashMap<>();
    }

    // Check if a player is on cooldown
    public boolean playerIsOnCooldown(String playerName) {
        // If the player is not in the cooldowns map, add them to the map with a false value, showing they are not yet on cooldown
        if (this.cooldowns.get(playerName) == null)
            this.cooldowns.put(playerName, false);

        // Return the player's cooldown status
        return this.cooldowns.get(playerName);
    }

    // Add a cooldown to a player
    public void addPlayerCooldown(String playerName) {
        // Add the player to the cooldowns map with a true value, showing they are now on cooldown
        this.cooldowns.put(playerName, true);

        // Get the plugin instance
        WarpSuite plugin = WarpSuite.getInstance();

        // Run a task to remove the cooldown after the cooldown time has passed
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            this.cooldowns.put(playerName, false);
        }, 20 * plugin.getWarpCooldown());
    }
    
}
