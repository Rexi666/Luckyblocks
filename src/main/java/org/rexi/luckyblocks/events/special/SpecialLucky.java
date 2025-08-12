package org.rexi.luckyblocks.events.special;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.rexi.luckyblocks.Luckyblocks;

public class SpecialLucky {

    private final Luckyblocks plugin;

    public SpecialLucky(Luckyblocks plugin) {
        this.plugin = plugin;
    }

    public void SpecialLuckyDiamondPillar(Player player, Block block) {
        player.sendMessage(Component.text("¿Y esta torre?").color(NamedTextColor.YELLOW));
        Location l = block.getLocation().add(0, 1, 0);

        int time = 0;
        for (int i = 0; i < 8; i++) {
            final Location finalLocation = l.clone();
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                finalLocation.getWorld().dropItemNaturally(finalLocation, new ItemStack(Material.BLUE_TERRACOTTA, 1));
            }, 10L * i);
            l.setY(l.getY() + 1); // Increment for next iteration
            time++;

            if (time == 8) {
                l.getWorld().dropItemNaturally(l, new ItemStack(Material.DIAMOND_BLOCK, 1));
            }
        }
    }

    public void SpecialLuckyDiamondBlock(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.DIAMOND_BLOCK);
        l.getWorld().strikeLightning(l.add(0, 1, 0));

        player.sendMessage(Component.text("¡Un rayo con... ¿regalo?!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckyGoldenApples(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) l.getBlock().getState();

        // Obtenemos el inventario del cofre y añadimos los objetos.
        Inventory inv = chest.getInventory();
        inv.addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
        inv.addItem(new ItemStack(Material.GOLDEN_APPLE, 3));

        player.sendMessage(Component.text("¡Estas manzanas son un encanto!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckyAxe(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) l.getBlock().getState();

        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = axe.getItemMeta();

        // Establecer el nombre del objeto
        meta.displayName(Component.text("Hacha de la suerte").color(NamedTextColor.YELLOW));

        meta.addEnchant(Enchantment.SHARPNESS, 10, true);
        meta.addEnchant(Enchantment.EFFICIENCY, 10, true);
        meta.addEnchant(Enchantment.LOOTING, 10, true);
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);

        axe.setItemMeta(meta);

        // Obtenemos el inventario del cofre y añadimos los objetos.
        Inventory inv = chest.getInventory();
        inv.addItem(axe);

        player.sendMessage(Component.text("¡Uy, soy un hacha!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckyPickaxe(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) l.getBlock().getState();

        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = pickaxe.getItemMeta();

        // Establecer el nombre del objeto
        meta.displayName(Component.text("Pico de la suerte").color(NamedTextColor.YELLOW));

        meta.addEnchant(Enchantment.UNBREAKING, 10, true);
        meta.addEnchant(Enchantment.EFFICIENCY, 10, true);
        meta.addEnchant(Enchantment.LOOTING, 10, true);

        pickaxe.setItemMeta(meta);

        // Obtenemos el inventario del cofre y añadimos los objetos.
        Inventory inv = chest.getInventory();
        inv.addItem(pickaxe);

        player.sendMessage(Component.text("¡Hay que ser minero!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckySword(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) l.getBlock().getState();

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();

        // Establecer el nombre del objeto
        meta.displayName(Component.text("Espada de la suerte").color(NamedTextColor.YELLOW));

        meta.addEnchant(Enchantment.SHARPNESS, 10, true);
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);
        meta.addEnchant(Enchantment.LOOTING, 10, true);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);

        sword.setItemMeta(meta);

        // Obtenemos el inventario del cofre y añadimos los objetos.
        Inventory inv = chest.getInventory();
        inv.addItem(sword);

        player.sendMessage(Component.text("¡¿Quién es el siguiente?!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckyHelmet(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) l.getBlock().getState();

        ItemStack armor = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = armor.getItemMeta();

        // Establecer el nombre del objeto
        meta.displayName(Component.text("Casco de la suerte").color(NamedTextColor.YELLOW));

        meta.addEnchant(Enchantment.PROTECTION, 10, true);
        meta.addEnchant(Enchantment.THORNS, 10, true);
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);

        armor.setItemMeta(meta);

        // Obtenemos el inventario del cofre y añadimos los objetos.
        Inventory inv = chest.getInventory();
        inv.addItem(armor);

        player.sendMessage(Component.text("¡Uy, creo que me han dejado un regalo!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckyChestplate(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) l.getBlock().getState();

        ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = armor.getItemMeta();

        // Establecer el nombre del objeto
        meta.displayName(Component.text("Pechera de la suerte").color(NamedTextColor.YELLOW));

        meta.addEnchant(Enchantment.PROTECTION, 10, true);
        meta.addEnchant(Enchantment.THORNS, 10, true);
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);

        armor.setItemMeta(meta);

        // Obtenemos el inventario del cofre y añadimos los objetos.
        Inventory inv = chest.getInventory();
        inv.addItem(armor);

        player.sendMessage(Component.text("¡Uy, creo que me han dejado un regalo!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckyLeggings(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) l.getBlock().getState();

        ItemStack armor = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = armor.getItemMeta();

        // Establecer el nombre del objeto
        meta.displayName(Component.text("Pantalones de la suerte").color(NamedTextColor.YELLOW));

        meta.addEnchant(Enchantment.PROTECTION, 10, true);
        meta.addEnchant(Enchantment.THORNS, 10, true);
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);

        armor.setItemMeta(meta);

        // Obtenemos el inventario del cofre y añadimos los objetos.
        Inventory inv = chest.getInventory();
        inv.addItem(armor);

        player.sendMessage(Component.text("¡Uy, creo que me han dejado un regalo!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckyBoots(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getBlock().setType(Material.CHEST);
        Chest chest = (Chest) l.getBlock().getState();

        ItemStack armor = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = armor.getItemMeta();

        // Establecer el nombre del objeto
        meta.displayName(Component.text("Botas de la suerte").color(NamedTextColor.YELLOW));

        meta.addEnchant(Enchantment.PROTECTION, 10, true);
        meta.addEnchant(Enchantment.THORNS, 10, true);
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);

        armor.setItemMeta(meta);

        // Obtenemos el inventario del cofre y añadimos los objetos.
        Inventory inv = chest.getInventory();
        inv.addItem(armor);

        player.sendMessage(Component.text("¡Uy, creo que me han dejado un regalo!").color(NamedTextColor.YELLOW));
    }

    public void SpecialLuckyBlocks(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.FIREWORK_ROCKET);
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.EMERALD, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.DIAMOND, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.GOLD_INGOT, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.IRON_INGOT, 4));
        l.getWorld().dropItemNaturally(l, new ItemStack(Material.LAPIS_LAZULI, 8));

        player.sendMessage(Component.text("¡Este regalo es muy caro!").color(NamedTextColor.YELLOW));
    }
}
