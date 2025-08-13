package org.rexi.luckyblocks.events.special;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.TNT;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpecialUnlucky implements Listener {

    public void SpecialUnluckyAnvil(Player player, Block block) {
        Location l = block.getLocation().add(0.5, 1, 0.5);

        for (int x = l.getBlockX() - 1; x <= l.getBlockX() + 1; x++) {
            for (int z = l.getBlockZ() - 1; z <= l.getBlockZ() + 1; z++) {
                for (int y = l.getBlockY() - 1; y <= l.getBlockY() + 2; y++) {
                    Location current = new Location(l.getWorld(), x, y, z);

                    if (y == l.getBlockY() - 1) {
                        // Suelo de obsidiana
                        current.getBlock().setType(Material.OBSIDIAN);
                    } else if (!(x == l.getBlockX() && z == l.getBlockZ())) {
                        // Paredes de iron bars
                        current.getBlock().setType(Material.IRON_BARS);
                    }
                }
            }
        }

        player.teleport(l.clone());

        l.add(0, 16, 0).getBlock().setType(Material.ANVIL);
        l.add(0, 24, 0).getBlock().setType(Material.ANVIL);

        player.sendMessage(Component.text("¡CUIDADO, Meteoritos!").color(NamedTextColor.YELLOW));
    }

    public void SpecialUnluckyZombie(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        ItemStack axe = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = axe.getItemMeta();

        // Establecer el nombre del objeto
        meta.displayName(Component.text("Espada de la suerte").color(NamedTextColor.YELLOW));

        meta.addEnchant(Enchantment.SHARPNESS, 10, true);
        meta.addEnchant(Enchantment.EFFICIENCY, 10, true);
        meta.addEnchant(Enchantment.LOOTING, 10, true);
        meta.addEnchant(Enchantment.UNBREAKING, 10, true);

        axe.setItemMeta(meta);

        Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
        zombie.setMaxHealth(60D);
        zombie.setHealth(60D);
        zombie.getEquipment().setItemInMainHand(axe.clone());
        zombie.getEquipment().setItemInMainHandDropChance(0F);
        zombie.setCanPickupItems(false);
        zombie.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eBryan"));
        zombie.setCustomNameVisible(true);

        player.sendMessage(Component.text("¿Bryan, que te ha pasado?").color(NamedTextColor.YELLOW));
    }

    public void SpecialUnluckyCreeper(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l, EntityType.CREEPER);
        l.getWorld().strikeLightning(l);

        player.sendMessage(Component.text("¡NoOOooOoOoOooOOooO mi casa!").color(NamedTextColor.YELLOW));
    }

    public void SpecialUnluckyWater(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);
        World world = l.getWorld();

        int baseX = l.getBlockX();
        int baseY = l.getBlockY();
        int baseZ = l.getBlockZ();

        for (int x = baseX - 1; x <= baseX + 1; x++) {
            for (int z = baseZ - 1; z <= baseZ + 1; z++) {
                for (int y = baseY - 1; y <= baseY + 2; y++) {
                    if (x == baseX && z == baseZ && y != baseY - 1 && y != baseY + 2) {
                        world.getBlockAt(x, y, z).setType(Material.WATER);
                    } else {
                        world.getBlockAt(x, y, z).setType(Material.OBSIDIAN);
                    }
                }
            }
        }

        player.teleport(l);
        player.sendMessage(Component.text("Una muerte lenta...").color(NamedTextColor.YELLOW));
    }

    public void SpecialUnluckyTNT(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);
        l.getWorld().createExplosion(l, 7F, false, false);

        player.sendMessage(Component.text("¡BUM!").color(NamedTextColor.YELLOW));
    }

    private final Map<Location, Location> pairedBlocks = new HashMap<>();
    private final Map<Location, Boolean> diamondBlocks = new HashMap<>();
    public void SpecialUnluckyDiamond(Player player, Block block) {
        Location base = block.getLocation().add(0, 1, 0);

        Location block1 = base.clone();
        Location block2 = base.clone().add(0, 1, 0);

        block1.getBlock().setType(Material.DIAMOND_BLOCK);
        block2.getBlock().setType(Material.DIAMOND_BLOCK);

        // Elegir aleatoriamente cuál es el bueno
        boolean firstIsGood = new Random().nextBoolean();

        diamondBlocks.put(block1, firstIsGood);
        diamondBlocks.put(block2, !firstIsGood);

        pairedBlocks.put(block1, block2);
        pairedBlocks.put(block2, block1);

        player.sendTitle("", ChatColor.translateAlternateColorCodes('&', "&7&o¿Cuál es el de verdad?"), 10, 20, 10);
        player.sendMessage(Component.text("Cuál será el verdadero...").color(NamedTextColor.YELLOW));
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();
        if (diamondBlocks.containsKey(loc)) {
            boolean isGood = diamondBlocks.get(loc);
            Location otherLoc = pairedBlocks.get(loc);

            if (isGood) {
                event.getPlayer().sendMessage(ChatColor.GREEN + "¡Has encontrado el diamante verdadero!");
                event.getBlock().setType(Material.AIR);
                event.setDropItems(false);
                event.getPlayer().give(new ItemStack (Material.DIAMOND_BLOCK, 1));

                // Romper la pareja sin explosión
                if (otherLoc != null && otherLoc.getBlock().getType() == Material.DIAMOND_BLOCK) {
                    otherLoc.getBlock().setType(Material.AIR);
                    event.getBlock().getWorld().playEffect(otherLoc, Effect.STEP_SOUND, Material.DIAMOND_BLOCK); // efecto visual opcional
                    diamondBlocks.remove(otherLoc);
                    pairedBlocks.remove(otherLoc);
                }

            } else {
                event.getPlayer().sendMessage(ChatColor.RED + "Era falso... ¡mala suerte!");
                loc.getWorld().createExplosion(loc, 7F, false, false);
                event.setDropItems(false);

                // Romper la pareja (si es el verdadero) sin explosión
                if (otherLoc != null && otherLoc.getBlock().getType() == Material.DIAMOND_BLOCK) {
                    otherLoc.getBlock().setType(Material.AIR);
                    diamondBlocks.remove(otherLoc);
                    pairedBlocks.remove(otherLoc);
                }
            }

            diamondBlocks.remove(loc);
            pairedBlocks.remove(loc);
        }
    }

    public void SpecialUnluckyFlyingCreeper(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        for (int i = 0; i < 4; i++) {
            Bat bat = (Bat) l.getWorld().spawnEntity(l, EntityType.BAT);
            bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));

            ArmorStand stand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setMarker(true);
            stand.setGravity(false);

            Creeper creeper = (Creeper) l.getWorld().spawnEntity(l, EntityType.CREEPER);

            // Montaje: creeper sobre Armor Stand, Armor Stand sobre Bat
            stand.addPassenger(creeper);
            bat.addPassenger(stand);
        }

        player.sendMessage(Component.text("¡Explosiones voladoras!").color(NamedTextColor.YELLOW));
    }

    public void SpecialUnluckyFlyingTNT(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);


        for (int i = 0; i < 4; i++) {
            Bat bat = (Bat) l.getWorld().spawnEntity(l, EntityType.BAT);
            bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));

            ArmorStand stand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setMarker(true);
            stand.setGravity(false);

            TNTPrimed tnt = (TNTPrimed) l.getWorld().spawnEntity(l, EntityType.TNT);

            // Montaje: TNT sobre Armor Stand, Armor Stand sobre Bat
            stand.addPassenger(tnt);
            bat.addPassenger(stand);
        }

        player.sendMessage(Component.text("¡Explosiones voladoras!").color(NamedTextColor.YELLOW));
    }

    public void SpecialUnluckyMuchasTNT(Player player, Block block) {
        Location l = block.getLocation().add(0, 1, 0);

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.TNT).setVelocity(new Vector(0, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.TNT).setVelocity(new Vector(0.05, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.TNT).setVelocity(new Vector(0.05, 0.5, 0));

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.TNT).setVelocity(new Vector(0, 0.5, -0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.TNT).setVelocity(new Vector(-0.05, 0.5, -0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.TNT).setVelocity(new Vector(-0.05, 0.5, 0));

        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.TNT).setVelocity(new Vector(-0.05, 0.5, 0.05));
        l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.TNT).setVelocity(new Vector(0.05, 0.5, -0.05));

        player.sendMessage(Component.text("Voy a morir, ¿verdad?").color(NamedTextColor.YELLOW));
    }
}
