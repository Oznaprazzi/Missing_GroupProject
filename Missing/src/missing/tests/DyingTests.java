/*File : DyingTests.java
 * 
 * Authors				ID
 * Jian Wei Chong		300352789
 * 
 * Date					Author				Modification
 * 3/10/16				Jian Wei			created the class, death by tree, rock and soil tests*/
package missing.tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.movable.Resource;
import missing.game.items.nonmovable.Rock;
import missing.game.items.nonmovable.Soil;
import missing.game.items.nonmovable.Tree;
import missing.helper.GameException;
import missing.helper.SignalException;

public class DyingTests {

	/**
	 * puts the player on 1 health, and tests that when they hit the tree, they lose health and die*/
	@Test
	public void deathByTree_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Tree tree = new Tree(worldLocation, tileLocation);
		player.setHealth(1);
		boolean playerIsDead = false;
			try {
				tree.performAction(player);
				
			} catch (GameException | SignalException e) {
				// TODO Auto-generated catch block
				//System.out.println("exception thrown");
				//e.printStackTrace();
				playerIsDead = true;
			}
			assert(player.getHealth()==0);
			assertTrue(playerIsDead);
	}
	
	/**
	 * puts the player on 1 health, and tests that when they hit the rock, they lose health and die*/
	@Test
	public void deathByRock_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Rock rock = new Rock(worldLocation, tileLocation);
		player.setHealth(1);
		boolean playerIsDead = false;
			try {
				rock.performAction(player);
				
			} catch (GameException | SignalException e) {
				// TODO Auto-generated catch block
				//System.out.println("exception thrown");
				//e.printStackTrace();
				playerIsDead = true;
			}
			assert(player.getHealth()==0);
			assertTrue(playerIsDead);
	}
	
	/**
	 * puts the player on 1 health, and tests that when they hit the soil, they lose health and die*/
	@Test
	public void deathBySoil_1(){
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Soil soil = new Soil(worldLocation, tileLocation);
		player.setHealth(1);
		boolean playerIsDead = false;
			try {
				soil.performAction(player);
			} catch (GameException | SignalException e) {
				// TODO Auto-generated catch block
				//System.out.println("exception thrown");
				//e.printStackTrace();
				playerIsDead = true;
			}
			assert(player.getHealth()==0);
			assertTrue(playerIsDead);
	}
}
