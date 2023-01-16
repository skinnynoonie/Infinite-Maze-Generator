package me.skinnynoonie.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Bukkit.broadcastMessage(event.getPlayer().getName() + ": " + event.getMessage());
        event.setCancelled(true);
    }

}
