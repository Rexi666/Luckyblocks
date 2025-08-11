package org.rexi.luckyblocks.events.normal.lucky;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Cake {

    public void NormalLuckyCake(Player player) {
        Location playerLocation = player.getLocation();
        playerLocation.getBlock().setType(Material.CAKE);
    }
}
