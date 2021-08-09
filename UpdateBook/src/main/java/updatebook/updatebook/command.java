package updatebook.updatebook;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import java.util.List;

public class command implements CommandExecutor {

    private final UpdateBook plugin;

    public command(UpdateBook UpdateBook) {
        this.plugin = UpdateBook;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("update")) {
            if (args.length <= 0) return false;
            if (args[0].equalsIgnoreCase("reload")) {
                //OP以外起動しないように設定
                if (!sender.hasPermission("Update.admin.open")) {
                    sender.sendMessage("コマンドを実行出来る権限がありません。");
                    return true;
                }
                plugin.reloadConfig();
                sender.sendMessage("UpdatePlugin reload complete!");
                String WorldOnMessage = plugin.getConfig().getString("Update.WorldOnMessage");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',WorldOnMessage));
                }

            }
            if (args[0].equalsIgnoreCase("open")) {
                if (args.length <= 1) {
                    return false;
                }
                if(args[1].equalsIgnoreCase(args[1])){
                    for(String key : plugin.getConfig().getConfigurationSection("Update").getKeys(false)) {
                        ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
                        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                        List<String> Page = plugin.getConfig().getStringList("Update." + key + ".List");
                        for (String P : Page){
                            bookMeta.addPage(ChatColor.translateAlternateColorCodes('&', P));
                        }
                        if(key.equalsIgnoreCase(args[1])){
                            bookMeta.setTitle("LIFE");
                            bookMeta.setAuthor("46kuri_");
                            itemStack.setItemMeta(bookMeta);
                            p.openBook(itemStack);
                        }
                    }
                }
            }
        }
        return true;
    }
}
