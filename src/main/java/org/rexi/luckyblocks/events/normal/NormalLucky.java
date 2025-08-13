package org.rexi.luckyblocks.events.normal;

import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.item.TooltipDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NormalLucky {

    private final Random random = new Random();

    public void NormalLuckyCake(Player player) {
        Location playerLocation = player.getLocation();
        playerLocation.getBlock().setType(Material.CAKE);

        player.sendMessage(Component.text("¿De quién es el cumpleaños?").color(NamedTextColor.YELLOW));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1.0f, 1.0f);
    }

    public void NormalLuckyCookedFood(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);

        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_BEEF, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_CHICKEN, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_PORKCHOP, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_COD, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_MUTTON, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_RABBIT, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_SALMON, 4));

        player.sendMessage(Component.text("Vamos a comer...").color(NamedTextColor.YELLOW));
    }

    public void NormalLuckyEmeraldBlock(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.EMERALD_BLOCK);
        l.getWorld().strikeLightning(l.add(0, 1, 0));

        player.sendMessage(Component.text("¡Un rayo con... ¿regalo?!").color(NamedTextColor.YELLOW));
    }

    public void NormalLuckyIronBlock(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().dropItemNaturally(l, new ItemStack(Material.IRON_BLOCK));

        player.sendMessage(Component.text("¿Que es ese bloque?").color(NamedTextColor.YELLOW));
    }

    public void NormalLuckyPotions(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        meta.displayName(Component.text("¿Poción de la Suerte?").color(NamedTextColor.YELLOW));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("Una pócima misteriosa...").color(NamedTextColor.GRAY));
        lore.add(Component.text("¡No sabes qué efectos tiene!").color(NamedTextColor.DARK_PURPLE));
        meta.lore(lore);

        meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 45*20, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 180*20, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 180*20, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 0, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 180*20, 0), true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.STRENGTH, 180*20, 0), true);

        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        potion.setItemMeta(meta);
        l.getWorld().dropItemNaturally(l, potion);

        player.sendMessage(Component.text("¡Soy una bruja!").color(NamedTextColor.YELLOW));
    }

    public void NormalLuckyCats(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        List<Cat.Type> variants = Registry.CAT_VARIANT.stream().toList();

        List<Cat.Type> variantList = new ArrayList<>(variants);

        for (int i = 0; i < 8; i++) {
            Location spawnLoc = l.clone().add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));

            Cat cat = (Cat) l.getWorld().spawnEntity(spawnLoc, EntityType.CAT);
            cat.setAdult();
            cat.setOwner(player);

            cat.setCatType(variantList.get(random.nextInt(variantList.size())));
        }
        player.sendMessage(Component.text("¡Michis!").color(NamedTextColor.YELLOW));
    }

    public void NormalLuckyDogs(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        for (int i = 0; i < 8; i++) {
            Location spawnLoc = l.clone().add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));

            Wolf wolf = (Wolf) l.getWorld().spawnEntity(spawnLoc, EntityType.WOLF);
            wolf.setAdult();
            wolf.setOwner(player);
        }
        player.sendMessage(Component.text("¡Hoy es un día de perros!").color(NamedTextColor.YELLOW));
    }

    public void NormalLuckyExperience(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0.05, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0.05, 0.5, 0));

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0, 0.5, -0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(-0.05, 0.5, -0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(-0.05, 0.5, 0));

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(-0.05, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EXPERIENCE_BOTTLE).setVelocity(new Vector(0.05, 0.5, -0.05));

        player.sendMessage(Component.text("¡Cae experiencia del cielo!").color(NamedTextColor.YELLOW));
    }
}
