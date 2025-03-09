package net.npwdev.candor.warpsuite;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Location;
import org.bukkit.World;

import net.npwdev.candor.warpsuite.warps.Warp;

public class JSONDataManager {
    
    private final WarpSuite plugin;

    public JSONDataManager() {  
        this.plugin = WarpSuite.getInstance();
    }

    public void savePlayerWarps() {
        plugin.getLogger().info("Saving player warps...");

        plugin.getWarpManager().getAllWarps().forEach((playerName, warps) -> {
            File playerFile = new File(plugin.getDataFolder() + "/warpData", playerName + ".yml");
            YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
            for (Warp warp : warps) {
                // TODO: Remove existing persistent data that is not present in the current warp list
                playerConfig.set("warps." + warp.getName(), warp.getLocation());
            }

            try {
                playerConfig.save(playerFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not save player warps for " + playerName + ": " + e.getMessage());
            }
        });
    }

    public void loadPlayerWarps() {
        plugin.getLogger().info("Loading player warps...");

        File warpDataFolder = new File(plugin.getDataFolder().getAbsolutePath() + "/warpData");
        if (warpDataFolder.exists()) {
            for (File playerFile : warpDataFolder.listFiles()) {
                String playerName = playerFile.getName().replace(".yml", "");
                YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
                for (String warpName : playerConfig.getConfigurationSection("warps").getKeys(false)) {
                    Location warpLocation = playerConfig.getLocation("warps." + warpName);
                    World warpWorld = warpLocation.getWorld();
                    double warpX = warpLocation.getX();
                    double warpY = warpLocation.getY();
                    double warpZ = warpLocation.getZ();
                    float warpYaw = warpLocation.getYaw();
                    float warpPitch = warpLocation.getPitch();
                    plugin.getWarpManager().addPlayerWarp(playerName, new Warp(warpName, warpWorld, warpX, warpY, warpZ, warpYaw, warpPitch));
                }
            }
        }
        else {
            plugin.getLogger().info("No player warps found. Skipping load...");
        }
    }
}
