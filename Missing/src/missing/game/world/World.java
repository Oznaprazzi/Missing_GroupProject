/*	File: World.java
 * 	Author:
 * 	Chris Rabe		300334207
 *  Edward Kelly	300334192
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Chris Rabe			created world class
 *  8 Sep 16		Edward Kelly		created constructor, setupWorld, setLocation
 *  13 Sep 16		Chris Rabe			removed some non-related fields to the world node
 */

package missing.game.world;
import java.util.List;

import missing.game.characters.Player;
import missing.game.world.nodes.WorldNode;
import missing.helper.GameException;
import missing.helper.WorldInitialiser;

/**
 * This class contains all the logic for moving around the players within the
 * game world as well as performing appropriate checking if item and players
 * collide.
 */
public class World {
	public static final int WORLD_WIDTH = 4;
	public static final int WORLD_HEIGHT = 4;
	
	/** The WorldNodes that make up the World */
	private WorldNode[][] worldNodes;
	/** Reference to the players in the world */
	private List<Player> players;
	
	public World(List<Player> players) throws GameException{
		this.players = players;
		setupWorld();
	}

	/**
	 * Creates the world, places items and players
	 * @throws GameException 
	 */
	private void setupWorld() throws GameException{		
		//Create worldNodes
		worldNodes = WorldInitialiser.createWorldNodes();
		worldNodes = WorldInitialiser.linkNodes(worldNodes);
	}
}
