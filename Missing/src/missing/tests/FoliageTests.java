/*File: FoliageTests.java
 * 
 * Authors			ID
 * Jian Wei			300352789
 * 
 * Date				Author			Modification
 * 30 Sep 16		Jian Wei		created the class, added bush test 1,2 and invalid 1
 * 7 oct 16			Jian Wei		updated invalid 1 to put a 2 second delay in between bush.performAction
 * */
package missing.tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.Item;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.nonmovable.Bush;
import missing.helper.GameException;

public class FoliageTests {

	/**
	 * Tests that a player can get a berry from the bush*/
	@Test
	public void BushTest_1(){
		Player player = new Player(0,"Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Bush bush = new Bush(worldLocation, tileLocation);
		try{
			bush.performAction(player);
			if(player.getPocket().getItems().size() == 1){
				Item item = player.getPocket().getItems().get(0);
				assert(item instanceof Food);
				assert(((Food) item).getFoodType() == FoodType.BERRY);
			}
		}catch(GameException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Player gets one berry from bush, then tests that they can get a second one after 10s,
	 * as the bush should have replenished by then*/
	@Test
	public void BushTest_2(){
		Player player = new Player(0,"Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Bush bush = new Bush(worldLocation, tileLocation);
		try{
			bush.performAction(player);
			if(player.getPocket().getItems().size() == 1){
				Item item = player.getPocket().getItems().get(0);
				assert(item instanceof Food);
				assert(((Food) item).getFoodType() == FoodType.BERRY);
			}
			TimeUnit.SECONDS.sleep(11);
			bush.performAction(player);
			if(player.getPocket().getItems().size() == 1 ||player.getPocket().getItems().size() == 2){
				int last = player.getPocket().getItems().size() - 1;
				Item item = player.getPocket().getItems().get(last);
				assert(item instanceof Food);
				assert(((Food) item).getFoodType() == FoodType.BERRY);
			}
		}catch(GameException | InterruptedException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Player gets one berry from bush, then tries to take another berry 2 seconds later. tests that a
	 * gameException is thrown
	 * */
	@Test
	public void Bush_Invalid_Test_1(){
		Player player = new Player(0,"Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Bush bush = new Bush(worldLocation, tileLocation);
		boolean exception = false;
		try{
			bush.performAction(player);
			if(player.getPocket().getItems().size() == 1){
				Item item = player.getPocket().getItems().get(0);
				assert(item instanceof Food);
				assert(((Food) item).getFoodType() == FoodType.BERRY);
			}
			TimeUnit.SECONDS.sleep(2);
			bush.performAction(player);
		}catch(GameException | InterruptedException  e){
			exception = true;
		}
		assertTrue(exception);
	}
	
}
