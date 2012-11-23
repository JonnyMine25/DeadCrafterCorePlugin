package de.DeadCrafterCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.DeadCrafterCore.commands.BanCommand;
import de.DeadCrafterCore.commands.BroadcastCommand;
import de.DeadCrafterCore.commands.FreezeCommand;
import de.DeadCrafterCore.commands.MuteCommand;
import de.DeadCrafterCore.commands.PWCommand;
import de.DeadCrafterCore.commands.PromCommand;
import de.DeadCrafterCore.listener.PlayerListener1;
import de.DeadCrafterCore.utils.BanList;
import de.DeadCrafterCore.utils.Config;

public class Main extends JavaPlugin {
	
	//Public Strings
	public String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "DeadCrafterCore" + ChatColor.WHITE + "] ";
	public String dcban = ChatColor.WHITE + "[" + ChatColor.GOLD + "DeadCrafterBan" + ChatColor.WHITE + "] ";
	public String nopermissions = this.prefix + ChatColor.RED + "Du hast keine Rechte, um diese Aktion auszuführen!";
	
	//OnDisable Methode
	@Override
	public void onDisable() {
		
		System.out.println("[DeadCrafterCore] Plugin wurde deaktiviert!");
		
	}
	
	//OnEnable Methode
	@Override
	public void onEnable() {
		registerStuff();
		
		System.out.println("[DeadCrafterCore] Plugin wurde aktiviert!(Plugin by JonnyMine25)");
	}
	
	//RegisterStuff Methode
	private void registerStuff() {
		//PluginManager
		PluginManager manager = Bukkit.getPluginManager();
		
		//Commands laden
		getCommand("freeze").setExecutor(Freezecmd);
		getCommand("prom").setExecutor(Promcmd);
		getCommand("pw").setExecutor(PWcmd);
		getCommand("mute").setExecutor(Mutecmd);
		getCommand("broadcast").setExecutor(Broadcastcmd);
		getCommand("ban").setExecutor(Bancmd);
		
		//Permissions-Nachrichten laden
		
		//Config laden
		new Config(this);
		new BanList(this);
		
		//Listener laden
		manager.registerEvents(pl1, this);
		
		//Anderes laden
		
		//Anderes
		
	}
	//Commands
	private BroadcastCommand Broadcastcmd = new BroadcastCommand(this);
	private MuteCommand Mutecmd = new MuteCommand(this);
	private FreezeCommand Freezecmd = new FreezeCommand(this);
	private PromCommand Promcmd = new PromCommand(this);
	private PWCommand PWcmd = new PWCommand(this);
	private BanCommand Bancmd = new BanCommand(this);
	
	//Config
	public Config cU = new Config(this);
	public BanList banlist = new BanList(this);

	//Listener
	private PlayerListener1 pl1 = new PlayerListener1(this);
}
