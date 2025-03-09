package net.npwdev.candor.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;
import net.npwdev.candor.warpsuite.command.WarpSuiteCommand;

public class WarpSuite extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("WarpSuite has been enabled!");

        getCommand("ws").setExecutor(new WarpSuiteCommand());
    }
    
    @Override
    public void onDisable() {
        getLogger().info("WarpSuite has been disabled!");
    }

    public static WarpSuite getInstance() {
        return WarpSuite.getPlugin(WarpSuite.class);
    }
}