package clubdev.worldedit.commands;

import java.util.List;

import clubdev.worldedit.WE;
import clubdev.worldedit.WEBlock;
import clubdev.worldedit.managers.PositionManager;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class CopyCommand extends Command {

    private WE main;

    public CopyCommand(String name, WE main) {
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

        if (!PositionManager.hasFirstPoint(player) || !PositionManager.hasSecondPoint(player)) {
            player.sendMessage(main.getPrefix() + "Для начала используйте //pos <1|2>.");
            return false;
        }

        List<WEBlock> array = main.getWEManager().toArray(player, PositionManager.getFirstPoint(player), PositionManager.getSecondPoint(player));

        main.getWEManager().getBlockArray().put(player, array);
        player.sendMessage(main.getPrefix() + "Скопировано " + array.size() + " блоков. \nИспользуйте: //paste, чтобы выставить");
        return true;
    }
}
