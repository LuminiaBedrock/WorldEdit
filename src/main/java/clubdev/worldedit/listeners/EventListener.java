package clubdev.worldedit.listeners;

import clubdev.worldedit.WE;
import clubdev.worldedit.managers.PositionManager;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerInteractEvent.Action;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;

public class EventListener implements Listener {
    
    private WE main;

    public EventListener(WE main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Item item = event.getItem();
        Location location = event.getBlock().getLocation();
        
        if (item.getNamedTag() != null && item.getNamedTag().contains("WEAxe")) {
            event.setCancelled();
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (PositionManager.hasFirstPoint(player) && PositionManager.getFirstPoint().get(player).equals(location)) {
                    return;
                }
                player.sendMessage(main.getPrefix() + "Первая позиция установлена. §7(" + (int) location.x + ", " + (int) location.y + ", " + (int) location.z + ")");
                PositionManager.getFirstPoint().put(player, location);
            }
            else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (PositionManager.hasSecondPoint(player) && PositionManager.getSecondPoint(player).equals(location)) {
                    return;
                }
                player.sendMessage(main.getPrefix() + "Вторая позиция установлена. §7(" + (int) location.x + ", " + (int) location.y + ", " + (int) location.z + ")");
                PositionManager.getSecondPoint().put(player, location);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Item item = event.getItem();
        Location location = event.getBlock().getLocation();
        
        if (item.getNamedTag() != null && item.getNamedTag().contains("WEAxe")) {
            event.setCancelled();
            if (PositionManager.hasFirstPoint(player) && PositionManager.getFirstPoint().get(player).equals(location)) {
                return;    
            }
            player.sendMessage(main.getPrefix() + "Первая позиция установлена. §7(" + (int) location.x + ", " + (int) location.y + ", " + (int) location.z + ")");
            PositionManager.getFirstPoint().put(player, location);
        }
    }
}
