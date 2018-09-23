# RepelAura
![alt text](https://raw.githubusercontent.com/johnelder/RepelAura/master/RepelAuraIconSmall.jpg "RepelAura")

**Description:** Basic plugin for keeping other players away from your area while you work. Designed for streaming, preventing harassment and playing online with younger kids.

**Latest Version:** 1.0.1

**Tested On:** Spigot 1.13.1, 1.12.2

## Commands

Aliases: /RepelAura or /raura or /raura
- /raura create <range> - Creates a new restricted area around you.
- /raura delete - Deletes your current aura.
- /raura disable - Disables your aura allowing everyone in.
- /raura enable - Re-enables your aura.
- /raura trust <player name> - Allows a specific player into your aura.
- /raura untrust <player name> - Removes a player from the trusted list.
- /raura trustlist - Displays a list of all players that you have allowed in.
- /raura reload - Reloads the configuration file.


## Permissions
- repelaura.use (create and modify your own aura)
- repelaura.admin (create and modify other player's auras, reload config file)


## Default Config File

```
# RepelAura by Civalo
# Spigot URL: Coming Soon to SpigotMC.com   Github: https://github.com/johnelder/RepelAura
# License: GNU Lesser General Public License v3  
# Potion effects available here: https://github.com/johnelder/RepelAura
debug: false
test_on_villagers: false
max_radius: 50
max_power: 15
min_power: 3
repel_delay_seconds: 2
show_admin_actions: true
break_fall_damage: true
apply_potion: true
potion: SLOW_FALLING
potion_duration_seconds: 10
potion_amplifier: 8
remove_auras_on_quit: true

messages:
   prefix: '[RepelAura]'
   repel_message: 'You do not have permission to be in this area.'
   players_only: 'This command can only be run by a player.'
   aura_too_big: 'Aura too large.  Please create a smaller one.'
   created: 'Aura has been created and enabled.'
   created_by_admin: 'Aura has been created and enabled by an admin.'
   deleted: 'Aura has been deleted.'
   deleted_by_admin: 'Aura has been deleted by an admin.'
   disabled: 'Aura has been disabled. Use /raura enable to re-enable it.'
   disabled_by_admin: 'Aura has been disabled by an admin.'
   already_disabled: 'Aura is already disabled.'
   enabled: 'Aura has been enabled.'
   enabled_by_admin: 'Aura has been enabled by adn admin.'
   already_enabled: 'Aura is already enabled.'
   no_aura: 'There is no aura to delete. Try creating one.'
   already: 'Aura already created.'
   add_trust: 'has been added to your trust list.'
   add_trust_already: 'is already on your trust list'
   rem_trust: 'has been removed from your trust list'
   rem_trust_already: 'is not on your trust list.'
   empty_list: 'There are no players on the trust list.'
   not_found: 'Record or Player not found.'
   need_args: 'Please provide another argument. e.g. /raura create 35, /raura trust Civalo'
   permission: 'You do not have permission to use that command.'
   help: |
      /raura create <range> - Creates a new restricted area around you.
      /raura delete - Deletes your current aura.
      /raura disable - Disables your aura allowing everyone in.
      /raura enable - Re-enables your aura.
      /raura trust <player name> - Allows a specific player into your aura.
      /raura untrust <player name> - Removes a player from the trusted list.
      /raura trustlist - Displays a list of all players that you have allowed in.
      /raura relaod - Reloads the config.yml file.


```
## 1.13.1 Potion Options:
```
ABSORPTION				Increases the maximum health of an entity with health that cannot be regenerated, but is refilled every 30 seconds.
BLINDNESS				Blinds an entity.
CONDUIT_POWER			Effects granted by a nearby conduit.
CONFUSION				Warps vision on the client.
DAMAGE_RESISTANCE		Decreases damage dealt to an entity.
DOLPHINS_GRACE			Squee'ek uh'k kk'kkkk squeek eee'eek.
FAST_DIGGING			Increases dig speed.
FIRE_RESISTANCE			Stops fire damage.
GLOWING					Outlines the entity so that it can be seen from afar.
HARM					Hurts an entity.
HEAL					Heals an entity.
HEALTH_BOOST			Increases the maximum health of an entity.
HUNGER					Increases hunger.
INCREASE_DAMAGE			Increases damage dealt.
INVISIBILITY			Grants invisibility.
JUMP					Increases jump height.
LEVITATION				Causes the entity to float into the air.
LUCK					Loot table luck.
NIGHT_VISION			Allows an entity to see in the dark.
POISON					Deals damage to an entity over time.
REGENERATION			Regenerates health.
SATURATION				Increases the food level of an entity each tick.
SLOW					Decreases movement speed.
SLOW_DIGGING			Decreases dig speed.
SLOW_FALLING			Slows entity fall rate.
SPEED					Increases movement speed.
UNLUCK					Loot table unluck.
WATER_BREATHING			Allows breathing underwater.
WEAKNESS				Decreases damage dealt by an entity.
WITHER					Deals damage to an entity over time and gives the health to the shooter.
```
## 1.12.2 Potion Options:
```
ABSORPTION				Increases the maximum health of an entity with health that cannot be regenerated, but is refilled every 30 seconds.
BLINDNESS				Blinds an entity.
CONFUSION				Warps vision on the client.
DAMAGE_RESISTANCE		Decreases damage dealt to an entity.
FAST_DIGGING			Increases dig speed.
FIRE_RESISTANCE			Stops fire damage.
GLOWING					Outlines the entity so that it can be seen from afar.
HARM					Hurts an entity.
HEAL					Heals an entity.
HEALTH_BOOST			Increases the maximum health of an entity.
HUNGER					Increases hunger.
INCREASE_DAMAGE			Increases damage dealt.
INVISIBILITY			Grants invisibility.
JUMP					Increases jump height.
LEVITATION				Causes the entity to float into the air.
LUCK					Loot table luck.
NIGHT_VISION			Allows an entity to see in the dark.
POISON					Deals damage to an entity over time.
REGENERATION			Regenerates health.
SATURATION				Increases the food level of an entity each tick.
SLOW					Decreases movement speed.
SLOW_DIGGING			Decreases dig speed.
SPEED					Increases movement speed.
UNLUCK					Loot table unluck.
WATER_BREATHING			Allows breathing underwater.
WEAKNESS				Decreases damage dealt by an entity.
WITHER					Deals damage to an entity over time and gives the health to the shooter.
```

## Support

We try to be available whenever possible on Discord at [https://discord.gg/W6hwDgJ](https://discord.gg/W6hwDgJ)
## Contributing
We appreciate any contributions.  
- Please make submissions via pull requests on GitHub. 
- Please licensed submissions under GNU Lesser General Public License v3.
## Links
**Spigot Resource Listing:** [https://www.spigotmc.org/resources/repelaura.61050/](https://www.spigotmc.org/resources/repelaura.61050/)

**Issues:** [https://github.com/johnelder/RepelAura/issues](https://github.com/johnelder/RepelAura/issues)

**Beta Testing Server:** techunlimitedgroup.com:25560

**Other Servers:** Many of our plugins can be found in game at pubcraft.civservers.com
