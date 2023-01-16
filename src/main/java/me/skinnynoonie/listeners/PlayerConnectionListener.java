package me.skinnynoonie.listeners;

import me.skinnynoonie.mazegenerator.MazeGenerator;
import me.skinnynoonie.miniconfig.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class PlayerConnectionListener implements Listener {

    public static HashMap<String, MazeGenerator> mazeGeneratorArranger = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if(!event.getPlayer().hasPlayedBefore())
            event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, -57, 0.5));

        String playerUUID = event.getPlayer().getUniqueId().toString();

        MazeGenerator mazeGenerator = new MazeGenerator()
                .setWallEnclosure(Config.WALL_ENCLOSURE)
                .setHeight(Config.WALL_HEIGHT)
                .setRadius(Config.MAZE_GENERATION_RADIUS)
                .setWallBlockType(Config.WALL_BLOCK_TYPE);

        mazeGeneratorArranger.put(playerUUID, mazeGenerator);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        String playerUUID = event.getPlayer().getUniqueId().toString();
        mazeGeneratorArranger.remove(playerUUID);
    }

}
