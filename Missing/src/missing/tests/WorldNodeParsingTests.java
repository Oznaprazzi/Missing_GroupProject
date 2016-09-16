/*	File: WorldNodeParsingTests.java
 * 	Author:
 * 	Edward Kelly		300334192
 * 
 * 	Date			Author				changes
 * 	12 Sep 16		Edward Kelly		created class
 */
package missing.tests;

import missing.game.world.World;
import missing.helper.GameException;

/**
 * Tests WorldNode parsing by printing all WorldNode objects in the World.
 * Cannot do a JUnit test because we have to visually see the data being passed.
 */
public class WorldNodeParsingTests {

	/** Print out the whole world */
	public static void test_01() {
		System.out.println("test_01");
		try {
			World world = new World();
			System.out.println(world.toString());
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WorldNodeParsingTests.test_01();
	}
}
