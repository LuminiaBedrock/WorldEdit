package clubdev.worldedit.managers;

import java.util.HashMap;

import cn.nukkit.Player;
import cn.nukkit.level.Location;
import lombok.Getter;

public class PositionManager {
    
    @Getter public static HashMap<Player, Location> firstPoint = new HashMap<>();
    @Getter public static HashMap<Player, Location> secondPoint = new HashMap<>();

    public static boolean hasFirstPoint(Player player) {
        return firstPoint.containsKey(player);
    }

    public static boolean hasSecondPoint(Player player) {
        return secondPoint.containsKey(player);
    }

    public static Location getFirstPoint(Player player) {
        return firstPoint.get(player);
    }

    public static Location getSecondPoint(Player player) {
        return secondPoint.get(player);
    }
}
