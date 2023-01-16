package me.skinnynoonie.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        Location playerLocation = event.getPlayer().getLocation();
        playerLocation.setY(-61.5);

        PlayerConnectionListener.mazeGeneratorArranger
                .get(event.getPlayer().getUniqueId().toString())
                .generateMaze(playerLocation);
    }

}
