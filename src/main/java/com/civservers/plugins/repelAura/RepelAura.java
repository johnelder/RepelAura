package com.civservers.plugins.repelAura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


import org.bukkit.Bukkit;
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

import net.md_5.bungee.api.ChatColor;

public final class RepelAura extends JavaPlugin {
	
	public FileConfiguration config = getConfig();
	public Map<String, Object> msgs = config.getConfigurationSection("messages").getValues(true);
	
	Utilities Util = new Utilities(this);
	BukkitScheduler scheduler = getServer().getScheduler();
	
	public Collection<? extends Player> onlinePlayers;
	
	public Integer maxPower;
	public Integer minPower;
	public Long repelDelay;
	public List<String> falling = new ArrayList<>();
	public List<Entity> fallingEnt = new ArrayList<>();
	
	@Override
    public void onEnable() {
		
		
		config.options().copyDefaults(true);
	    saveConfig();
	    reload();
	    	    
	    this.getCommand("repelaura").setExecutor(new Commands(this));
		Bukkit.getServer().getPluginManager().registerEvents(new Listeners(this), this);		
		
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	for (Player a_pl : onlinePlayers) {
            		String a_uuid = a_pl.getUniqueId().toString();
            		// Is player using an aura?
            		if (config.contains("auras." + a_uuid)) {
            			// Is it enabled
            			if (config.getBoolean("auras." + a_uuid + ".enabled")) {
            				// Get needed locations and range data
			            	Location a_loc = a_pl.getLocation();
			            	Integer range = config.getInt("auras." + a_uuid + ".radius");
			            	Integer rangeSquared = range * range;		            	
			            	
			            	// Run effects on villagers for testing
			    			if (config.getBoolean("test_on_villagers")) {
			    				// Get villagers in players current world
			            		Collection<Entity> villagers =  a_pl.getWorld().getEntitiesByClasses(Villager.class);
			            		for (Entity ent : villagers) {
			            			
			            			Location tloc = ent.getLocation();
			            			Double dDist = tloc.distanceSquared(a_loc);
			            			// Is villager in range of aura?
			            			if (dDist < rangeSquared) {
			            				
			            				Double buffer = (1 - (dDist / rangeSquared));
			            				Integer power = (int) (buffer * maxPower);
			            				
			            				Util.debug("Range: " + buffer + "%" + (buffer * maxPower));
			            				
			            				if (power < minPower) { power = minPower; }
			            				
			            				Util.debug("Power Calculated:" + power);
			            				
			            				repelEnt(ent,a_loc,power);
			            			}
			            		}
			            	}
			    			
			            	
			        		for (Player t_pl : onlinePlayers) {
			        			String t_uuid = t_pl.getUniqueId().toString();
			        			List<String> trustList = new ArrayList<String>();
			        	    	trustList = config.getStringList("auras." + a_uuid + ".trustlist");
			        	    	
			        	    	// Skip players that are trusted
			        	    	if (trustList.contains(t_uuid)) {

			        	    	} else {
			        	    		// Skip aura owner
			        	    		if (!a_uuid.equals(t_uuid)) {
			        	    			
			        	    			// Get target location
					        			Location tloc = t_pl.getLocation();
					        			Double dDist =  tloc.distanceSquared(a_loc);
					        			
					        			// Check if target player is within aura
					        			if (dDist < rangeSquared) {
					        				// Calculate power and repel players
					        				Double buffer = (1 - (dDist / rangeSquared));
				            				Integer power = (int) (buffer * maxPower);
				            				
				            				Util.debug("Range: " + buffer + "%" + (buffer * maxPower));
				            				
				            				if (power < minPower) { power = minPower; }
				            				
				            				Util.debug("Power Calculated:" + power);
					        				Util.debug(a_uuid + " vs " + t_uuid);
					        				repelEnt(t_pl,a_loc,power);
					        			}
			        	    		}
			        	    	}
			        		}
            			} else {
            				Util.debug("Not enabled");
            			}
            		}
            	}
            }
        }, 0L, repelDelay);
       
    }
    
    @Override
    public void onDisable() {
    	if (config.getBoolean("remove_auras_on_quit")) {
    		config.set("auras", null);
    		saveConfig();
    	}
    }    
    
	public void repelEnt(final Entity ent, Location from, Integer power) {
		Util.debug("in repelEnt - ent#:" + ent.getEntityId());
		Vector dir = ent.getLocation().clone().subtract(from).toVector();
		Vector vel = dir.normalize().add(new Vector(0,0.2,0));
		vel = vel.multiply(power);
		ent.setVelocity(vel);
		if (config.getBoolean("apply_potion")) {
			try {
				boolean tryPotion = ((LivingEntity) ent).addPotionEffect(new PotionEffect(PotionEffectType.getByName(config.getString("potion")), config.getInt("potion_duration_seconds") * 20, config.getInt("potion_amplifier")) );
				if (tryPotion) {
					Util.debug("Potion applied: " + config.getString("potion") + ":" + config.getInt("potion_duration_seconds") + ":" + config.getInt("potion_amplifier"));
				}
			} catch (Exception e) {
				Util.debug(e.getMessage());
				if (e.getMessage().equals("effect type cannot be null")) {
					Util.sendConsole(ChatColor.RED + config.getString("potion") + " " + msgs.get("bad_potion").toString());
					Util.sendConsole(ChatColor.RED + "A list of potion options can be found at: https://github.com/johnelder/RepelAura");
				}
			}
		}
		
		if(fallingEnt.add(ent)) {
			Util.debug("Entity added to falling group.");
		} else {
			Util.debug("Could not add to falling group");
		}		
		
		scheduler.runTaskLater(this, new Runnable() {

			@Override
			public void run() {
				if(fallingEnt.remove(ent)) {
					Util.debug("Entity removed from falling group.");
				} else {
					Util.debug("Could not remove from falling group");
				}
			}
			
		}, 200L);
		
		if (ent instanceof Player) {
			Util.sendPlayer((Player) ent,ChatColor.RED + msgs.get("repel_message").toString());
		}
		
		Util.debug("Vectoring Entity: " + vel.getX() + ":" + vel.getY() + ":" + vel.getZ() + " Power:" + power);
	}
    
    public boolean reload() {
		reloadConfig();
		config = getConfig();
		msgs = config.getConfigurationSection("messages").getValues(true);
		onlinePlayers = Bukkit.getOnlinePlayers();
		maxPower = config.getInt("max_power");
		minPower = config.getInt("min_power");
		repelDelay = config.getLong("repel_delay_seconds") * 20L;
		Util.debug("Config Reloaded - repelDelay:" + repelDelay + " default power:" + maxPower);
		Util.debug(Bukkit.getVersion().replace("(MC: ", "|").replace(")", "").split("|")[1].toString());
		return true;     
    }

}

