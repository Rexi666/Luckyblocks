package org.rexi.luckyblocks.events.special;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Registry;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.List;
import java.util.Random;

public class SpecialNeutral {

    Random random = new Random();

    public void SpecialNeutralAldeano(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        Villager v = (Villager) l.getWorld().spawnEntity(l, EntityType.VILLAGER);
        v.setAdult();
        v.setCustomName("Aldeano de la Suerte");
        v.setCustomNameVisible(true);

        // Obtener profesiones desde el registry
        List<Villager.Profession> professions = Registry.VILLAGER_PROFESSION.stream().toList();
        Villager.Profession randomProfession = professions.get(random.nextInt(professions.size()));
        v.setProfession(randomProfession);

        player.sendMessage(Component.text("¡Un amigo!").color(NamedTextColor.YELLOW));
    }

    public void SpecialNeutralTrader(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.WANDERING_TRADER);

        player.sendMessage(Component.text("¡Un amigo!").color(NamedTextColor.YELLOW));
    }
}
