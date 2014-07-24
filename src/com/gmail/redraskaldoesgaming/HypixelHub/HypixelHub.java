//Imports
package com.gmail.redraskaldoesgaming.HypixelHub;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
	 
public final class HypixelHub extends JavaPlugin implements Listener{
	public static HypixelHub plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	private List<Player> cantdocommand = new ArrayList<Player>();
	
	Countdown d = new Countdown();
	 
	//Methods
	@Override
    public void onEnable(){
		Bukkit.broadcastMessage("HypixelHub is now enabled!");
		getLogger().info("HypixelHub is now enabled!");
    	getConfig().options().copyDefaults(true);
    	saveConfig();
    }
 
    @Override
    public void onDisable() {
		Bukkit.broadcastMessage("HypixelHub is now disabled!");
		getLogger().info("HypixelHub is now disabled!");
    }
    
    //Commands
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	Player player = (Player) sender;
    	if(commandLabel.equalsIgnoreCase("welcome")){
    	if(player.hasPermission("hypixelhub.player.welcome"));
    	String Playerm = getConfig().getString("BarMessage");
    	String barcolor = getConfig().getString("BarColor");
    	BarAPI.setMessage(player, barcolor + Playerm, 3);
    	} else{
    		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
    	}
    	if(commandLabel.equalsIgnoreCase("firework")){
    		if (player.hasPermission("hypixelhub.vip.firework"));
    		if (!cantdocommand.contains(player)) {
    		shootFireworks();
    		cantdocommand.add(player);
    		d.setList(cantdocommand);
    		d.setPlayer(player);
    		new Thread(d).start();
    		} else{
    		if(cantdocommand.contains(player)) {
        		player.sendMessage(ChatColor.RED + "Please wait 15 seconds before launching another firework");
    		} else{
    			player.sendMessage(ChatColor.RED + "You don't have permission to launch a firework!");
    		}
    	}
    }
    	if(commandLabel.equalsIgnoreCase("fw")){
    		if(!cantdocommand.contains(player)) {
    		shootFireworks();
    		cantdocommand.add(player);
    		d.setList(cantdocommand);
    		d.setPlayer(player);
    		new Thread(d).start();
    		} else{
    		if(cantdocommand.contains(player)) {
        		player.sendMessage(ChatColor.RED + "Please wait 15 seconds before launching another firework");
    			}
    		}
    	}
    	return false;
    }
    //Firework Shooting variable shootFireworks()
    private void shootFireworks() {
    	for (Player player : Bukkit.getOnlinePlayers()) {
    	Firework fw = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
    	FireworkMeta fm = fw.getFireworkMeta();
    	Random r = new Random();
    	int fType = r.nextInt(4) + 1;
    	Type type = null;
    	switch (fType) {
    	default:
    	case 1:
    		type = type.BALL;
    		break;
    		
    	case 2:
    		type = type.BALL_LARGE;
    		break;
    	case 3:
    		type = type.BURST;
    		break;
    	case 4:
    		type = type.CREEPER;
    		break;
    	case 5:
    		type = type.STAR;
    		break;
    		}
    	int c1i = r.nextInt(17) + 1;
    	int c2i = r.nextInt(17) + 1;
    	Color c1 = getColour(c1i);
    	Color c2 = getColour(c2i);
    	FireworkEffect effect = FireworkEffect.builder()
    			.flicker(r.nextBoolean()).withColor(c1).withFade(c2)
    			.with(type).build();
    	fm.addEffect(effect);
    	int power = r.nextInt(2)+1;
    	fm.setPower(power);
    	fw.setFireworkMeta(fm);
    	}
    }
    	
    	public Color getColour(int c) {
    		switch (c) {
    		default:
    		case 1:return Color.AQUA;
    		case 2:return Color.BLACK;
    		case 3:return Color.BLUE;
    		case 4:return Color.FUCHSIA;
    		case 5:return Color.GRAY;
    		case 6:return Color.GREEN;	
    		case 7:return Color.LIME;	
    		case 8:return Color.MAROON;
    		case 9:return Color.NAVY;	
    		case 10:return Color.OLIVE;
    		case 11:return Color.ORANGE;
    		case 12:return Color.PURPLE;
    		case 13:return Color.RED;
    		case 14:return Color.SILVER;
    		case 15:return Color.TEAL;
    		case 16:return Color.WHITE;
    		case 17:return Color.YELLOW;
    		}
    	}
	
	//Listeners
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
    	Player player = event.getPlayer();
    	player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999, 3));
    	player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999, 255));
    	player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999, 255));
    	player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 3));
    	String Playerm = getConfig().getString("BarMessage");
    	BarAPI.setMessage(ChatColor.BLUE + Playerm + ChatColor.GOLD + player, 3);
    }
}