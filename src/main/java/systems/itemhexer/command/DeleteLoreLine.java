package systems.itemhexer.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static systems.itemhexer.ItemHexer.*;

public class DeleteLoreLine implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(mm.deserialize("<red>Only players can use this command."));
            return true;
        }
        if (args.length == 0) {
            player.sendMessage(mm.deserialize(ERROR + "Use '/deleteline <line>' to remove a line of lore on your items!"));
            return true;
        }

        String lineStr = args[0];
        int line;
        try {
            line = Integer.parseInt(lineStr);
        } catch (NumberFormatException e) {
            player.sendMessage(mm.deserialize(ERROR + "Please enter a valid number."));
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (item.getType().isAir()) {
            player.sendMessage(mm.deserialize(ERROR + "You can't delete the lore of air!"));
            return true;
        }
        if (!meta.hasLore()) {
            player.sendMessage(mm.deserialize(ERROR + "This item doesn't have any lore!"));
            return true;
        }

        List<Component> lore = meta.lore();
        if (lore.get(line - 1) == null) {
            player.sendMessage(mm.deserialize(ERROR + "This line doesn't exist!"));
            return true;
        }
        lore.set(line - 1, null);

        meta.lore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItemInMainHand(item);
        player.sendMessage(mm.deserialize(ACCENT + "Removed line: <reset><gray>" + lineStr));
        return true;
    }
}
