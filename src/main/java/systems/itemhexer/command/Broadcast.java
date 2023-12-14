package systems.itemhexer.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static systems.itemhexer.ItemHexer.mm;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) {
            sender.sendMessage(mm.deserialize("<red>Use '/broadcast <message>' to broadcast a message to the server!"));
            return true;
        }
        String message = String.join(" ", args);
        for (Player p : sender.getServer().getOnlinePlayers()) {
            p.sendMessage(mm.deserialize(message));
        }

        return true;
    }
}
