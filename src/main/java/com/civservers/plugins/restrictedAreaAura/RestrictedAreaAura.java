package com.civservers.plugins.restrictedAreaAura;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;





public final class RestrictedAreaAura extends JavaPlugin implements Listener {
	
	public String pluginName = "RestrictedAreaAura";
	public Utilities u;
	public FileConfiguration config = getConfig();
	
	public Map<String, Object> msgs = config.getConfigurationSection("messages").getValues(true);
	

	@Override
    public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage("["+pluginName+"] Loading");
		
		config.options().copyDefaults(true);
	    saveConfig();
	    reload();
	    
		this.getCommand("mycmd").setExecutor(new Commands(this));
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                
            }
        }, 0L, 100L);
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		u.debug("Testing");
        
    }
    
    @Override
    public void onDisable() {
    	Bukkit.getConsoleSender().sendMessage("["+pluginName+"] Unloaded");
    }    
    

    public boolean reload() {
		reloadConfig();
		config = getConfig();
		msgs = config.getConfigurationSection("messages").getValues(true);
		return true;     
    }

}

