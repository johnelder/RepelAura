package com.civservers.plugins.restrictedAura;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class RestrictedAreaAura extends JavaPlugin {
	
	public FileConfiguration config = getConfig();
	public Map<String, Object> msgs = config.getConfigurationSection("messages").getValues(true);
	
	Utilities Util = new Utilities(this);

	@Override
    public void onEnable() {
		
		
		config.options().copyDefaults(true);
	    saveConfig();
	    reload();
	    
	    this.getCommand("restrictedareaaura").setExecutor(new Commands(this));
		
//		BukkitScheduler scheduler = getServer().getScheduler();
//        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
//            @Override
//            public void run() {
//                
//            }
//        }, 0L, 100L);
		
//		Bukkit.getPluginManager().registerEvents(this, this);
		
		
        
    }
    
    @Override
    public void onDisable() {
    	Bukkit.getConsoleSender().sendMessage(" Unloading");
    }    
    

    public void debug(String dString) {
    	if (config.getBoolean("debug")) {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + msgs.get("prefix").toString() + " [DEBUG]" + " " + dString);
//    		u.sendConsole(ChatColor.LIGHT_PURPLE + msgs.get("prefix").toString() + " [DEBUG]" + " " + dString);
    	}
    }
    public boolean reload() {
		reloadConfig();
		config = getConfig();
		msgs = config.getConfigurationSection("messages").getValues(true);
		
		debug("Config Reloaded.");
		return true;     
    }

}

