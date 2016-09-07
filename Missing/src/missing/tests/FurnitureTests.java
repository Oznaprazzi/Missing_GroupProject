package missing.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import missing.game.characters.Player;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.nonmovable.Fireplace;
import missing.helper.GameException;




/*
 * AUTHORS 
 * Jian Wei Chong    300352789
 * 
 * Updates : 
 * 7/9 - Added tests 1 and 2 for fireplace
 * 
 */
/**
 * these are test for all the items which extend furniture*/
public class FurnitureTests {


	/**
	 * Creates a player and a fireplace, then performs the fireplace's action. Test ensures player has 1 item in pocket after action*/
	@Test
	public void fireplaceTest_1(){
		Player player  = new Player("Chris");
		Point worldLocation = new Point(1,1);
		Point tileLocation = new Point(1,1);
		Food food  = new Food(worldLocation, tileLocation, FoodType.APPLE);
		Fireplace fireplace = new Fireplace(worldLocation, tileLocation);
		fireplace.setFood(food);
		try{
			player.addToPocket(food);
		    fireplace.performAction(player); //player takes wood from tree
		}catch(GameException e){
			e.printStackTrace();
		}
		assertEquals(player.getPocket().size(), 1); //checks that player has 1 item in pocket
	}
	
	/**
	 * Creates a player and a fireplace, then performs the fireplace's action. This test checks that the 
	 * food in the fireplace has been cooked*/
	@Test
	public void fireplaceTest_2(){
		Player player  = new Player("Chris");
		Point worldLocation = new Point(1,1);
		Point tileLocation = new Point(1,1);
		Food food  = new Food(worldLocation, tileLocation, FoodType.APPLE);
		Fireplace fireplace = new Fireplace(worldLocation, tileLocation);
		fireplace.setFood(food);
		
		try{
			player.addToPocket(food);
		    fireplace.performAction(player); //player takes wood from tree
		}catch(GameException e){
			e.printStackTrace();
		}
		boolean foodIsCooked = food.isCooked();
		assertTrue("Food is cooked", foodIsCooked); //checks that player has 1 item in pocket
	}
	
}
