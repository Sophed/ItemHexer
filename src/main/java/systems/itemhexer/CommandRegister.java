package systems.itemhexer;

import systems.itemhexer.command.*;

@SuppressWarnings("all")
public class CommandRegister {
    public CommandRegister(ItemHexer p) {
        p.getCommand("hex").setExecutor(new Hex());
        p.getCommand("itemname").setExecutor(new ItemName());
        p.getCommand("addlore").setExecutor(new AddLore());
        p.getCommand("resetlore").setExecutor(new ResetLore());
        p.getCommand("setline").setExecutor(new SetLoreLine());
        p.getCommand("deleteline").setExecutor(new DeleteLoreLine());
        p.getCommand("broadcast").setExecutor(new Broadcast());
    }
}
