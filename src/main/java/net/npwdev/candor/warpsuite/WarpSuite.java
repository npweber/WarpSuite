package net.npwdev.candor.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;

import net.npwdev.candor.warpsuite.command.WarpSuiteCommand;
import net.npwdev.candor.warpsuite.command.SetDefaultCooldownCommand;
import net.npwdev.candor.warpsuite.warps.WarpManager;
import net.npwdev.candor.warpsuite.warps.CooldownManager;

import java.io.File;

public class WarpSuite extends JavaPlugin {

    private WarpManager warpManager;
    private int warpCooldown;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        getLogger().info("WarpSuite has been enabled!");
        
        warpManager = new WarpManager();

        if (!new File(getDataFolder(), "config.yml").exists())
            saveDefaultConfig();

        warpCooldown = getConfig().getInt("warp-cooldown");
        cooldownManager = new CooldownManager();

        getCommand("ws").setExecutor(new WarpSuiteCommand());
        getCommand("setdefaultcooldown").setExecutor(new SetDefaultCooldownCommand());
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

    public CooldownManager getCooldownManager() {
        return this.cooldownManager;
    }

    public int getWarpCooldown() {
        return this.warpCooldown;
    }

    public void setWarpCooldown(int warpCooldown) {
        this.warpCooldown = warpCooldown;
        getConfig().set("warp-cooldown", warpCooldown);
        saveConfig();
    }
}