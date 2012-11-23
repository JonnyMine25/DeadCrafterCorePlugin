package de.DeadCrafterCore.listener;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import de.DeadCrafterCore.Main;
import de.DeadCrafterCore.commands.FreezeCommand;
import de.DeadCrafterCore.commands.MuteCommand;
import de.DeadCrafterCore.utils.BanList;

public class PlayerListener1 implements Listener {
	
	public Main plugin;
	
	public PlayerListener1(Main main) {
		plugin = main;
	}
	
	
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		
		if(FreezeCommand.FreezeListe.contains(p.getName())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		PermissionUser user = PermissionsEx.getPermissionManager().getUser(p);
		
		if(p.hasPermission("dc.colorchat")) {
			String message = user.getPrefix() + p.getName() + ChatColor.WHITE + ": " + event.getMessage();
			message = message.replace("&", "§");
			event.setFormat(message);
		} else {
			String prefixuser = user.getPrefix() + p.getName() + ChatColor.WHITE + ": ";
			prefixuser = prefixuser.replace("&", "§");
			event.setFormat(prefixuser + event.getMessage()); 
		}
		
		if(event.getMessage().contains("Anwalt")) {
			event.setCancelled(true);
			p.kickPlayer(plugin.prefix + ChatColor.RED + "Du darfst das Passwort nicht im Chat schreiben!");
			plugin.getServer().broadcastMessage(plugin.prefix + ChatColor.RED + p.getName() + " wollte das Passwort im Chat schreiben!");
		}
		
		if(MuteCommand.MuteListe.contains(p.getName())) {
			event.setCancelled(true);
			p.sendMessage(plugin.prefix + ChatColor.RED + "Du bist gemutet!");
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		
		if(!BanList.player.contains(p.getName())) {
			event.setJoinMessage(ChatColor.WHITE + p.getName() + ChatColor.GREEN + " ist dem Server beigetreten!");
		} else {
			Object grund = plugin.cU.getConfigValue("BanList.Grund." + p.getName());
			p.kickPlayer(plugin.dcban + ChatColor.RED + "Du bist gebannt! Grund: " + ChatColor.WHITE + grund);
		}
		
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		
		event.setQuitMessage(ChatColor.WHITE + p.getName() + ChatColor.GREEN + " hat den Server verlassen!");
	}
	
	

}
