package com.civservers.plugins.restrictedAreaAura;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;





public class Commands implements CommandExecutor {
	private final RestrictedAreaAura plugin;
	
	public Commands(RestrictedAreaAura plugin) {
		this.plugin = plugin;
	}
	
	
//	public Utilities u;
	
	@Override	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Utilities Util = new Utilities(plugin);
		
		if (sender instanceof Player) {
			Player s_player = (Player) sender;
			String s_uuid = s_player.getUniqueId().toString(); 
	    	if (cmd.getName().equalsIgnoreCase("restrictedareaaura")) {
	    		if (s_player.hasPermission("restrictedaura.use") || s_player.hasPermission("restrictedaura.admin")) {
		    		if (args.length >= 1) {
		    			if (args[0].equalsIgnoreCase("create")) {
		    				
		    				
		    				
		    			} else if (args[0].equalsIgnoreCase("delete")) {
		    				if (plugin.config.contains("auras." + s_uuid)) {
		    					plugin.config.set("auras." + s_uuid, null);
		    					plugin.saveConfig();
		    				} else {
		    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
		    				}
		    			} else if (args[0].equalsIgnoreCase("enable")) {
		    				
		    			} else if (args[0].equalsIgnoreCase("disable")) {
		    				
		    			} else if (args[0].equalsIgnoreCase("trust")) {
		    				
		    			} else if (args[0].equalsIgnoreCase("untrust")) {
		    				
		    			} else if (args[0].equalsIgnoreCase("trustlist")) {
		    				
		    			} else if (args[0].equalsIgnoreCase("reload")) {
		    				plugin.reload();
		    				Util.sendPlayer(s_player, ChatColor.GREEN + "---- Config Reloaded ----");
		    			} else {
		    				Util.sendPlayer(s_player, ChatColor.GREEN + plugin.msgs.get("help").toString());
		    			}
		    		} else {
		    			Util.sendPlayer(s_player, ChatColor.GREEN + plugin.msgs.get("help").toString());
		    			return false;
		    		}
	    		return true;
	    		} else {
	    			Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("permission").toString());
	    		}
	    	}
		} else {
			Util.sendConsole(ChatColor.RED + plugin.msgs.get("players_only").toString());
		}
		return false;
	}
	
}
