/* File: GGame.java
 * Author:
 * Casey Huang		300316284
 * Linus Go			300345571
 * 
 * Date				Author				Changes
 * 19 Sep 16		Casey Huang			created GGame class
 * 19 Sep 16		Linus Go			added the fields
 * 19 Sep 16		Casey Huang			created getGame method
 */
package missing.ui.assets;

import java.awt.Point;

import missing.game.Game;
import missing.helper.GameException;
import missing.ui.controller.VControl.View;

/**
 * This class is a wrapper class to the World objects which contains methods
 * which are purely used for graphics and rendering.
 */
public class GGame {

	private GWorld gworld;
	private Game game;

	public GGame(Game game, View theView) throws GameException {
		this.game = game;
		gworld = new GWorld(game.getWorld(), theView, new Point(0, 0));
	}

	// Methods
	public GWorld getGWorld() {
		return gworld;
	}

	public Game getGame() {
		return game;
	}
}
