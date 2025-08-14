package org.rexi.luckyblocks.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SpongeAbsorbEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.rexi.luckyblocks.Luckyblocks;
import org.rexi.luckyblocks.events.normal.NormalLucky;
import org.rexi.luckyblocks.events.normal.NormalNeutral;
import org.rexi.luckyblocks.events.normal.NormalUnlucky;
import org.rexi.luckyblocks.events.special.SpecialLucky;
import org.rexi.luckyblocks.events.special.SpecialNeutral;
import org.rexi.luckyblocks.events.special.SpecialUnlucky;

import java.util.*;
import java.util.function.BiConsumer;

import static org.bukkit.Bukkit.getServer;

public class LuckyblockListener implements Listener {

    private final Random random = new Random();

    private final Luckyblocks plugin;

    /* ####################################
    #               EVENTOS               #
    #################################### */

    public record EventEntry(String name, BiConsumer<Block, Player> action) {}

    private final List<EventEntry> normalEvents = new ArrayList<>();
    private final List<EventEntry> specialEvents = new ArrayList<>();

    private final NormalLucky NormalLucky;
    private final NormalUnlucky NormalUnlucky;
    private final NormalNeutral NormalNeutral;
    private final SpecialLucky SpecialLucky;
    private final SpecialNeutral SpecialNeutral;
    private final SpecialUnlucky SpecialUnlucky;

    public LuckyblockListener(Luckyblocks plugin) {
        this.plugin = plugin;

        this.NormalLucky = new NormalLucky();
        this.NormalUnlucky = new NormalUnlucky();
        this.NormalNeutral = new NormalNeutral(plugin);

        this.SpecialLucky = new SpecialLucky(plugin);
        this.SpecialNeutral = new SpecialNeutral();
        this.SpecialUnlucky = new SpecialUnlucky();

        getServer().getPluginManager().registerEvents(SpecialUnlucky, plugin);

        registrarEventosNormales();
        registrarEventosEspeciales();
    }

    public List<EventEntry> getNormalEvents() {
        return normalEvents;
    }

    public List<EventEntry> getSpecialEvents() {
        return specialEvents;
    }


    /* ####################################
    #              LISTENERS              #
    #################################### */

    private final Set<Location> blockedLocations = new HashSet<>();

    @EventHandler
    public void onLuckyBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        String blockType = block.getType().toString();
        Location loc = block.getLocation();

        if (blockedLocations.contains(loc)) return;

        if (blockType.equalsIgnoreCase("OAK_LOG")) {
            blockedLocations.add(loc);

            // Desbloquear tras 10 ticks
            Bukkit.getScheduler().runTaskLater(plugin, () -> blockedLocations.remove(loc), 10L);
            return;
        }


        if (blockType.equalsIgnoreCase("SPONGE")) {
            Player player = event.getPlayer();
            player.spawnParticle(Particle.HAPPY_VILLAGER, block.getLocation(), 50, 0.5, 0.5, 0.5, 0.1);
            player.playSound(player.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 1.0f);
            event.setDropItems(false);

            normalLuckyBlockBreak(block, player);
        } else if (blockType.equalsIgnoreCase("WET_SPONGE")) {
            Player player = event.getPlayer();
            player.spawnParticle(Particle.HAPPY_VILLAGER, block.getLocation(), 50, 0.5, 0.5, 0.5, 0.1);
            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THROW, 1.0f, 1.0f);
            event.setDropItems(false);

