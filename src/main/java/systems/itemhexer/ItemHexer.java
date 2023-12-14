package systems.itemhexer;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/*
  ██ ████████ ███████ ███    ███   ██   ██ ███████ ██   ██ ███████ ██████
  ██    ██    ██      ████  ████   ██   ██ ██       ██ ██  ██      ██   ██
  ██    ██    █████   ██ ████ ██   ███████ █████     ███   █████   ██████
  ██    ██    ██      ██  ██  ██   ██   ██ ██       ██ ██  ██      ██   ██
  ██    ██    ███████ ██      ██   ██   ██ ███████ ██   ██ ███████ ██   ██
  - By Sophed (https://soph.systems)
*/

@SuppressWarnings("all")
public final class ItemHexer extends JavaPlugin {

    public static String VERSION = "1.0.1";
    public static MiniMessage mm = MiniMessage.miniMessage();
    public static final String FG = "<#00B5FF>";
    public static final String ACCENT = "<#3064FF>";
    public static final String ERROR = "<#FF0036>";
    public static List<String> blockList;

    @Override
    public void onEnable() {
        CommandSender console = getServer().getConsoleSender();
        console.sendMessage(mm.deserialize(ACCENT + "<strikethrough>                                      <reset>"));
        console.sendMessage(mm.deserialize(ACCENT + "ItemHexer " + VERSION + " has been <bold>enabled!</bold>"));
        console.sendMessage(mm.deserialize(ACCENT + "<strikethrough>                                      <reset>"));

        saveDefaultConfig();
        blockList = getConfig().getStringList("BlockedWords");

        new CommandRegister(this);

    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(mm.deserialize(ACCENT + "ItemHexer " + VERSION + " has been <bold>disabled.</bold>"));
    }
    public static boolean filter(String message) {
        for (String word : blockList) {
            if (message.contains(word)) {
                return true;
            }
        }
        return false;
    }
}
