package missing.tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import missing.game.world.World;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.helper.GameException;
import missing.helper.WorldInitialiser;


public class WorldNodeParsingTests {
	
	public static void main(String[] args) throws GameException{
		WorldNode[][] worldNodes = WorldInitialiser.createWorldNodes();
//		for (int i=0; i<worldNodes.length; i++){
//			for (int j=0; j<worldNodes[0].length; j++){
//				System.out.println(worldNodes[i][j].toString());
//			}
//		}
		WorldTile[][] worldTiles = worldNodes[0][0].getWorldTiles();
		for (int i=0; i<worldTiles.length; i++){
			for (int j=0; j<worldTiles[i].length; j++){
				System.out.println(worldTiles[i][j].toString());
				System.out.println(worldTiles[i][j].getType().toString());
			}
		}
	}
}
