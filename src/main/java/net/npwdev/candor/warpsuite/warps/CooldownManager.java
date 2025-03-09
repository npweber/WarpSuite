package net.npwdev.candor.warpsuite.warps;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

import net.npwdev.candor.warpsuite.WarpSuite;

public class CooldownManager {
    private Map<String, Boolean> cooldowns = new HashMap<>();
    
    public CooldownManager() {
        this.cooldowns = new HashMap<>();
    }

    public boolean playerIsOnCooldown(String playerName) {
        if (this.cooldowns.get(playerName) == null)
            this.cooldowns.put(playerName, false);
        return this.cooldowns.get(playerName);
    }

    public void addPlayerCooldown(String playerName) {
        this.cooldowns.put(playerName, true);
        WarpSuite plugin = WarpSuite.getInstance();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            this.cooldowns.put(playerName, false);
        }, 20 * plugin.getWarpCooldown());
    }
    
}
