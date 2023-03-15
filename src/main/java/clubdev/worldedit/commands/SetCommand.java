package clubdev.worldedit.commands;

import clubdev.worldedit.WE;
import clubdev.worldedit.managers.PositionManager;
import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class SetCommand extends Command {

    private WE main;

    public SetCommand(String name, WE main) {
        super("/" + name, "Сет");

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

        if (args.length != 1) {
            player.sendMessage("Используйте: /" + label + " <id блока>");
            return false;   
        }

        if (!PositionManager.hasFirstPoint(player) || !PositionManager.hasSecondPoint(player)) {
            player.sendMessage(main.getPrefix() + "Для начала используйте //pos <1|2>.");
            return false;
        }

        Block block = Block.get(Integer.parseInt(args[0]));
        main.getWEManager().set(PositionManager.getFirstPoint(player), PositionManager.getSecondPoint(player), block);

        return true;
    }
}
