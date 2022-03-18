package me.Jonnyfant.DeathMessage;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathMessage extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DeathListener(),this);
    }
}
