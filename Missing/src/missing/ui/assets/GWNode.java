package missing.ui.assets;

import missing.game.world.nodes.WorldNode;

public class GWNode {
	private WorldNode node;
	private GWTile[][] GWTiles = new GWTile[10][10];
	public GWNode(WorldNode n){
		node = n;
		initialiseGWTiles();
	}
	
	public void initialiseGWTiles(){
		
	}

}
