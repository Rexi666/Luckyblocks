package org.rexi.luckyblocks.events.normal.lucky;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class CookedFoodSurprise {

    public void NormalLuckyCookedFood(Block block) {
        Location l = block.getLocation();
        l.setY(l.getY() + 1);

        l.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);

        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_BEEF, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_CHICKEN, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_PORKCHOP, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_COD, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_MUTTON, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_RABBIT, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_SALMON, 4));
    }
}
