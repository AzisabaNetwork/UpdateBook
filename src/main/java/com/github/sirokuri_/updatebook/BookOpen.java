package com.github.sirokuri_.updatebook;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.Timer;
import java.util.TimerTask;

public class BookOpen implements Listener {

    private final UpdateBook plugin;

    public BookOpen(UpdateBook UpdateBook){
        this.plugin = UpdateBook;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        String JoinMessage1 = plugin.getConfig().getString("Update.JoinMessage1");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',JoinMessage1));
        String JoinMessage2 = plugin.getConfig().getString("Update.JoinMessage2");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',JoinMessage2));
        Timer timer = new Timer(false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',JoinMessage1));
                timer.cancel();
            }
        };
        timer.schedule(task, 7000);
    }
}