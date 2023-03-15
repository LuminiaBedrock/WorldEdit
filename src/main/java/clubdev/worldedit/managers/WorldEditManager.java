package clubdev.worldedit.managers;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import clubdev.worldedit.WE;
import clubdev.worldedit.WEBlock;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

public class WorldEditManager {
    
    private WE main;

    @Getter public HashMap<Player, List<WEBlock>> blockArray = new HashMap<>();

    public WorldEditManager(WE main) {
        this.main = main;
    }

    public List<WEBlock> toArray(Location center, Location firstPos, Location secondPos) {

        int minX = (int) Math.min(firstPos.x, secondPos.x);
        int minY = (int) Math.min(firstPos.y, secondPos.y);
        int minZ = (int) Math.min(firstPos.z, secondPos.z);
        int maxX = (int) Math.max(firstPos.x, secondPos.x);
        int maxY = (int) Math.max(firstPos.y, secondPos.y);
        int maxZ = (int) Math.max(firstPos.z, secondPos.z);

        List<WEBlock> blocks = new ArrayList<>();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    int id = center.getLevel().getBlockIdAt(x, y, z);

                    if (id == 0) {
                        continue;
                    }

                    Block block = center.getLevel().getBlock(new Vector3(x, y, z));

                    if (block != null) {
                        block.setComponents(x - center.getFloorX(), y - center.getFloorY(), z - center.getFloorZ());
                        blocks.add(new WEBlock(block, new Location(x, y, z)));
                    }
                }
            }
        }
        return blocks;
    }

    public void pasteArray(Location center, List<WEBlock> array) {
        var executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            var subExecutor = Executors.newCachedThreadPool();
            Vector3 vector3 = new Vector3();
            for (WEBlock weblock : array) {
                Block block = weblock.getBlock();
                vector3.setComponents(center.getFloorX() + block.x, center.getFloorY() + block.y, center.getFloorZ() + block.z);
                center.getLevel().setBlock(vector3, weblock.getBlock(), true, false);
            }   
            subExecutor.shutdown();
        });
        executor.shutdown();
    }

    public void set(Location firstPos, Location secondPos, Block block) {
        var executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            
            double x1 = Math.min(firstPos.x, secondPos.x);
            double x2 = Math.max(firstPos.x, secondPos.x);
            double y1 = Math.min(firstPos.y, secondPos.y); 
            double y2 = Math.max(firstPos.y, secondPos.y);
            double z1 = Math.min(firstPos.z, secondPos.z); 
            double z2 = Math.max(firstPos.z, secondPos.z);

            var subExecutor = Executors.newCachedThreadPool();
            for (int z = (int) z1; z <= z2; z++) {
                for (int x = (int) x1; x <= x2; x++) {
                    for (int y = (int) y1; y <= y2; y++) {
                        final int fx = x, fy = y, fz = z;
                        subExecutor.execute(() -> firstPos.getLevel().setBlock(new Vector3(fx, fy, fz), block, true, true));
                    }
                }
            }
            subExecutor.shutdown();
        });
        executor.shutdown();
    }
}
