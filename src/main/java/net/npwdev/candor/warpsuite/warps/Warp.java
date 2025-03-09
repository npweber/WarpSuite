package net.npwdev.candor.warpsuite.warps;

import org.bukkit.Location;
import org.bukkit.World;
public class Warp {
    private final String name;
    private final Location location;

    public Warp(String name, World world, double x, double y, double z) {
        this.name = name;
        this.location = new Location(world, x, y, z);
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}