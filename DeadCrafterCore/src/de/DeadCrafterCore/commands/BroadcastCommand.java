package de.DeadCrafterCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.DeadCrafterCore.Main;

public class BroadcastCommand implements CommandExecutor {
	
	public Main plugin;
	
	public BroadcastCommand(Main main) {
		plugin = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if((cmd.getName().equalsIgnoreCase("bc")) || (cmd.getName().equalsIgnoreCase("broadcast")))  {
			if(!sender.hasPermission("dc.broadcast")) {
				sender.sendMessage(plugin.nopermissions);
				return true;
			}
			
			
			if(args.length == 0)  {
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Syntax Fehler");
				sender.sendMessage(plugin.prefix + ChatColor.RED + "Verwendung: /bc <Nachricht>");
				return true;
			}
			
			if(args.length >0) {
				int i = 0;
		        StringBuilder builder = new StringBuilder();
		        for (String s : args) {
		          if (i >= 0) {
		            builder.append(s);
		            builder.append(" ");
		          }
		          i++;
		        }
		        
		        String nachricht = builder.toString();
		        builder.substring(0, builder.length());
		        
		        nachricht = nachricht.replace("&", "§");
		        
		        plugin.getServer().broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Info/Broadcast von " + sender.getName() + ChatColor.WHITE + "] " + ChatColor.WHITE + nachricht);
		        sender.sendMessage(plugin.prefix + ChatColor.GREEN + "Du hast erfolgreich eine Nachricht gebroadcastet!");
		        return true;
			}
		}
		
		
		return false;
		
	}

}
