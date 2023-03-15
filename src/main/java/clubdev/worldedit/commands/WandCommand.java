package clubdev.worldedit.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.StringTag;

public class WandCommand extends Command {

    public WandCommand(String name) {
        super("/" + name, "Получить топор для выделения позиции");
        this.commandParameters.clear();
    }
    
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Только в игре!");
            return false;
        }

        Player player = (Player) sender;

        Item item = Item.get(Item.WOODEN_AXE);
        
        item.setNamedTag(new CompoundTag().put("WEAxe", new StringTag("WEAxe", "")));
        item.setCustomName("§r§aТопор для выделения");
        item.setLore("§7Левый клик - первая позиция.", "§7Правый клик - вторая позиция.");
        item.addEnchantment(Enchantment.getEnchantment(Enchantment.ID_DURABILITY));

        player.getInventory().addItem(item);

        return true;
    }
}
