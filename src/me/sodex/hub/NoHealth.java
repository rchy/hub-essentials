package me.sodex.hub;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoHealth implements Listener{

	@EventHandler
	public void health(EntityDamageEvent e){
		
		e.setDamage(0);
		
	}
	
}
