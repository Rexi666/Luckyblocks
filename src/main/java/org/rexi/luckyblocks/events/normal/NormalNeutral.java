package org.rexi.luckyblocks.events.normal;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.rexi.luckyblocks.Luckyblocks;

import java.util.Random;

public class NormalNeutral {

    private final Luckyblocks plugin;
    private final Random random = new Random();

    public NormalNeutral(Luckyblocks plugin) {
        this.plugin = plugin;
    }

    public void NormalNeutralChickenRain(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0.05, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0.05, 0.5, 0));

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0, 0.5, -0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(-0.05, 0.5, -0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(-0.05, 0.5, 0));

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(-0.05, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0.05, 0.5, -0.05));

        player.sendMessage(Component.text("¡Pollos!").color(NamedTextColor.YELLOW));
    }

    public void NormalNeutralGalletas(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);
        for (int i = 0; i < 8; i++) {
            final Location finalLocation = l.clone();
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                finalLocation.getWorld().dropItemNaturally(finalLocation, new ItemStack(Material.COOKIE));
            }, 10L * i);
            l.setY(l.getY() + 1);
        }

        player.sendMessage(Component.text("¡Es hora de galletas!").color(NamedTextColor.YELLOW));
    }

    public void NormalNeutralTintes(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.CYAN_DYE));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.LIGHT_BLUE_DYE));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.LIME_DYE));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.MAGENTA_DYE));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.ORANGE_DYE));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.PINK_DYE));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.PURPLE_DYE));

        player.sendMessage(Component.text("¡Hay que darle color a la vida!").color(NamedTextColor.YELLOW));
    }

    public void NormalNeutralPeces(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COD));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.SALMON));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.PUFFERFISH));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.TROPICAL_FISH));

        player.sendMessage(Component.text("¡Tenemos el mar en casa!").color(NamedTextColor.YELLOW));
    }

    public void NormalNeutralOveja(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        Sheep sheep = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
        sheep.setCustomName("jeb_");
        sheep.setCustomNameVisible(true);

        player.sendMessage(Component.text("¡JEB!").color(NamedTextColor.YELLOW));
    }

    public void NormalNeutralSlime(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        Slime slime = (Slime) l.getWorld().spawnEntity(l, EntityType.SLIME);
        slime.setSize(1);
        slime.setCustomName(ChatColor.translateAlternateColorCodes('&', "&2Jerry"));
        slime.setCustomNameVisible(true);

        player.sendMessage(Component.text("¡JERRY!").color(NamedTextColor.YELLOW));
    }

    public void NormalNeutralPatatas(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);
        for (int i = 0; i < 8; i++) {
            final Location finalLocation = l.clone();
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                finalLocation.getWorld().dropItemNaturally(finalLocation, new ItemStack(Material.POTATO));
            }, 10L * i);
            l.setY(l.getY() + 1);
        }

        player.sendMessage(Component.text("¡Patatas!").color(NamedTextColor.YELLOW));
    }

    public void NormalNeutralOvejaArcoiris(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        String string = "Mr. Arcoiris";
        ChatColor[] colors = new ChatColor[] {ChatColor.AQUA, ChatColor.BLUE, ChatColor.DARK_AQUA, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_PURPLE, ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.GREEN, ChatColor.LIGHT_PURPLE, ChatColor.RED, ChatColor.YELLOW};
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            name.append(colors[random.nextInt(colors.length)]);
            name.append(string.charAt(i));
        }

        for (int i = 0; i < 8; i++) {
            Sheep sheep = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
            sheep.setAdult();
            sheep.setColor(DyeColor.values()[random.nextInt(DyeColor.values().length)]);
            sheep.setCustomName(name.toString());
            sheep.setCustomNameVisible(true);
        }

        player.sendMessage(Component.text("¿Un arcoiris?").color(NamedTextColor.YELLOW));
    }

    public void NormalNeutralComidaCruda(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.BEEF, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.CHICKEN, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.PORKCHOP, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.COD, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.SALMON, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.RABBIT, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.MUTTON, 4));

        player.sendMessage(Component.text("¡Comida para parar un tren!").color(NamedTextColor.YELLOW));
    }
}
