package com.gmail.redraskaldoesgaming.HypixelHub;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Countdown implements Runnable {
	
	public Player player1 = null;
    public List<Player> cantdocommand1 = new ArrayList<Player>();
    
    public void setPlayer(Player player){
    	this.player1 = player;
    }
    
    public void setList(List<Player> list) {
    	this.cantdocommand1 = list;
    }
    public List<Player> getList() {
    return cantdocommand1;
	}
    
    public void run() {
    	try {
    		player1.sendMessage(ChatColor.GREEN + "You launched a firework!");
    		Thread.sleep(15000);
    		cantdocommand1.remove(player1);
    	} catch (Exception ignored) {
    	}
    }
}