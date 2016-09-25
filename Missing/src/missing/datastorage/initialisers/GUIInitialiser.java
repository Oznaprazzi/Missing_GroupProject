/* File: GUIInitialiser.java
 * Author:			ID
 * Linus Go			300345571
 * Chris Rabe		300334207
 * Edward Kelly		300334192
 * 
 * Date				Author				Changes
 * 13 Sep 16		Linus Go			created GUIInitialiser.java
 * 13 Sep 16		Linus Go			added initialiseGNodes method.
 * 13 Sep 16		Chris Rabe			implemented intialiseGNodes method
 * 13 Sep 16		Chris Rabe			implemented initialiseGTiles method
 * 13 Sep 16		Chris Rabe			made methods throw GameException
 * 18 Sep 16		Linus Go			added GameView into createViews
 * 24 Sep 16		Edward Kelly		added play, host and join game views
 */
package missing.datastorage.initialisers;

import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.helper.GameException;
import missing.ui.assets.GWTile;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.panels.MapPanel;
import missing.ui.views.*;
import missing.ui.views.playgame.HostGameView;
import missing.ui.views.playgame.JoinGameView;
import missing.ui.views.playgame.PlayGameView;
import missing.game.world.World;
import missing.ui.assets.GWNode;

public class GUIInitialiser {

	public static GWTile[][] initialiseGTiles(WorldNode node, int tileSize) throws GameException {
		WorldTile[][] tiles = node.getWorldTiles();
		GWTile[][] temp = new GWTile[tiles.length][tiles[0].length];
		int size = tileSize / temp.length;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				temp[i][j] = new GWTile(tiles[i][j], size);
			}
		}
		return temp;
	}

	public static GWNode[][] initialiseGNodes(World world, int nodeSize) throws GameException {
		WorldNode[][] nodes = world.getWorldNodes();
		GWNode[][] temp = new GWNode[nodes.length][nodes[0].length];
		int size = nodeSize / temp.length;
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				temp[i][j] = new GWNode(nodes[i][j], size);
			}
		}
		return temp;
	}

	public static View[] createViews(VControl control) {
		// TODO append new views at end, increase views variable
		int views = 7;
		View[] tmp = new View[views];
		tmp[0] = new SplashView(control);
		tmp[1] = new MenuView(control);
		tmp[2] = new MapPanel(control);
		//tmp[3] = new GamePanel(control, control.getWorld());
		tmp[4] = new PlayGameView(control);
		tmp[5] = new HostGameView(control);
		tmp[6] = new JoinGameView(control);
		return tmp;
	}
}
