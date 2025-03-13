# WarpSuite
Candor Studios commissioned Spigot plugin that allows players to set custom warp points in-game.

* Only 3 warp points are allowed per player.
* Players' warps are stored in .yml files located at: "plugins/WarpSuite/warpData".
* Players will receive a "warp cooldown" after warping that has a configurable duration in seconds.
* Only OPs & Players with permission node "warpsuite.use" may use /warpsuite.

## Plugin commands:

```
/warpsuite <set|del|warp|list>

    set: set <warpName> [world] [x] [y] [z]
        Description: Sets a warp point at the player's current location or the provided location, if given.

    del: del <warpName>
        Description: Deletes the player's specified warp point.
    
    warp: warp <warpName> 
        Description: Teleports the player to their custom warp point.
    
    list: list
        Description: List the player's defined custom warp points.

/setdefaultwarpcooldown <seconds>
    Description: Sets the cooldown duration, in seconds, that prohibits players from intentionally overloading the server with warp commands.

```

**Enjoy the ability to teleport to up to 3 locations!**