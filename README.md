# Advancements
Advancements Placeholder for [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) plugin

## Discord

If you see that I do not answer, contact me through my [discord server](https://discord.com/invite/WnfvECZ). I normally pay more attention to discord than github

## Placeholders
* `%Advancements_<advancement>%`
  
  Check for player if have advancement
* `%Advancements_player_<player>;<advancement>%`
  
  Check if another player have advancement
* `%Advancements_list%`
  Return data without format. Example:
  ```
  <advancement_name>;<if_player_have_advancement>,<advancement_name>;<if_player_have_advancement>,
  ```
* `%Advancements_list_<command>%`
  
  Send data without format to another command as a parameter (this can be useful if you want to make your custom format with one command)
* `%Advancements_playerList_<player>%`
  
  Equal to `%Advancements_list%` just for another player
* `%Advancements_playerList_<player>,<command>%`
  
  Equal to `%Advancements_list_<command>%` just for another player
* `%Advancements_listFormat_<player>%`
  
  One simple format for see all advancements:
  ```
  <Advancement>: <green>true/<red>false
  <Advancement>: <green>true/<red>false
  <Advancement>: <green>true/<red>false
  ```
* `%Advancements_playerListFormat_<player>%`
  
  Equal to `%Advancements_listFormat%` just for another player
* `%Advancements_completedAmount%`
  
  Amount complete advancements
* `%Advancements_completedAmount_<category>%`
  
  Amount **complete** advancemente for category. Default category:
  - nether
  - story
  - adventure
  - end
  - husbandry
* `%Advancements_playerCompletedAmount_<player>%`
  
  Equal to `%Advancements_completedAmount%` just for another player
* `%Advancements_playerCompletedAmount_<player>,<category>%`
  
  Equal to `%Advancements_completedAmount_<category>%` just for another player
* `%Advancements_remainingAmount%`
  
  Amount **not complete / remaining** advancements
* `%Advancements_remainingAmount_<category>%`
  
  Amount **not complete / remaining** advancemente for category. Default category:
  - nether
  - story
  - adventure
  - end
  - husbandry
* `%Advancements_playerRemainingAmount_<player>%`
  
  Equal to `%Advancements_remainingAmount%` just for another player
* `%Advancements_playerRemainingAmount_<player>,<category>%`
  
  Equal to `%Advancements_remainingAmount_<category>%` just for another player
