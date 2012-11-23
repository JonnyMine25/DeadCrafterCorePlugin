package de.DeadCrafterCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DeadCrafterCore.Main;
import de.DeadCrafterCore.utils.Config;

public class PWCommand implements CommandExecutor {
	
	public Main plugin;
	
	public PWCommand(Main main) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p;
		
		if(!(sender instanceof Player)) {
			System.out.println("[DeadCrafterCore] Dieser Command ist nur fuer Spieler geignet!");
		}
		
		p = (Player) sender;
		
		if((cmd.getName().equalsIgnoreCase("pw")) || (cmd.getName().equalsIgnoreCase("apply"))) {
			if(!p.hasPermission("dc.pw")) {
				p.sendMessage(plugin.nopermissions);
				return true;
			}
			
			if(args.length == 0) {
				p.sendMessage(plugin.prefix + ChatColor.RED + "Syntax Fehler");
				p.sendMessage(plugin.prefix + ChatColor.RED + "Verwendung: /pw <Passwort>");
				return true;
			}
			
			
			Object pw = plugin.cU.getConfigValue("Configuration.Promoter.Freischaltungs-Passwort");
			if(args[0].equals(pw)) {
				Object gruppe = plugin.cU.getConfigValue("Configuration.Promoter.Freischaltungs-Gruppe");
				plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "pex user " + p.getName() + " group set " + gruppe);
				p.sendMessage(plugin.prefix + ChatColor.GREEN + "Du wurdest erfolgreich in die Gruppe " + gruppe + " promotet!");
				return true;
			} else {
				p.sendMessage(plugin.prefix + ChatColor.RED + "Das Passwort, welches du eingegeben hast, ist falsch!");
				return true;
			}
		}
		
		
		return false;
		
	}

}
