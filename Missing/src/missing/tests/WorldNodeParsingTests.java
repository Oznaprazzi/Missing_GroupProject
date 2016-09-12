/*	File: WorldNodeParsingTests.java
 * 	Author:
 * 	Edward Kelly		300334192
 * 
 * 	Date			Author				changes
 * 	12 Sep 16		Edward Kelly		created class
 */
package missing.tests;

import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.helper.GameException;
import missing.helper.WorldInitialiser;

/**
 * Tests WorldNode parsing by printing all WorldNode objects in the World
 *
 */
public class WorldNodeParsingTests {
	
	public static void main(String[] args) throws GameException{
		//print tiles
		WorldNode[][] worldNodes = WorldInitialiser.createWorldNodes();
		for (int y=0; y<worldNodes.length; y++){
			for (int x=0; x<worldNodes[0].length; x++){
				WorldTile[][] worldTiles = worldNodes[0][0].getWorldTiles();
				for (int i=0; i<worldTiles.length; i++){
					for (int j=0; j<worldTiles[i].length; j++){
						WorldTile tile = worldTiles[i][j];
						System.out.print(tile.toString());
						if (j==worldTiles[i].length-1)System.out.println("");
					}
				}
				System.out.println("");
			}
		}
	}
}
