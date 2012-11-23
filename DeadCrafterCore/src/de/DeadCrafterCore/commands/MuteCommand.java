package de.DeadCrafterCore.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DeadCrafterCore.Main;

public class MuteCommand implements CommandExecutor {
	
	public Main plugin;
	
	public MuteCommand(Main main) {
		plugin = main;
	}
	
	public static ArrayList MuteListe = new ArrayList();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("mute")) {
			if(!sender.hasPermission("dc.mute")) {
				sender.sendMessage(plugin.nopermissions);
				return true;
			}
			
			if(args.length == 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				
				if(target == null) {
					sender.sendMessage(plugin.prefix + ChatColor.RED + "Der Spieler exestiert nicht/ist nicht online!");
					return true;
				}
				
				if(!MuteListe.contains(target.getName())) {
					MuteListe.add(target.getName());
					sender.sendMessage(plugin.prefix + ChatColor.GREEN + "Du hast " + target.getName() + " erfolgreich gemutet!");
					target.sendMessage(plugin.prefix + ChatColor.RED + "Du wurdest von " + sender.getName() + " gemutet!");
					return true;
				} else {
					MuteListe.remove(target.getName());
					sender.sendMessage(plugin.prefix + ChatColor.GREEN + "Du hast " + target.getName() + " erfolgreich demutet!");
					target.sendMessage(plugin.prefix + ChatColor.RED + "Du wurdest wieder von " + sender.getName() + " demutet!");
					return true;
				}
			} else {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Syntax Fehler");
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Verwendung: /mute <Spieler>");
				return true;
			}
		}
		
		return false;
		
	}

}
