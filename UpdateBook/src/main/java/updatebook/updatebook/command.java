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
            if (args.length <= 0) {
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("Update.permission.admin")) {
                    String WorldOnMessage = plugin.getConfig().getString("Update.WorldOnMessage");
                    if (WorldOnMessage == null) return true;
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', WorldOnMessage));
                    plugin.reloadConfig();
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("open")) {
                if (args.length <= 1) {
                    return true;
                }
                if (args[1].equalsIgnoreCase(args[1])) {
                    for (String key : plugin.getConfig().getConfigurationSection("Update").getKeys(false)) {
                        ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
                        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                        if (bookMeta == null) return true;
                        List<String> Page = plugin.getConfig().getStringList("Update." + key + ".List");
                        for (String P : Page) {
                            bookMeta.addPage(ChatColor.translateAlternateColorCodes('&', P));
                        }
                        if (key.equalsIgnoreCase(args[1])) {
                            bookMeta.setTitle("LIFE");
                            bookMeta.setAuthor("46kuri_");
                            itemStack.setItemMeta(bookMeta);
                            p.openBook(itemStack);
                        }
                    }
                    return true;
                }
                return true;
            }
        }
        return true;
    }
}