            specialLuckyBlockBreak(block, player);
        }
    }

    @EventHandler
    public void BlockLuckyBlockChange(SpongeAbsorbEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        // Evitar que se destruyan bloques
        event.blockList().clear();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        // Clonamos el inventario para dropearlo manualmente
        PlayerInventory inv = player.getInventory();
        Location deathLoc = player.getLocation();

        for (ItemStack item : inv.getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                deathLoc.getWorld().dropItemNaturally(deathLoc, item);
            }
        }

        for (ItemStack armor : inv.getArmorContents()) {
            if (armor != null && armor.getType() != Material.AIR) {
                deathLoc.getWorld().dropItemNaturally(deathLoc, armor);
            }
        }

        // Limpiar drops automáticos (por si OneBlock los bloquea)
        event.getDrops().clear();
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }

    // Ejecutar aleatorio
    public void normalLuckyBlockBreak(Block block, Player player) {
        int randomIndex = random.nextInt(normalEvents.size());
        normalEvents.get(randomIndex).action().accept(block, player);
    }

    public void specialLuckyBlockBreak(Block block, Player player) {
        int randomIndex = random.nextInt(specialEvents.size());
        specialEvents.get(randomIndex).action().accept(block, player);
    }

    public void registrarEventosNormales() {
        // Lucky
        normalEvents.add(new EventEntry("NormalLuckyCake", (block, player) -> NormalLucky.NormalLuckyCake(player)));
        normalEvents.add(new EventEntry("NormalLuckyCookedFood", (block, player) -> NormalLucky.NormalLuckyCookedFood(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyEmeraldBlock", (block, player) -> NormalLucky.NormalLuckyEmeraldBlock(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyIronBlock", (block, player) -> NormalLucky.NormalLuckyIronBlock(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyPotions", (block, player) -> NormalLucky.NormalLuckyPotions(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyCats", (block, player) -> NormalLucky.NormalLuckyCats(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyDogs", (block, player) -> NormalLucky.NormalLuckyDogs(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyExperience", (block, player) -> NormalLucky.NormalLuckyExperience(player, block)));

        // Neutral
        normalEvents.add(new EventEntry("NormalNeutralChickenRain", (block, player) -> NormalNeutral.NormalNeutralChickenRain(player, block)));
        normalEvents.add(new EventEntry("NormalNeutralGalletas", (block, player) -> NormalNeutral.NormalNeutralGalletas(player, block)));
        normalEvents.add(new EventEntry("NormalNeutralTintes", (block, player) -> NormalNeutral.NormalNeutralTintes(player, block)));
        normalEvents.add(new EventEntry("NormalNeutralPeces", (block, player) -> NormalNeutral.NormalNeutralPeces(player, block)));
        normalEvents.add(new EventEntry("NormalNeutralOveja", (block, player) -> NormalNeutral.NormalNeutralOveja(player, block)));
        normalEvents.add(new EventEntry("NormalNeutralSlime", (block, player) -> NormalNeutral.NormalNeutralSlime(player, block)));
        normalEvents.add(new EventEntry("NormalNeutralPatatas", (block, player) -> NormalNeutral.NormalNeutralPatatas(player, block)));
        normalEvents.add(new EventEntry("NormalNeutralOvejaArcoiris", (block, player) -> NormalNeutral.NormalNeutralOvejaArcoiris(player, block)));
        normalEvents.add(new EventEntry("NormalNeutralComidaCruda", (block, player) -> NormalNeutral.NormalNeutralComidaCruda(player, block)));

        // Unlucky
        normalEvents.add(new EventEntry("NormalUnluckyPotions", (block, player) -> NormalUnlucky.NormalUnluckyPotions(player, block)));
        normalEvents.add(new EventEntry("NormalUnluckySlime", (block, player) -> NormalUnlucky.NormalUnluckySlime(player, block)));
        normalEvents.add(new EventEntry("NormalUnluckyScary", (block, player) -> NormalUnlucky.NormalUnluckyScary(player, block)));
        normalEvents.add(new EventEntry("NormalUnluckyJump", (block, player) -> NormalUnlucky.NormalUnluckyJump(player, block)));
        normalEvents.add(new EventEntry("NormalUnluckyWitch", (block, player) -> NormalUnlucky.NormalUnluckyWitch(player, block)));
        normalEvents.add(new EventEntry("NormalUnluckyPigman", (block, player) -> NormalUnlucky.NormalUnluckyPigman(player, block)));
    }

    public void registrarEventosEspeciales() {
        // Lucky
        specialEvents.add(new EventEntry("SpecialLuckyDiamondPillar", (block, player) -> SpecialLucky.SpecialLuckyDiamondPillar(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyDiamondBlock", (block, player) -> SpecialLucky.SpecialLuckyDiamondBlock(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyGoldenApples", (block, player) -> SpecialLucky.SpecialLuckyGoldenApples(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyAxe", (block, player) -> SpecialLucky.SpecialLuckyAxe(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyPickaxe", (block, player) -> SpecialLucky.SpecialLuckyPickaxe(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckySword", (block, player) -> SpecialLucky.SpecialLuckySword(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyHelmet", (block, player) -> SpecialLucky.SpecialLuckyHelmet(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyChestplate", (block, player) -> SpecialLucky.SpecialLuckyChestplate(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyLeggings", (block, player) -> SpecialLucky.SpecialLuckyLeggings(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyBoots", (block, player) -> SpecialLucky.SpecialLuckyBoots(player, block)));
        specialEvents.add(new EventEntry("SpecialLuckyBlocks", (block, player) -> SpecialLucky.SpecialLuckyBlocks(player, block)));

        // Neutral
        specialEvents.add(new EventEntry("SpecialNeutralTrader", (block, player) -> SpecialNeutral.SpecialNeutralTrader(player, block)));

        // Unlucky
        specialEvents.add(new EventEntry("SpecialUnluckyAnvil", (block, player) -> SpecialUnlucky.SpecialUnluckyAnvil(player, block)));
        specialEvents.add(new EventEntry("SpecialUnluckyZombie", (block, player) -> SpecialUnlucky.SpecialUnluckyZombie(player, block)));
        specialEvents.add(new EventEntry("SpecialUnluckyCreeper", (block, player) -> SpecialUnlucky.SpecialUnluckyCreeper(player, block)));
        specialEvents.add(new EventEntry("SpecialUnluckyWater", (block, player) -> SpecialUnlucky.SpecialUnluckyWater(player, block)));
        specialEvents.add(new EventEntry("SpecialUnluckyTNT", (block, player) -> SpecialUnlucky.SpecialUnluckyTNT(player, block)));
        specialEvents.add(new EventEntry("SpecialUnluckyDiamond", (block, player) -> SpecialUnlucky.SpecialUnluckyDiamond(player, block)));
        specialEvents.add(new EventEntry("SpecialUnluckyFlyingCreeper", (block, player) -> SpecialUnlucky.SpecialUnluckyFlyingCreeper(player, block)));
        specialEvents.add(new EventEntry("SpecialUnluckyFlyingTNT", (block, player) -> SpecialUnlucky.SpecialUnluckyFlyingTNT(player, block)));
        specialEvents.add(new EventEntry("SpecialUnluckyMuchasTNT", (block, player) -> SpecialUnlucky.SpecialUnluckyMuchasTNT(player, block)));
    }
}
