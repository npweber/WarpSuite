package net.npwdev.candor.warpsuite;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Location;
import org.bukkit.World;

import net.npwdev.candor.warpsuite.warps.Warp;

// WarpDataManager: Manages warp data for players

public class WarpDataManager {
    
    private final WarpSuite plugin;

    public WarpDataManager() {  
        this.plugin = WarpSuite.getInstance();
    }

    // Save player warps to plugin-data-folder/warpData
    public void savePlayerWarps() {
        plugin.getLogger().info("Saving player warps...");

        // Get all player warps in memory from the WarpManager && save each player's warps to their own file
        plugin.getWarpManager().getAllWarps().forEach((playerName, warps) -> {
            // Get the player's warp file OR create a new one if it doesn't exist
            File playerFile = new File(plugin.getDataFolder().getAbsolutePath() + "/warpData", playerName + ".yml");
            YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

            // Clear the player's warp data from the file to prepare for updating it with current warps in memory
            playerConfig.set("warps", null);

            // Save all the player's warps currently in memory to the player's warp file
            for (Warp warp : warps)
                playerConfig.set("warps." + warp.getName(), warp.getLocation());

            try {
                playerConfig.save(playerFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not save player warps for " + playerName + ": " + e.getMessage());
            }
        });
    }

    // Load player warps from plugin-data-folder/warpData
    public void loadPlayerWarps() {
        plugin.getLogger().info("Loading player warps...");

        // Get the warpData folder
        File warpDataFolder = new File(plugin.getDataFolder().getAbsolutePath() + "/warpData");

        // If the warpData folder exists, load all player warps from it
        if (warpDataFolder.exists()) {
            // Get all files in the warpData folder
            File[] playerFiles = warpDataFolder.listFiles();

            // If there are files, load all player warps from them
            if (playerFiles != null && playerFiles.length > 0) {
                for (File playerFile : playerFiles) {
                    // Get the player's name from the file name, assuming it has the player's name as the file name and a .yml extension
                    String playerName = playerFile.getName().replace(".yml", "");

                    // Load the configuration data from the file
                    YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

                    // If the configuration data is null, log an error and skip the file
                    if (playerConfig == null) {
                        plugin.getLogger().severe("Could not load player warps from file " + playerFile.getName() + ": " + playerFile.getAbsolutePath());
                        continue;
                    }

                    // Get the "warps" section of the configuration data
                    ConfigurationSection warpsSection = playerConfig.getConfigurationSection("warps");

                    // If the "warps" section is null, log an error and skip the file
                    if (warpsSection == null) {
                        plugin.getLogger().info("No warp data section found in player warp file " + playerFile.getName());
                        continue;
                    }

                    // For each warp in the "warps" section, load the warp
                    for (String warpName : warpsSection.getKeys(false)) {
                        // Get the warp location from the configuration data
                        Location warpLocation = playerConfig.getLocation("warps." + warpName);

                        // Get the warp world
                        World warpWorld = warpLocation.getWorld();

                        // Get the warp x, y, z coordinates && yaw && pitch
                        double warpX = warpLocation.getX();
                        double warpY = warpLocation.getY();
                        double warpZ = warpLocation.getZ();
                        float warpYaw = warpLocation.getYaw();
                        float warpPitch = warpLocation.getPitch();

                        // Add the warp to the WarpManager
                        plugin.getWarpManager().addPlayerWarp(playerName, new Warp(warpName, warpWorld, warpX, warpY, warpZ, warpYaw, warpPitch));
                    }
                }
            } else {
                plugin.getLogger().info("No player warps found. Skipping load...");
            }
        }
        else {
            plugin.getLogger().info("No player warps found. Skipping load...");
        }
    }
}
