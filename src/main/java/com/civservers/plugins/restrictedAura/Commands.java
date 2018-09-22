package com.civservers.plugins.restrictedAura;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;





public class Commands implements CommandExecutor {
	private final RestrictedAura plugin;
	
	public Commands(RestrictedAura plugin) {
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
		    				if (args.length >= 3) {
		    					
		    					/* Creating other player's aura */
		    					if (s_player.hasPermission("restrictedaura.admin")) {
		    						String t_uuid = Util.getOnlineUUID(args[2]);
		    						if (!t_uuid.equals("not found")) {
				    					if (!plugin.config.contains("auras." + t_uuid)) {
				    						if (args.length >= 3) {
				    							if (Util.isValidNumber(args[1])) {
					    							plugin.config.set("auras." + t_uuid + ".radius", args[1]);
					    							plugin.config.set("auras." + t_uuid + ".enabled", true);
					    							plugin.saveConfig();
					    							Util.sendPlayer(s_player, ChatColor.GREEN + plugin.msgs.get("created").toString());
					    							if (plugin.config.getBoolean("show_admin_actions")) {
					    								Util.sendPlayer(Bukkit.getPlayer(UUID.fromString(t_uuid)), ChatColor.GREEN + plugin.msgs.get("created_by_admin").toString());
					    							}
				    							} else {
				    								Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("need_args").toString());
				    							}
				    						} else {
				    							Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("need_args").toString());
				    						}
				    					} else {
				    						Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("already").toString());
				    					}
		    						} else {
		    							Util.sendPlayer(s_player, plugin.msgs.get("not_found").toString());
		    						}
		    					} else {
		    						Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("permission").toString());
		    					}
		    				} else {
		    					
			    				/* Creating own aura */
		    					if (!plugin.config.contains("auras." + s_uuid)) {
		    						if (args.length >= 2) {
		    							if (Util.isValidNumber(args[1])) {
			    							plugin.config.set("auras." + s_uuid + ".radius", args[1]);
			    							plugin.config.set("auras." + s_uuid + ".enabled", true);
			    							plugin.saveConfig();
			    							Util.sendPlayer(s_player, ChatColor.GREEN + plugin.msgs.get("created").toString());
		    							} else {
		    								Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("need_args").toString());
		    							}
		    						} else {
		    							Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("need_args").toString());
		    						}
		    					} else {
		    						Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("already").toString());
		    					}
		    				}
	    					
	    					
	    					
	    					
	    					
		    			} else if (args[0].equalsIgnoreCase("delete")) {
		    				if (args.length >= 2) {
		    					
		    					// Deleting other player aura
		    					if (s_player.hasPermission("restrictedaura.admin")) {
		    						String t_uuid = Util.getOnlineUUID(args[1]);
				    				Player t_player = Bukkit.getPlayer(UUID.fromString(t_uuid));
				    				if (plugin.config.contains("auras." + t_uuid)) {
				    					plugin.config.set("auras." + t_uuid, null);
				    					plugin.saveConfig();
				    					Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("deleted").toString());
		    							if (plugin.config.getBoolean("show_admin_actions")) {
		    								Util.sendPlayer(Bukkit.getPlayer(UUID.fromString(t_uuid)), ChatColor.GREEN + plugin.msgs.get("deleted_by_admin").toString());
		    							}
				    				} else {
				    					Util.sendPlayer(s_player, plugin.msgs.get("not_found").toString());
				    				}
			    				} else {
			    					Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("permission").toString());
			    				}
		    				} else {
		    					
		    					// Deleting own aura
		    					if (plugin.config.contains("auras." + s_uuid)) {
			    					plugin.config.set("auras." + s_uuid, null);
			    					plugin.saveConfig();
			    					Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("deleted").toString());
			    				} else {
			    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
			    				}
		    				}
			    				
		    				
		    				
		    				
		    				
		    				
		    				
		    			} else if (args[0].equalsIgnoreCase("enable")) {
		    				if (args.length >= 2) {
		    					
		    					/* Enabling other player aura */
		    					if (s_player.hasPermission("restrictedaura.admin")) {
		    						String t_uuid = Util.getOnlineUUID(args[1]);
		    						if (plugin.config.contains("auras." + t_uuid)) {
				    					if (!plugin.config.get("auras." + t_uuid + ".enabled").equals(true)) {
				    						plugin.config.set("auras." + t_uuid + ".enabled", true);
				    						plugin.saveConfig();
				    						Util.sendPlayer(s_player, plugin.msgs.get("enabled").toString());
				    						if (plugin.config.getBoolean("show_admin_actions")) {
				    							Util.sendPlayer(Bukkit.getPlayer(UUID.fromString(t_uuid)), plugin.msgs.get("enabled_by_admin").toString());
				    						}
				    					} else {
				    						Util.sendPlayer(s_player, plugin.msgs.get("already_enabled").toString());
				    					}
				    				} else {
				    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
				    				}
			    				} else {
			    					Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("permission").toString());
			    				}
		    				} else {
		    					
		    					/* Enabling own aura */
			    				if (plugin.config.contains("auras." + s_uuid)) {
			    					if (!plugin.config.get("auras." + s_uuid + ".enabled").equals(true)) {
			    						plugin.config.set("auras." + s_uuid + ".enabled", true);
			    						plugin.saveConfig();
			    						Util.sendPlayer(s_player, plugin.msgs.get("enabled").toString());
			    					} else {
			    						Util.sendPlayer(s_player, plugin.msgs.get("already_enabled").toString());
			    					}
			    				} else {
			    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
			    				}
		    				}
		    				
		    				
		    				
		    				
		    				
		    			} else if (args[0].equalsIgnoreCase("disable")) {
		    				if (args.length >= 2) {
		    					/* Disabling other player's aura */
		    					if (s_player.hasPermission("restrictedaura.admin")) {
		    						String t_uuid = Util.getOnlineUUID(args[1]);
		    						if (plugin.config.contains("auras." + t_uuid)) {
				    					if (!plugin.config.get("auras." + t_uuid + ".enabled").equals(false)) {
				    						plugin.config.set("auras." + t_uuid + ".enabled", false);
				    						plugin.saveConfig();
				    						Util.sendPlayer(s_player, plugin.msgs.get("disabled").toString());
				    						if (plugin.config.getBoolean("show_admin_actions")) {
				    							Util.sendPlayer(Bukkit.getPlayer(UUID.fromString(t_uuid)), plugin.msgs.get("disabled_by_admin").toString());
				    						}
				    					} else {
				    						Util.sendPlayer(s_player, plugin.msgs.get("already_disabled").toString());
				    					}
				    				} else {
				    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
				    				}				    				
			    				} else {
			    					Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("permission").toString());
			    				}
		    				} else {
		    					/* Disabling own aura */
			    				if (plugin.config.contains("auras." + s_uuid)) {
			    					if (!plugin.config.get("auras." + s_uuid + ".enabled").equals(false)) {
			    						plugin.config.set("auras." + s_uuid + ".enabled", false);
			    						plugin.saveConfig();
			    						Util.sendPlayer(s_player, plugin.msgs.get("disabled").toString());
			    					} else {
			    						Util.sendPlayer(s_player, plugin.msgs.get("already_disabled").toString());
			    					}
			    				} else {
			    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
			    				}
		    				}
		    				
		    			} else if (args[0].equalsIgnoreCase("trust")) {
		    				if (plugin.config.contains("auras." + s_uuid)) {
		    					if (args.length >= 2) {
			    					String t_uuid = Util.getOnlineUUID(args[1]);
			    					Boolean addTrust = Util.configListAdd("auras." + s_uuid + ".trustlist", t_uuid);
			    					if (addTrust) {
			    						Util.sendPlayer(s_player, args[1] + " " + plugin.msgs.get("add_trust"));
			    					} else {
			    						Util.sendPlayer(s_player, args[1] + " " + plugin.msgs.get("add_trust_already"));
			    					}
		    					} else {
		    						Util.sendPlayer(s_player, plugin.msgs.get("need_args").toString());
		    					}
		    				} else {
		    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
		    				}
		    				
		    			} else if (args[0].equalsIgnoreCase("untrust")) {
		    				if (plugin.config.contains("auras." + s_uuid)) {
		    					if (args.length >= 2) {
			    					String t_uuid = Util.getOnlineUUID(args[1]);
			    					Boolean remTrust = Util.configListRemove("auras." + s_uuid + ".trustlist", t_uuid);
			    					if (remTrust) {
			    						Util.sendPlayer(s_player, args[1] + " " + plugin.msgs.get("rem_trust"));
			    					} else {
			    						Util.sendPlayer(s_player, args[1] + " " + plugin.msgs.get("rem_trust_already"));
			    					}
		    					} else {
		    						Util.sendPlayer(s_player, plugin.msgs.get("need_args").toString());
		    					}
		    				} else {
		    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
		    				}
		    				
		    				
		    			} else if (args[0].equalsIgnoreCase("trustlist")) {
		    				if (plugin.config.contains("auras." + s_uuid)) {
								String[] trustList = plugin.config.getStringList("auras." + s_uuid + ".trustlist").toArray(new String[0]);
			    				if (trustList.length > 0) {
			    					for (String t_uuid : trustList) {
			    						Util.sendPlayer(s_player, Bukkit.getOfflinePlayer(UUID.fromString(t_uuid)).getName().toString());
			    					}
			    				} else {
			    					Util.sendPlayer(s_player, plugin.msgs.get("empty_list").toString());
			    				}
		    				} else {
		    					Util.sendPlayer(s_player, plugin.msgs.get("no_aura").toString());
		    				}
		    			} else if (args[0].equalsIgnoreCase("reload")) {
		    				if (s_player.hasPermission("restrictedaura.admin")) {
			    				plugin.reload();
			    				Util.sendPlayer(s_player, ChatColor.GREEN + "---- Config Reloaded ----");
		    				} else {
		    					Util.sendPlayer(s_player, ChatColor.RED + plugin.msgs.get("permission").toString());
		    				}
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
