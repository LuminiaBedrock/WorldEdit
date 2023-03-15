package clubdev.worldedit;

import cn.nukkit.block.Block;
import cn.nukkit.level.Location;
import lombok.Getter;

public class WEBlock {
    
    @Getter private Block block;
    @Getter private Location location;

    public WEBlock(Block block, Location location) {
        this.block = block;
        this.location = location;
    }
}
