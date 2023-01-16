package me.skinnynoonie;

import me.skinnynoonie.commands.ConfigCommand;
import me.skinnynoonie.listeners.BlockListener;
import me.skinnynoonie.listeners.ChatListener;
import me.skinnynoonie.listeners.MovementListener;
import me.skinnynoonie.listeners.PlayerConnectionListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new MovementListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginCommand("config").setExecutor(new ConfigCommand());

    }

}
