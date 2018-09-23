package com.civservers.plugins.restrictedAura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public final class RestrictedAura extends JavaPlugin {
	
	public FileConfiguration config = getConfig();
	public Map<String, Object> msgs = config.getConfigurationSection("messages").getValues(true);
	
	Utilities Util = new Utilities(this);

	public Collection<? extends Player> onlinePlayers;
	
	public Double defPower;
	public Long repelDelay;
	
	
	@Override
    public void onEnable() {
		
		
		config.options().copyDefaults(true);
	    saveConfig();
	    reload();
	    
	    Util.debug("Power:" + defPower.toString());
	    
	    
	    this.getCommand("restrictedareaaura").setExecutor(new Commands(this));
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(this), this);
	    
		
	    
		BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	for (Player a_pl : onlinePlayers) {
            		String a_uuid = a_pl.getUniqueId().toString();
            		if (config.contains("auras." + a_uuid)) {
            			if (config.getBoolean("auras." + a_uuid + ".enabled")) {
            				
			            	Location a_loc = a_pl.getLocation();
			            	Integer range = config.getInt("auras." + a_uuid + ".radius");
			            	Integer rangeSquared = range * range;		            	
			            	Util.debug("Range:"+range+" R2:"+rangeSquared);
			            	
			            	
			    			if (config.getBoolean("test_on_villagers")) {
			            		Collection<Entity> villagers = Bukkit.getWorld("world").getEntitiesByClasses(Villager.class);
			            		for (Entity ent : villagers) {
			            			Location tloc = ent.getLocation();
			            			Double dDist = tloc.distanceSquared(a_loc);
			            			if (dDist < rangeSquared) {
			            				Util.debug("Villager in range!");
			            				Integer power = (int) ((dDist / rangeSquared) * defPower);
			            				if (power < 5) {
			            					power = 5;
			            				}
			            				Util.debug("PrePower:" + power);
			            				repelEnt(ent,a_loc,power);
			            			}
			            		}
			            	}
			    			
			            	
			        		for (Player t_pl : onlinePlayers) {
			        			String t_uuid = t_pl.getUniqueId().toString();
			        			List<String> trustList = new ArrayList<String>();
			        	    	trustList = config.getStringList("auras." + a_uuid + "trustlist");
			        	    	Util.debug(trustList.toString());
			        	    	if (!trustList.contains(t_uuid)) {
			        	    		if (!a_uuid.equals(t_uuid)) {
					        			Location tloc = t_pl.getLocation();
					        			Double dDist =  tloc.distanceSquared(a_loc);
					
					        			if (dDist < rangeSquared) {
					        				Integer power = (int) ((dDist / rangeSquared) * defPower);
				            				if (power < 5) {
				            					power = 5;
				            				}
					        				Util.debug(a_uuid + " vs " + t_uuid);
					        				repelEnt(t_pl,a_loc,power);
					        			}
			        	    		}
			        	    	} else {
			        	    		Util.debug("Trusted:" + t_uuid);
			        	    	}
			        		}
            			} else {
            				Util.debug("Not enabled");
            			}
            		}
	        		
	        		
	        		
	        		
            	}
            }
        }, 0L, repelDelay);
		
//		Bukkit.getPluginManager().registerEvents(this, this);
		

        
    }
    
    @Override
    public void onDisable() {
    	Bukkit.getConsoleSender().sendMessage(" Unloading");
    }    
    
	public void repelEnt(Entity ent, Location from, Integer power) {
		Vector dir = ent.getLocation().clone().subtract(from).toVector();
		Vector vel = dir.normalize().add(new Vector(0,0.2,0));
		vel = vel.multiply(power);
		ent.setVelocity(vel);
		((LivingEntity) ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 6, 1) );
		
		Util.debug("Vectoring Entity: " + vel.getX() + ":" + vel.getY() + ":" + vel.getZ() + " Power:" + power);
	}
    
    public void debug(String dString) {
    	if (config.getBoolean("debug")) {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + msgs.get("prefix").toString() + " [DEBUG]" + " " + dString);
    	}
    }
    public boolean reload() {
		reloadConfig();
		config = getConfig();
		msgs = config.getConfigurationSection("messages").getValues(true);
		onlinePlayers = Bukkit.getOnlinePlayers();
		defPower = config.getDouble("repel_power");
		repelDelay = config.getLong("repel_delay_seconds") * 20L;
		debug("Config Reloaded - repelDelay:" + repelDelay + " default power:" + defPower);
		return true;     
    }

}

