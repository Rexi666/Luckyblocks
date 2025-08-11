package org.rexi.luckyblocks.listeners;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SpongeAbsorbEvent;
import org.rexi.luckyblocks.events.normal.lucky.Cake;
import org.rexi.luckyblocks.events.normal.lucky.CookedFoodSurprise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class LuckyblockListener implements Listener {

    private final Random random = new Random();

    /* ####################################
    #               EVENTOS               #
    #################################### */

    private final List<BiConsumer<Block, Player>> normalEvents = new ArrayList<>();

    Cake NormalLuckyCake = new Cake();
    CookedFoodSurprise NormalLuckyCookedFood = new CookedFoodSurprise();

    public LuckyblockListener() {
        normalEvents.add((block, player) -> NormalLuckyCake.NormalLuckyCake(player));
        normalEvents.add((block, player) -> NormalLuckyCookedFood.NormalLuckyCookedFood(block));
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

    public void normalLuckyBlockBreak(Block block, Player player) {

        int randomIndex = random.nextInt(normalEvents.size());

        normalEvents.get(randomIndex).accept(block, player);
    }

    public void specialLuckyBlockBreak(Block block, Player player) {
        player.sendMessage("¡Has roto un Lucky Block especial!");
    }
}
