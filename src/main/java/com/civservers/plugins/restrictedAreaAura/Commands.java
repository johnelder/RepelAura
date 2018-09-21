package com.civservers.plugins.restrictedAreaAura;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
	private final RestrictedAreaAura pl;
	
	public Commands(RestrictedAreaAura pl) {
		this.pl = pl;
	}
	
	
	


	@Override	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

    	if (cmd.getName().equalsIgnoreCase("mycmd")) {
    		if (args.length > 0) {
    			
    		} else {
    			
    			return false;
    		}
    		return true;   		
    	}
		return false;
	}
	
}
