package de.DeadCrafterCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DeadCrafterCore.Main;

public class PromCommand implements CommandExecutor {
	
	public Main plugin;
	
	public PromCommand(Main main) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p;
		
		if(!(sender instanceof Player)) {
			System.out.println("[DeadCrafterCore] Dieser Command ist nur fuer Spieler geignet!");
		}
		
		p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("prom")) {
			if(!sender.hasPermission("dc.prom")) {
				p.sendMessage(plugin.nopermissions);
				return true;
			}
			
			if(args.length == 2) {
				p.performCommand("pex user " + args[0] + " group set " + args[1]);
				return true;
			} else {
				p.sendMessage(plugin.prefix + ChatColor.RED + "Syntax Fehler");
				p.sendMessage(plugin.prefix + ChatColor.RED + "Verwendung: /prom <Spieler> <Gruppe>");
				return true;
			}
		}
		
		return false;
		
	}

}
