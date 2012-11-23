package de.DeadCrafterCore.utils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.DeadCrafterCore.Main;

public class Config {
	
	public Main plugin;
	
	public Config(Main main) {
		plugin = main;
		
		manageConfig();
	}
	
	private void manageConfig() {
		
		File configFile = new File("plugins/DeadCrafterCore/config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		LinkedHashMap<String, Object> configMap = new LinkedHashMap<String, Object>();
		
		//Optionen
		config.options().header("Config of DeadCrafterCore || Plugin by JonnyMine25");
		
		//Adding
		configMap.put("Configuration.Promoter.Freischaltungs-Passwort", "PASSWORT");
		configMap.put("Configuration.Promoter.Freischaltungs-Gruppe", "Spieler");
		
		//Settings
		for(Entry<String, Object> entry : configMap.entrySet()) {
			String path = entry.getKey();
			Object value = entry.getValue();
			
			if(!(config.contains(path))) {
				config.set(path, value);
			}
		}
		
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

	private FileConfiguration config;
}
