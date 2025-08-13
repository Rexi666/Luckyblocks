package org.rexi.luckyblocks.events.special;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpecialNeutral {

    public void SpecialNeutralTrader(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.WANDERING_TRADER);

        player.sendMessage(Component.text("¡Un amigo!").color(NamedTextColor.YELLOW));
    }
}
