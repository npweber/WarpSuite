package net.npwdev.candor.warpsuite;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import net.npwdev.candor.warpsuite.command.WarpSuiteCommand;
import net.npwdev.candor.warpsuite.command.SetDefaultWarpCooldownCommand;
import net.npwdev.candor.warpsuite.warps.WarpManager;
import net.npwdev.candor.warpsuite.warps.CooldownManager;

import java.io.File;

// WarpSuite: Plugin Main Class

public class WarpSuite extends JavaPlugin {

    public static final String MSG_PREFIX = ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "WarpSuite" + ChatColor.GRAY + "] " + ChatColor.WHITE;

    // WarpManager: Manages all warp points
    private WarpManager warpManager;

    // WarpDataManager: Manages warp data for players
    private WarpDataManager warpDataManager;

    // WarpCooldown: The cooldown time in seconds for warps
    private int warpCooldown;

    // CooldownManager: Manages cooldowns for warps
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        warpManager = new WarpManager();
        warpDataManager = new WarpDataManager();

        // Load player warps from plugin-data-folder/warpData
        warpDataManager.loadPlayerWarps();

        // Check if config.yml exists, if not, save default config
        if (!new File(getDataFolder(), "config.yml").exists())
            saveDefaultConfig();

        // Get warp cooldown from config.yml
        warpCooldown = getConfig().getInt("warp-cooldown");

        cooldownManager = new CooldownManager();

        // Set up commands
        getCommand("ws").setExecutor(new WarpSuiteCommand());
        getCommand("setdefaultwarpcooldown").setExecutor(new SetDefaultWarpCooldownCommand());

        getLogger().info("WarpSuite has been enabled!");
    }
    
    @Override
    public void onDisable() {
        // Save player warps to plugin-data-folder/warpData
        warpDataManager.savePlayerWarps();

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

    // Set the warp cooldown in config.yml and update the cooldown variable
    public void setWarpCooldown(int warpCooldown) {
        this.warpCooldown = warpCooldown;
        getConfig().set("warp-cooldown", warpCooldown);
        saveConfig();
    }
}