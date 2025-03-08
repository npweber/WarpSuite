package net.npwdev.candor.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;

public class WarpSuite extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("WarpSuite has been enabled!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("WarpSuite has been disabled!");
    }
}