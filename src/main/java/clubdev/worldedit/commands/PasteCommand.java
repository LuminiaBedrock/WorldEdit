package clubdev.worldedit.commands;

import clubdev.worldedit.WE;
import clubdev.worldedit.WEBlock;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import java.util.List;

public class PasteCommand extends Command {

    private WE main;

    public PasteCommand(String name, WE main) {
        super("/" + name, "Скопировать выделеную область");

        this.commandParameters.clear();
        /*this.commandParameters.put("default", new CommandParameter[] {
            CommandParameter.newEnum("Args", new CommandEnum("Args", "1", "2"))
        });*/

        this.main = main;
    }
    
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Только в игре!");
            return false;
        }

        Player player = (Player) sender;

        if (!main.getWEManager().getBlockArray().containsKey(player)) {
            player.sendMessage(main.getPrefix() + "Для начала используйте //copy");
            return false;
        }

        List<WEBlock> array = main.getWEManager().getBlockArray().get(player);

        main.getWEManager().pasteArray(player, array);
        player.sendMessage(main.getPrefix() + "Успешно вставлено " + array.size() + " блоков.");
        return true;
    }
}
