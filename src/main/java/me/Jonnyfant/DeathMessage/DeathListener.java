package me.Jonnyfant.DeathMessage;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class DeathListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        //dimension
        World.Environment env = p.getLocation().getWorld().getEnvironment();
        String dimension;
        if(env==World.Environment.NORMAL)
        {
            dimension="overworld";
        }
        else if(env==World.Environment.NETHER)
        {
            dimension="nether";
        }
        else if(env==World.Environment.THE_END)
        {
            dimension="end";
        }
        else
        {
            dimension=env.name();
        }

        String location = "X=" + p.getLocation().getBlockX() + ", Y=" + p.getLocation().getBlockY() + ", Z=" + p.getLocation().getBlockZ() + " in the "  + dimension;
        p.sendMessage("You died at " + location + ".");
        p.sendMessage("You have lost the following items:" + lostItemsString(p.getInventory()));
        helpMessage(p,location);
    }

    public void helpMessage(Player p, String deathLocation)
    {
        String message = ChatColor.BLUE + "Click here to share the location and ask others for help.";
        TextComponent component = new TextComponent(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
        String shareMessage="I died at " + deathLocation + ". Can you help me retrieve my items?";
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, shareMessage));

        p.spigot().sendMessage(component);
    }


    public String lostItemsString (Inventory inventory)
    {
        String lostItemsMessage = "";
        for (ItemStack itemStack : inventory.getContents()) {
            if(itemStack!=null) {
                lostItemsMessage += "\n";
                lostItemsMessage += itemStack.getAmount();
                lostItemsMessage += "  ";
                lostItemsMessage += itemStack.getType().name();
            }
        }
        if (lostItemsMessage == "") {
            lostItemsMessage += "\nNothing";
        }
        return lostItemsMessage;
    }
}