package updatebook.updatebook;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class UpdateBook extends JavaPlugin implements Listener {

    public BukkitRunnable task = null;

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new BookOpen(this), this);
        getCommand("update").setExecutor(new command(this));
        saveDefaultConfig();
        startTimer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void startTimer() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"---------------------\n&l鯖内パッチノートは\n&r&a/update open 1から5の数値 \n&fを入力すると見れます\n---------------------"));
            }
        };
        task.runTaskTimer(this, 0, 6000);
    }
}
