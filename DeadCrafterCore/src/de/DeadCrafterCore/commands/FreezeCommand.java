package de.DeadCrafterCore.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DeadCrafterCore.Main;

public class FreezeCommand implements CommandExecutor {
	
	public static ArrayList FreezeListe = new ArrayList();
	
	public Main plugin;
	
	public FreezeCommand(Main main) {
		plugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("freeze")) {
			if(!sender.hasPermission("dc.freeze")) {
				sender.sendMessage(plugin.nopermissions);
				return true;
			}
			
			if(args.length == 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				
				if(target == null) {
					sender.sendMessage(plugin.prefix + ChatColor.RED  + "Der Spieler exestiert nicht/ist nicht online!");
					return true;
				}
				if(!FreezeListe.contains(target.getName())) {
					FreezeListe.add(target.getName());
					sender.sendMessage(plugin.prefix + ChatColor.GREEN + "Du hast " + target.getName() + " erfolgreich gefreezt!");
					target.sendMessage(plugin.prefix + ChatColor.RED + "Du wurdest von " + sender.getName() + " gefreezt!"); 
					return true;
				} else {
					FreezeListe.remove(target.getName());
					sender.sendMessage(plugin.prefix + ChatColor.GREEN + "Du hast " + target.getName() + " erfolgreich ungefreezt!");
					target.sendMessage(plugin.prefix + ChatColor.RED + "Du wurdest von " + sender.getName() + " wieder ungefreezt!"); 
					return true;
				}
				
			} else {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Syntax Fehler");
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Verwendung: /freeze <Spieler>");
				return true;
			}
			
			
		}
		
		return false;
		
	}

}
