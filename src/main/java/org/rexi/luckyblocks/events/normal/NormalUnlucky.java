package org.rexi.luckyblocks.events.normal;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class NormalUnlucky {

    public void NormalUnluckyPotions(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        meta.displayName(Component.text("¿Poción de la Suerte?").color(NamedTextColor.YELLOW));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Una pócima misteriosa...").color(NamedTextColor.GRAY));
        lore.add(Component.text("¡No sabes qué efectos tiene!").color(NamedTextColor.DARK_PURPLE));
        meta.lore(lore);

        meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 45, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 90, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 90, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 0, 0), true);

        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        potion.setItemMeta(meta);
        l.getWorld().dropItemNaturally(l, potion);

        player.sendMessage(Component.text("¡Soy una bruja!").color(NamedTextColor.YELLOW));
    }
}
