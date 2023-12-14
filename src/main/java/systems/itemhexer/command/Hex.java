package systems.itemhexer.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import systems.itemhexer.ItemHexer;

import java.util.List;

import static systems.itemhexer.ItemHexer.*;

public class Hex implements CommandExecutor, TabExecutor {
    private static final String RELOAD_PERMISSION = "hex.reload";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(mm.deserialize("<red>Only players can use this command."));
            return true;
        }
        if (args.length == 0) {
            player.sendMessage(mm.deserialize(ACCENT + "<bold>ItemHexer " + VERSION + "</bold> by <underlined><hover:show_text:'" + ACCENT + "Click open my site!'><click:open_url:'https://soph.systems'>Sophed</click></hover></underlined>"));
            player.sendMessage(mm.deserialize(FG + "Edit your item names and lore easily!"));
            player.sendMessage(mm.deserialize("<gray><underlined>https://github.com/Sophed/ItemHexer"));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission(RELOAD_PERMISSION)) {
                ItemHexer.getPlugin(ItemHexer.class).reloadConfig();
                blockList = ItemHexer.getPlugin(ItemHexer.class).getConfig().getStringList("BlockedWords");
                sender.sendMessage(mm.deserialize(FG + "Config reloaded!"));
            } else {
                sender.sendMessage(mm.deserialize(ERROR + "You don't have permission for this."));
            }
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 1) {
            return List.of("reload");
        }
        return null;
    }
}
