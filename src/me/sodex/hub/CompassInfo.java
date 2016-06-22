package me.sodex.hub;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.ChatColor;

public class CompassInfo implements Listener{

	public static ItemStack getCompass(){
		
		ItemStack i = new ItemStack(Material.COMPASS);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "SERVER SELECTOR");
		i.setItemMeta(im);
		
		return i;
		
	}
	
	@EventHandler
	public void compassClick(PlayerInteractEvent e){
		
		try{
		
			if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
				
				if(!e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "SERVER SELECTOR")){
					return;
				}else{
					e.setCancelled(true);
					
					Inventory i = Bukkit.createInventory(null, getInventorySize(ConfigMessages.SERVER_NAMES.size()), ChatColor.GREEN + "Server Selector");
					
					int largest = ConfigMessages.SERVER_NAMES.size();
					
					int got = 0;
					
					for(got = 0; got < largest; got++){
						
						String name = ConfigMessages.SERVER_NAMES.get(got);
						int itemID = ConfigMessages.SERVER_IDS.get(got);
						
						@SuppressWarnings("deprecation")
						ItemStack item = new ItemStack(Material.getMaterial(itemID));
						ItemMeta itemMeta = item.getItemMeta();
						itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
						
						List<String> lore = new ArrayList<String>();
						lore.clear();
						
						itemMeta.setLore(lore);
						
						item.setItemMeta(itemMeta);
						
						i.addItem(item);
						
					}
					
					e.getPlayer().openInventory(i);
					
				}
				
			}
		
		}catch(Exception e1){}
		
	}
	
	private static int getInventorySize(int max) {
		
	    if (max <= 9){
	        return 9;
	    }else if (max <= 18){
	        return 18;
	    }else if (max <= 27){
	        return 27;
	    }else if (max <= 36){
	        return 36;
	    }else if (max <= 45){
	        return 45;
	    }else if (max <= 54){
	        return 54;
	    }else{
	        return 54;
	    }
	    
	}
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e){
		
		if(e.getInventory().getName().equals(ChatColor.GREEN + "Server Selector")){
			
			String servername = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
			
			e.setCancelled(true);
			e.getWhoClicked().closeInventory();
			Player player = (Player) e.getWhoClicked();
			
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(servername);
            player.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
			
		}
		
	}
	
}
