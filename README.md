# Advancements
Advancements Placeholder for [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) plugin

## Discord

If you see that I do not answer, contact me through my [discord server](https://discord.com/invite/WnfvECZ). I normally pay more attention to discord than github

## Placeholders
* `%Advancements_<advancement>%`
  
  Check for player if have advancement
* `%Advancements_player_<player>;<advancement>%`
  
  Check if another player have advancement
* `%Advancements_playerList_<player>%`
  
  Return data without format. Example:
  ```
  <advancement_name>;<if_player_have_advancement>,<advancement_name>;<if_player_have_advancement>,
  ```
* `%Advancements_playerList_<player>,<command>%`
  
  Send data without format to another command as a parameter (this can be useful if you want to make your custom format with one command)
* `%Advancements_playerListFormat_<player>%`
  
  One simple format for see all advancements:
  ```
  <Advancement>: <green>true/<red>false
  <Advancement>: <green>true/<red>false
  <Advancement>: <green>true/<red>false
  ```
* `%Advancements_completedAmount_<player>%`
  
  Amount complete advancements
* `%Advancements_completedAmount_<player>,<category>%`
  
  Amount **complete** advancemente for category. Default category:
  - nether
  - story
  - adventure
  - end
  - husbandry
* `%Advancements_remainingAmount_<player>%`
  
  Amount **not complete / remaining** advancements
* `%Advancements_remainingAmount_<player>,<category>%`
  
  Amount **not complete / remaining** advancemente for category. Default category:
  - nether
  - story
  - adventure
  - end
  - husbandry
