package me.sodex.hub;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import net.md_5.bungee.api.ChatColor;

public class ConfigMessages {

	public static String SERVER_NAME;
	public static String JOIN_MESSAGE;
	public static String LEAVE_MESSAGE;
	public static boolean LAUNCH_FIREWORK;
	public static boolean GIVE_COMPASS;
	public static boolean SEND_TITLE;
	public static boolean CLEAR_INV;
	public static boolean NO_HUNGER_LOSS;
	public static boolean NO_HEALTH_LOSS;
	public static boolean NO_DROPS;
	public static List<String> SERVER_NAMES;
	public static List<Integer> SERVER_IDS;

	public static boolean checkConfig(){
		
		if(Main.getPlugin().getConfig().getBoolean("donotdelete.loaded")){
			
			return true;
			
		}else{
			
			return false;
			
		}
		
	}
	
	public static void createConfig(){
		
		Main.getPlugin().getConfig().set("donotdelete.loaded", true);
		Main.getPlugin().getConfig().set("server.name", "&2MyServer");
		Main.getPlugin().getConfig().set("server.joinmessage", "&1[%SERVERNAME%&1] &2%USERNAME% &3joined the game!");
		Main.getPlugin().getConfig().set("server.leavemessage", "&1[%SERVERNAME%&1] &2%USERNAME% &3left the game!");
		Main.getPlugin().getConfig().set("join.launchfirework", true);
		Main.getPlugin().getConfig().set("join.givecompass", true);
		Main.getPlugin().getConfig().set("join.sendtitle", true);
		Main.getPlugin().getConfig().set("join.clearinv", true);
		Main.getPlugin().getConfig().set("join.nohungerloss", true);
		Main.getPlugin().getConfig().set("join.nohealthloss", true);
		Main.getPlugin().getConfig().set("join.nodrops", true);
		
		List<String> compass = new ArrayList<String>();
		compass.add("&1factions");
		compass.add("&2minigames");
		
		Main.getPlugin().getConfig().set("compass.servernames", compass);
		
		List<Integer> blockids = new ArrayList<Integer>();
		blockids.add(276);
		blockids.add(332);
		
		Main.getPlugin().getConfig().set("compass.serverblockids", blockids);
		
		Main.getPlugin().saveConfig();
		
	}
	
	public static void loadCompassPoints(){
		
		FileConfiguration con = Main.getPlugin().getConfig();
		SERVER_NAMES = con.getStringList("compass.servernames");
		SERVER_IDS = con.getIntegerList("compass.serverblockids");
		
		
	}
	
	public static void loadConfig(){
		
		FileConfiguration config = Main.getPlugin().getConfig();
		
		SERVER_NAME = config.getString("server.name");
		JOIN_MESSAGE = config.getString("server.joinmessage");
		LEAVE_MESSAGE = config.getString("server.leavemessage");
		LAUNCH_FIREWORK = config.getBoolean("join.launchfirework");
		GIVE_COMPASS = config.getBoolean("join.givecompass");
		SEND_TITLE = config.getBoolean("join.sendtitle");
		CLEAR_INV = config.getBoolean("join.clearinv");
		NO_HEALTH_LOSS = config.getBoolean("join.nohealthloss");
		NO_HUNGER_LOSS = config.getBoolean("join.nohungerloss");
		NO_DROPS = config.getBoolean("join.nodrops");
		
	}
	
	public static void filterConfig(){
		
		SERVER_NAME = ChatColor.translateAlternateColorCodes('&', SERVER_NAME);
		JOIN_MESSAGE = ChatColor.translateAlternateColorCodes('&', JOIN_MESSAGE.replace("%SERVERNAME%", SERVER_NAME));
		LEAVE_MESSAGE = ChatColor.translateAlternateColorCodes('&', LEAVE_MESSAGE.replace("%SERVERNAME%", SERVER_NAME));
		
	}
	
}
