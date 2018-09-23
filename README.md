# RepelAura
**Description:** 

**Latest Version:** 0.0.1
**Tested On:** Spigot 1.13.1

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


## Default Config File

```
# RepelAura by Civalo
# Spigot URL: Coming Soon to SpigotMC.com   Github: https://github.com/johnelder/RepelAura
# License: GNU Lesser General Public License v3  
debug: false
test_on_villagers: false
max_radius: 50
max_power: 15
min_power: 3
repel_delay_seconds: 2
show_admin_actions: true
potion: SLOW_FALLING
potion_duration_seconds: 10
potion_amplifier: 8

messages:
   prefix: '[RepelAura]'
   aura_message: 'You do not have permission to be in this area.'
   players_only: 'This command can only be run by a player.'
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


## Support
We try to be available whenever possible on Discord at [https://discord.gg/W6hwDgJ](https://discord.gg/W6hwDgJ)
## Contributing
We appreciate any contributions.  
- Please make submissions via pull requests on GitHub. 
- Please licensed submissions under GNU Lesser General Public License v3.
## Links
**Spigot Resource Listing:** Will be updated on release.

**Issues:** [https://github.com/johnelder/RepelAura/issues](https://github.com/johnelder/RepelAura/issues)

**Beta Testing Server:** techunlimitedgroup.com:25560

**Other Servers:** Many of our plugins can be found in game at pubcraft.civservers.com
