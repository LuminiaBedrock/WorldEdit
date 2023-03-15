package clubdev.worldedit.commands;

import clubdev.worldedit.WE;
import clubdev.worldedit.managers.PositionManager;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandEnum;
import cn.nukkit.command.data.CommandParameter;

public class PositionCommand extends Command {

    private WE main;

    public PositionCommand(String name, WE main) {
        super("/" + name, "Установить позицию");
        this.setAliases(new String[]{"/pos1", "/pos2", "/1", "/2"});

        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[] {
            CommandParameter.newEnum("Args", new CommandEnum("Args", "1", "2"))
        });

        this.main = main;
    }
    
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Только в игре!");
            return false;
        }

        Player player = (Player) sender;

        int position = 0;

        if (label.equals("/pos1") || label.equals("/1")) {
            position = 1;
        } 
        else if (label.equals("/pos2") || label.equals("/2")) {
            position = 2;
        }

        else {
            if (args.length == 1) {
                if (args[0].equals("1") || args[0].equals("first")) {
                    position = 1;
                } 
                else if (args[0].equals("2") || args[0].equals("second")) {
                    position = 2;
                }
                else {
                    sender.sendMessage("Используйте: /" + label + " <1|2>");
                    return false;
                }
            }
            else {
                sender.sendMessage("Используйте: /" + label + " <1|2>");
                return false;
            }
        }

        if (position == 1) {
            sender.sendMessage(main.getPrefix() + "Первая позиция установлена. §7(" + (int) player.x + ", " + (int) player.y + ", " + (int) player.z + ")");
            PositionManager.getFirstPoint().put(player, player.getLocation());
        } else {
            sender.sendMessage(main.getPrefix() + "Вторая позиция установлена. §7(" + (int) player.x + ", " + (int) player.y + ", " + (int) player.z + ")");
            PositionManager.getSecondPoint().put(player, player.getLocation());
        }

        return true;
    }
}
