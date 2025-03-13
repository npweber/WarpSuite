package net.npwdev.candor.warpsuite.warps;

import org.bukkit.Location;
import org.bukkit.World;

// Warp: Represents a warp point in the game

public class Warp {
    private final String name;
    private final Location location;

    public Warp(String name, World world, double x, double y, double z, float yaw, float pitch) {
        this.name = name;
        this.location = new Location(world, x, y, z, yaw, pitch);
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}