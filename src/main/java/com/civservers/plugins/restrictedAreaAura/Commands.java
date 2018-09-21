package com.civservers.plugins.restrictedAreaAura;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Commands implements CommandExecutor {
	private final RestrictedAreaAura pl;
	private Utilities u;
	
	public Commands(RestrictedAreaAura pl) {
		this.pl = pl;
	}
	
	
	


	@Override	
	public boolean onCommand(CommandSender csender, Command cmd, String label, String[] args) {
		u.debug(pl.msgs.toString());
		if (csender instanceof Player) {
			Player sender = (Player) csender;
			String s_uuid = sender.getUniqueId().toString(); 
	    	if (cmd.getName().equalsIgnoreCase("restrictedareaaura")) {
	    		if (args.length >= 1) {
	    			if (args[0].equalsIgnoreCase("start")) {
	    				if (args.length >= 2) {
	    					if (u.isValidNumber(args[1])) {
	    						u.debug("arg 1 is number");
	    					} else {
	    						//TODO msg please enter a number from 1 to ... (/raa start <radius> ['message to players'])
	    					}
	    				}
	    			} else if (args[0].equalsIgnoreCase("stop")) {
	    				if (pl.config.contains("auras." + s_uuid)) {
	    					pl.config.set("auras." + s_uuid, null);
	    				}
	    			} else if (args[0].equalsIgnoreCase("pause")) {
	    			} else if (args[0].equalsIgnoreCase("resume")) {
	    			} else if (args[0].equalsIgnoreCase("allow")) {
	    			} else if (args[0].equalsIgnoreCase("deny")) {
	    				
	    			}
	    		} else {
	    			
	    			return false;
	    		}
	    		return true;   		
	    	}
		} else {
			u.sendConsole(ChatColor.RED + "" + pl.msgs.get("players_only").toString());
		}
		return false;
	}
	
}
