package com.gmail.redraskaldoesgaming.HypixelHub;

import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

public class VIPInventory extends JavaPlugin implements Listener {
	
	public static Inventory vip;

	@Override
    public void onEnable(){
		Bukkit.createInventory((InventoryHolder)vip, 9, "VIP Inventory");
    }
 
    @Override
    public void onDisable() {
    	
    }
    @Override
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        	Player player = (Player) sender;
        	if(commandLabel.equalsIgnoreCase("vip")){
        	if(player.hasPermission("hypixelhub.vip.inventory"));
        	player.openInventory(vip);
        	BarAPI.setMessage(player, ChatColor.GREEN + "Opened Inventory!", 3);
        	
        	}else {
            	BarAPI.setMessage(player, ChatColor.RED +  "You are not vip!", 3);
        	
    	}
    return false;
	}
}