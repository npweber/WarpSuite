package net.npwdev.candor.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;

import net.npwdev.candor.warpsuite.command.WarpSuiteCommand;
import net.npwdev.candor.warpsuite.warps.WarpManager;

public class WarpSuite extends JavaPlugin {

    private WarpManager warpManager;

    @Override
    public void onEnable() {
        getLogger().info("WarpSuite has been enabled!");
        
        warpManager = new WarpManager();
        getCommand("ws").setExecutor(new WarpSuiteCommand());
    }
    
    @Override
    public void onDisable() {
        getLogger().info("WarpSuite has been disabled!");
    }

    public static WarpSuite getInstance() {
        return WarpSuite.getPlugin(WarpSuite.class);
    }

    public WarpManager getWarpManager() {
        return this.warpManager;
    }
}