package missing.tests;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;


import missing.datastorage.assetloader.XMLHandler;
import missing.game.Game;
import missing.game.characters.Player;
import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;

public class GameViewTests {
	public static void main(String[] args) {
		// XMLHandler received file name...
		String xmlFile = "items.xml";
		XMLHandler.filename = xmlFile;
		//Create an array of players
		Player[] avatars = { new Player("Chris", new Point(3, 3), new Point(9, 9)) };
		try {
			
			Game game = new Game(avatars);
			Player chris = avatars[0];
			World world = game.getWorld();
			TestWindow window = new TestWindow(world, chris);
		} catch (GameException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

}

