package org.rexi.luckyblocks;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.rexi.luckyblocks.listeners.LuckyblockListener;

public final class Luckyblocks extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new LuckyblockListener(), this);

        Bukkit.getConsoleSender().sendMessage(Component.text("Luckyblocks ha sido activado").color(NamedTextColor.GREEN));
        Bukkit.getConsoleSender().sendMessage(Component.text("Gracias por usar plugins de Rexi666 :D").color(NamedTextColor.BLUE));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Component.text("Luckyblocks ha sido desactivado").color(NamedTextColor.RED));
        Bukkit.getConsoleSender().sendMessage(Component.text("Gracias por usar plugins de Rexi666 :D").color(NamedTextColor.BLUE));
    }
}
