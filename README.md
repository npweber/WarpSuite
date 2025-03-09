# WarpSuite
Candor Studios commissioned Spigot plugin that allows players to set custom warp points in-game.

Players can teleport to custom-defined locations using the following commands:

/warpsuite <set|del|warp|list>

    set: set <warpName> [world] [x] [y] [z]
        Description: Sets a warp point at the player's current location or the provided location, if given.

    del: del <warpName>
        Description: Deletes the player's specified warp point.
    
    warp: warp <warpName> 
        Description: Teleports the player to their custom warp point.
    
    list: list
        Description: List the player's defined custom warp points.