package missing.ui.views;

import java.util.ArrayList;

import missing.game.characters.Player;
import missing.game.world.World;
import missing.helper.GameException;

public class StringTest {
	public static void main(String[] args) {
		try {
			World w = new World(new ArrayList<Player>());
			System.out.println(w.toString());
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
}
