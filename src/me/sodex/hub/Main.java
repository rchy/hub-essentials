package me.sodex.hub;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	private static Plugin plugin;
	
	public void onEnable(){
		
		plugin = this;
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		if(!ConfigMessages.checkConfig())
			ConfigMessages.createConfig();
		ConfigMessages.loadConfig();
		ConfigMessages.filterConfig();
		ConfigMessages.loadCompassPoints();
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CompassInfo(), this);
		
		if(ConfigMessages.NO_HEALTH_LOSS)
			Bukkit.getServer().getPluginManager().registerEvents(new NoHealth(), this);
		
		if(ConfigMessages.NO_HUNGER_LOSS)
			Bukkit.getServer().getPluginManager().registerEvents(new NoHunger(), this);
		
	}
	
	public void onDisable(){
		plugin = null;
	}
	
	public static Plugin getPlugin(){
		return plugin;
	}
	
}
