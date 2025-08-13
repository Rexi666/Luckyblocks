package org.rexi.luckyblocks.events.normal;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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

        meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 45*20, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 90*20, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 90*20, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 0, 0), true);

        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        potion.setItemMeta(meta);
        l.getWorld().dropItemNaturally(l, potion);

        player.sendMessage(Component.text("¡Soy una bruja!").color(NamedTextColor.YELLOW));
    }

    public void NormalUnluckySlime(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        Slime slime = (Slime) l.getWorld().spawnEntity(l, EntityType.SLIME);
        slime.setSize(7);
        slime.setTarget(player);

        player.sendMessage(Component.text("¡Uy, como pega!").color(NamedTextColor.YELLOW));
    }

    public void NormalUnluckyScary(Player player, Block block) {
        Location startLoc = player.getLocation();
        Location highLoc = startLoc.clone().add(0, 100, 0);

        Block groundBlock = highLoc.getBlock();
        while (groundBlock.getY() > player.getWorld().getMinHeight()) {
            Block below = groundBlock.getRelative(0, -1, 0);
            if (below.getType().isSolid()) {
                groundBlock = below;
                break;
            }
            groundBlock = below;
        }

        Location cobwebLoc = groundBlock.getLocation().add(0, 3, 0);
        cobwebLoc.getBlock().setType(Material.COBWEB);

        highLoc.setX(highLoc.getBlockX() + 0.5); // centrar en bloque
        highLoc.setZ(highLoc.getBlockZ() + 0.5);
        player.teleport(highLoc);

        player.sendTitle(
                ChatColor.translateAlternateColorCodes('&', "&e&oHay una telaraña abajo"),
                ChatColor.translateAlternateColorCodes('&', "&7&o¿serás capaz de sobrevivir?"),
                10, 30, 10
        );
        player.sendMessage(
                Component.text("Hay una telaraña abajo, ¿serás capaz de sobrevivir?")
                        .color(NamedTextColor.YELLOW)
        );
    }

    public void NormalUnluckyJump(Player player, Block block) {
        player.setVelocity(new Vector(0, 2.75, 0));
        player.sendMessage(Component.text("¡Hasta el infinito y más allá!").color(NamedTextColor.YELLOW));
    }

    public void NormalUnluckyWitch(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30*20, 1));
        l.getWorld().spawnEntity(l, EntityType.WITCH);

        for (int i = 0; i < 16; i++) {
            l.getWorld().spawnEntity(l, EntityType.BAT);
        }
        player.sendMessage(Component.text("¡AHHHHH, no veo!").color(NamedTextColor.YELLOW));
    }

    public void NormalUnluckyPigman(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        EntityType type = EntityType.ZOMBIFIED_PIGLIN;
        PigZombie pigman = (PigZombie) l.getWorld().spawnEntity(l, type);
        pigman.setAngry(true);
        pigman.setTarget(player);

        player.sendMessage(Component.text("¡AHHHHH, no veo!").color(NamedTextColor.YELLOW));
    }
}
