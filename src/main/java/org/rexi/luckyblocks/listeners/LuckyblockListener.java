package org.rexi.luckyblocks.listeners;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SpongeAbsorbEvent;
import org.rexi.luckyblocks.Luckyblocks;
import org.rexi.luckyblocks.events.normal.NormalLucky;
import org.rexi.luckyblocks.events.special.SpecialLucky;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

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
    private final SpecialLucky SpecialLucky;

    public LuckyblockListener(Luckyblocks plugin) {
        this.plugin = plugin;

        this.NormalLucky = new NormalLucky();
        this.SpecialLucky = new SpecialLucky(plugin);
        // Eventos normales
        normalEvents.add(new EventEntry("NormalLuckyCake", (block, player) -> NormalLucky.NormalLuckyCake(player)));
        normalEvents.add(new EventEntry("NormalLuckyCookedFood", (block, player) -> NormalLucky.NormalLuckyCookedFood(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyEmeraldBlock", (block, player) -> NormalLucky.NormalLuckyEmeraldBlock(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyIronBlock", (block, player) -> NormalLucky.NormalLuckyIronBlock(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyPotions", (block, player) -> NormalLucky.NormalLuckyPotions(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyCats", (block, player) -> NormalLucky.NormalLuckyCats(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyDogs", (block, player) -> NormalLucky.NormalLuckyDogs(player, block)));
        normalEvents.add(new EventEntry("NormalLuckyExperience", (block, player) -> NormalLucky.NormalLuckyExperience(player, block)));

        // Eventos especiales
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

    @EventHandler
    public void onLuckyBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        String blockType = block.getType().toString();
        if (blockType.equalsIgnoreCase("SPONGE")) {
            Player player = event.getPlayer();
            player.spawnParticle(Particle.HAPPY_VILLAGER, block.getLocation(), 50, 0.5, 0.5, 0.5, 0.1);
            player.playSound(player.getLocation(), Sound.BLOCK_CHAIN_PLACE, 1.0f, 1.0f);

            normalLuckyBlockBreak(block, player);
        } else if (blockType.equalsIgnoreCase("WET_SPONGE")) {
            Player player = event.getPlayer();
            player.spawnParticle(Particle.HAPPY_VILLAGER, block.getLocation(), 50, 0.5, 0.5, 0.5, 0.1);
            player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THROW, 1.0f, 1.0f);

            specialLuckyBlockBreak(block, player);
        }
    }

    @EventHandler
    public void BlockLuckyBlockChange(SpongeAbsorbEvent event) {
        event.setCancelled(true);
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
}
