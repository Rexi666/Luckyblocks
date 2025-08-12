package org.rexi.luckyblocks.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.rexi.luckyblocks.listeners.LuckyblockListener;

public class LuckyblockTestCommand implements CommandExecutor {

    private final LuckyblockListener listener;

    public LuckyblockTestCommand(LuckyblockListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("Este comando solo lo puede usar un jugador.").color(NamedTextColor.RED));
            return true;
        }

        if (!player.hasPermission("luckyblocks.test")) {
            player.sendMessage(Component.text("No tienes permiso para usar este comando.").color(NamedTextColor.RED));
            return true;
        }

        if (args.length != 2) {
            player.sendMessage(Component.text("Uso: /luckytest <normal|special> <id> o /luckytest list <normal|special>")
                    .color(NamedTextColor.YELLOW));
            return true;
        }

        // Listar eventos
        if (args[0].equalsIgnoreCase("list")) {
            if (args[1].equalsIgnoreCase("normal")) {
                player.sendMessage(Component.text("Eventos normales:").color(NamedTextColor.GOLD));
                for (int i = 0; i < listener.getNormalEvents().size(); i++) {
                    player.sendMessage(Component.text("[" + i + "] " + listener.getNormalEvents().get(i).name())
                            .color(NamedTextColor.YELLOW));
                }
            } else if (args[1].equalsIgnoreCase("special")) {
                player.sendMessage(Component.text("Eventos especiales:").color(NamedTextColor.GOLD));
                for (int i = 0; i < listener.getSpecialEvents().size(); i++) {
                    player.sendMessage(Component.text("[" + i + "] " + listener.getSpecialEvents().get(i).name())
                            .color(NamedTextColor.YELLOW));
                }
            } else {
                player.sendMessage(Component.text("Tipo desconocido. Usa normal o special").color(NamedTextColor.RED));
            }
            return true;
        }

        // Ejecutar evento por ID
        String type = args[0].toLowerCase();
        int id;

        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(Component.text("El ID debe ser un número").color(NamedTextColor.RED));
            return true;
        }

        Block block = player.getLocation().getBlock();

        if (type.equals("normal")) {
            if (id < 0 || id >= listener.getNormalEvents().size()) {
                player.sendMessage(Component.text("ID fuera de rango. Máximo: " + (listener.getNormalEvents().size() - 1)).color(NamedTextColor.RED));
                return true;
            }
            listener.getNormalEvents().get(id).action().accept(block, player);
            player.sendMessage(Component.text("Evento normal ejecutado: " + id).color(NamedTextColor.GREEN));
        } else if (type.equals("special")) {
            if (id < 0 || id >= listener.getSpecialEvents().size()) {
                player.sendMessage(Component.text("ID fuera de rango. Máximo: " + (listener.getSpecialEvents().size() - 1)).color(NamedTextColor.RED));
                return true;
            }
            listener.getSpecialEvents().get(id).action().accept(block, player);
            player.sendMessage(Component.text("Evento especial ejecutado: " + id).color(NamedTextColor.GREEN));
        } else {
            player.sendMessage(Component.text("Tipo desconocido. Usa normal o special").color(NamedTextColor.RED));
        }

        return true;
    }
}