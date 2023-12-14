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

import java.util.ArrayList;
import java.util.List;

import static systems.itemhexer.ItemHexer.*;

public class AddLore implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(mm.deserialize("<red>Only players can use this command."));
            return true;
        }
        if (args.length == 0) {
            player.sendMessage(mm.deserialize(ERROR + "Use '/addlore <text>' to add lore to your items!"));
            player.sendMessage(mm.deserialize(FG + "For colors, hex codes and text decoration, use <underlined><hover:show_text:'" + ACCENT + "Click to read documentation!'><click:open_url:'https://docs.advntr.dev/minimessage/format.html#minimessage-format'>MiniMessage</click></hover></underlined> formatting."));
            return true;
        }

        String loreStr = String.join(" ", args);
        Component loreCom = mm.deserialize(loreStr);
        String rawLore = PlainTextComponentSerializer.plainText().serialize(loreCom);
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType().isAir()) {
            player.sendMessage(mm.deserialize(ERROR + "You can't add lore to air!"));
            return true;
        }
        if (rawLore.length() > 64) {
            player.sendMessage(mm.deserialize(ERROR + "This line is too long! Please limit lore entries to less than 64 characters (not including formatting)."));
            return true;
        }
        if (filter(rawLore)) {
            player.sendMessage(mm.deserialize(ERROR + "This item name contains a blacklisted word/phrase."));
            return true;
        }

        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        if (meta.hasLore()) {
            lore = meta.lore();
        }
        if (lore != null) {
            lore.add(mm.deserialize("<!italic>").append(loreCom));
        }
        meta.lore(lore);
        item.setItemMeta(meta);
        player.getInventory().setItemInMainHand(item);
        player.sendMessage(mm.deserialize(ACCENT + "Added lore: <reset><gray>").append(loreCom));
        return true;
    }
}
