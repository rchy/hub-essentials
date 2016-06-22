package me.sodex.hub;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void join(PlayerJoinEvent e){
		
		String temp_join = ConfigMessages.JOIN_MESSAGE.replace("%USERNAME%", e.getPlayer().getName());
		
		e.setJoinMessage(temp_join);
		
		if(ConfigMessages.SEND_TITLE){
			
			e.getPlayer().sendTitle(ChatColor.RED + "Welcome to:", ChatColor.GREEN + ConfigMessages.SERVER_NAME);
			
		}
		
		if(ConfigMessages.LAUNCH_FIREWORK){
			
			JoinEffects.launchRandomFirework(e.getPlayer());
			
		}
		
		if(ConfigMessages.CLEAR_INV){
			
			e.getPlayer().getInventory().clear();
			
		}
		
		if(ConfigMessages.GIVE_COMPASS){
		
			e.getPlayer().getInventory().setItem(4, CompassInfo.getCompass());
			
		}
		
	}
	
	@EventHandler
	public void throwItem(PlayerDropItemEvent e){
		if(ConfigMessages.NO_DROPS){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void leave(PlayerQuitEvent e){
		
		String temp_leave = ConfigMessages.LEAVE_MESSAGE.replace("%USERNAME%", e.getPlayer().getName());
		
		e.setQuitMessage(temp_leave);
		
	}
	
}
