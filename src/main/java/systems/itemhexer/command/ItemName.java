package systems.itemhexer.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import static systems.itemhexer.ItemHexer.*;

public class ItemName implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(mm.deserialize("<red>Only players can use this command."));
            return true;
        }
        if (args.length == 0) {
            player.sendMessage(mm.deserialize(ERROR + "Use '/itemname <name>' to rename your items!"));
            player.sendMessage(mm.deserialize(FG + "For colors, hex codes and text decoration, use <underlined><hover:show_text:'" + ACCENT + "Click to read documentation!'><click:open_url:'https://docs.advntr.dev/minimessage/format.html#minimessage-format'>MiniMessage</click></hover></underlined> formatting."));
            return true;
        }

        String nameStr = String.join(" ", args);
        Component name = mm.deserialize(nameStr);
        String rawStr = PlainTextComponentSerializer.plainText().serialize(name);

        if (rawStr.length() > 48) {
            player.sendMessage(mm.deserialize(ERROR + "This name is too long! Please use a name with less than 48 characters (not including formatting)."));
            return true;
        }
        if (filter(rawStr)) {
            player.sendMessage(mm.deserialize(ERROR + "This item name contains a blacklisted word/phrase."));
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().isAir()) {
            player.sendMessage(mm.deserialize(ERROR + "You can't rename air!"));
            return true;
        }
        ItemMeta meta = item.getItemMeta();
        meta.displayName(mm.deserialize("<!italic>").append(name));
        item.setItemMeta(meta);
        player.getInventory().setItemInMainHand(item);

        sender.sendMessage(mm.deserialize(ACCENT + "Set item name: <reset><gray>").append(name));
        return true;
    }
}
