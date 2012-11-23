package de.DeadCrafterCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DeadCrafterCore.Main;
import de.DeadCrafterCore.utils.BanList;

public class BanCommand implements CommandExecutor {
	
	public Main plugin;
	
	public BanCommand(Main main) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("ban")) {
			
			if(!sender.hasPermission("dc.ban")) {
				sender.sendMessage(plugin.nopermissions);
				return true;
			}
			
			if(args.length == 0) {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Syntax Fehler");
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Verwendung: /ban <Spieler> <Grund>");
				return true;
			}
			
			if(args.length == 1) {
				BanList banlist = new BanList(plugin);
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target.isOnline()) {
					target.kickPlayer(ChatColor.RED + "Du wurdest gebannt! Grund: " + ChatColor.WHITE + "KEIN GRUND!");
					return true;
				}

				banlist.player.add(target.getName());
				banlist.config.addDefault("BanList.Grund." + target.getName(), "KEIN GRUND!");
				plugin.getServer().broadcastMessage(plugin.dcban + ChatColor.RED + target.getName() + ChatColor.GREEN + " wurde gebannt! Grund: " + ChatColor.RED + "KEIN GRUND!");
				sender.sendMessage(plugin.dcban + ChatColor.RED + "Du hast erfolgreich " + ChatColor.RED + target.getName() + ChatColor.GREEN + " gebannt!");
				return true;
			}
			
			if(args.length >1) {
				int i = 0;
		        StringBuilder builder = new StringBuilder();
		        for (String s : args) {
		          if (i >= 1) {
		            builder.append(s);
		            builder.append(" ");
		          }
		          i++;
		        }
		        
		        String grund = builder.toString();
		        builder.substring(0, builder.length());
		        
		        grund = grund.replace("&", "§");
				
				BanList banlist = new BanList(plugin);
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(target.isOnline()) {
					target.kickPlayer(ChatColor.RED + "Du wurdest gebannt! Grund: " + ChatColor.WHITE + grund);
					return true;
				}
				banlist.player.add(target.getName());

				banlist.config.addDefault("BanList.Grund." + target.getName(), grund);
				plugin.getServer().broadcastMessage(plugin.dcban + ChatColor.RED + target.getName() + ChatColor.GREEN + " wurde gebannt! Grund: " + ChatColor.RED + grund);
				sender.sendMessage(plugin.dcban + ChatColor.RED + "Du hast erfolgreich " + ChatColor.RED + target.getName() + ChatColor.GREEN + " gebannt!");
				return true;
			}
			
		}
		
		return false;
		
	}

}
