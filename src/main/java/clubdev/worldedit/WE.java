package clubdev.worldedit;

import cn.nukkit.plugin.PluginBase;
import clubdev.worldedit.commands.*;
import clubdev.worldedit.listeners.EventListener;
import clubdev.worldedit.managers.WorldEditManager;
import cn.nukkit.command.SimpleCommandMap;
import lombok.Getter;

public class WE extends PluginBase {
    
    @Getter private static WE instance;

    @Getter private WorldEditManager WEManager;

    @Override
    public void onEnable() {
        instance = this;

        this.WEManager = new WorldEditManager(this);

        this.saveDefaultConfig();
        this.register();
    }

    public void register() {
        SimpleCommandMap map = this.getServer().getCommandMap();
        map.register("WorldEdit", new WandCommand("wand"));
        map.register("WorldEdit", new PositionCommand("pos", this));
        map.register("WorldEdit", new SetCommand("set", this));
        map.register("WorldEdit", new CopyCommand("copy", this));
        map.register("WorldEdit", new PasteCommand("paste", this));

        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }

    public String getPrefix() {
        return this.getConfig().getString("prefix");
    }
}
