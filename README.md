# "Missing" - Island Survivor Game

#### This project is developed for SWEN222

# Getting Started

## Single Player
Select Play Game -> Host Game -> select 1 player and enter an available port from 1024 to 65535 -> Start Server -> Create Player
## Multiplayer
One player must host a game with up to 5 players  
Other players join by entering the host IP address and port into the Join Game menu  
Once all players have connected to the host, the game will start

# Playing the Game

## Controls
**Moving** - W-A-S-D  
**Rotate anti-clockwise** - Q  
**Rotate clockwise** - E  
**Perform action** - F  

## In Game Panel

**Player Money** -  Displays the amount of money you have  
**Current Time** - Displays the in game time - 1 real life second = 10 in game minutes  
**Current Player** - Displays your name  
**Craft Item** - Opens the crafting window  
**View Map** - Opens a map of the world  
**Players Bag** - Opens the player's bag  
**Players Online** - Displays the players currently online

## Perfrom Action
Pushing 'F' in front of the following objects will do the following  
**Tree** - Gives you wood and sometimes apples  
**Bush** - Gives you berries  
**Rock** - Gives you stones  
**Soil** - Gives you dirt  
**Fishing Area** - Gives you fish  
**Shop Door** - Enters shop  
**Other Player** - Damages player  

## Player Pocket and Bag
The pocket and bag are used to hold items for a player  
Items can be moved between the pocket and bag by opening the Player Bag window  
**Pocket Capacity** - 10 items  
**Bag Capacity** - 20 items but can be expanded by purchasing upgrade from a shop

## Crafting
You can craft tools in the game from the crafting window.  
  * The resources needed to craft a tool must be in your pocket.  
  * Craftable tools are displayed on the left.  
  * Resources required to craft that item are on the right.  
  * Tools displayed in colour mean you have enough resources in your pocket to craft that tool.  
  * Resources displayed in colour mean you have enough of that resource in your pocket to craft the corresponding tool.  
  * You can view information about tools and resources by hovering your mouse over their image
  * To craft a tool, click a tool displayed in colour and click craft.  
  * The tool will be added to your pocket.  

### Craftable Items
The following tools must be in your pocket to gain their benefits  
**Axe** - cuts up to 3 wood from trees  
**Pickaxe** - mines up to 5 stones from rocks  
**Shovel** - digs up to 5 dirt from soil  
**Fishing Rod** - can catch fish with at fishing spots

## Player Health
Every player has a health bar. You can lose health by taking damage from other players,   
hitting trees, rocks and collecting soil without the correct tool in your pocket.  
You can regain health by eating food.
### Player Death
When a player loses all their health they die and drop all their items in the world in the form of a pile,  
while other players can pick up using 'F'. The dead player enters spectator mode where they can see all  
players in the world but are no longer part of the game.

## Saving the Game
The host player has the option of saving the game by going File -> Save. This saves an .xml file to the  
given directory. This kills all players and drops their items at their current location.

## Loading a Game
A player can load a previously saved game by going File -> Load from the Main Menu, and selecting their saved  
.xml file. The player must then host a game and other players can join. When the game starts, all the items  
dropped by each player from the save, will be in the same location.

## Disconnecting
If the host disconnects from the game, all players are disconnected and returned to the main menu.  
If a client disconnects, the game simply displays a message to all players that they have disonnected.
