package de.DeadCrafterCore.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.DeadCrafterCore.Main;

public class BanList {
	public static List player = Arrays.asList(new String[] { "kingpopo" });
	
	public Main plugin;
	
	public BanList(Main main) {
		plugin = main;
		
		manageConfig();
	}
	
	private void manageConfig() {
		
		File configFile = new File("plugins/DeadCrafterCore/ban-list.yml");
		config = YamlConfiguration.loadConfiguration(configFile);

		
		//Optionen
		config.options().copyDefaults(true);
		config.options().header("BanList of DeadCrafterBan || Plugin by JonnyMine25");
		
		//Adding
		config.addDefault("BanList.Spieler", player);
		config.addDefault("BanList.Grund.kingpopo", "Hacker bzw. Mr_bio xD.");
		
		//Saving
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Object getConfigValue(String path) {
		
		return config.get(path);
	}

	public FileConfiguration config;
}
