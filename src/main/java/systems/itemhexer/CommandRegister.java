package systems.itemhexer;

import systems.itemhexer.command.AddLore;
import systems.itemhexer.command.Hex;
import systems.itemhexer.command.ItemName;

@SuppressWarnings("all")
public class CommandRegister {
    public CommandRegister(ItemHexer plugin) {
        plugin.getCommand("hex").setExecutor(new Hex());
        plugin.getCommand("itemname").setExecutor(new ItemName());
        plugin.getCommand("addlore").setExecutor(new AddLore());
    }
}
