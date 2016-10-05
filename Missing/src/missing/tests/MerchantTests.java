/*File: MerchantTests.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * Date				Author			Modification
 * 4 oct 16			Jian Wei		Created the class, did buy1, sell1, buyInvalid1 & 2, sellInvalid1*/
package missing.tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import missing.game.characters.Merchant;
import missing.game.characters.Merchant.MerchantType;
import missing.game.characters.Player;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Wood;
import missing.helper.GameException;

public class MerchantTests {

	/**
	 * creates a general merchant and buys 2 items from it
	 */
	@Test
	public void buyTest_1() {
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Merchant merchant = new Merchant(worldLocation, tileLocation, MerchantType.GENERAL);
		player.setMoney(2);
		try {
			merchant.buyItem(player, "wood");
			merchant.buyItem(player, "axe");
			assertTrue(player.getPocket().getItems().get(0) instanceof Wood);
			assertTrue(player.getPocket().getItems().get(1) instanceof Tool);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tries to buy an item the merchant doesn't have, a game exception should
	 * be thrown
	 */
	@Test
	public void buyInvalidTest_1() {
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Merchant merchant = new Merchant(worldLocation, tileLocation, MerchantType.FOOD);
		player.setMoney(2);
		boolean cannotBuy = false;
		try {
			merchant.buyItem(player, "wood");
		} catch (GameException e) {
			cannotBuy = true;
		}
		assertTrue(cannotBuy);
	}

	/**
	 * tries to buy an item without enough money, a game exception should be
	 * thrown
	 */
	@Test
	public void buyInvalidTest_2() {
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Merchant merchant = new Merchant(worldLocation, tileLocation, MerchantType.GENERAL);
		player.setMoney(0);
		boolean cannotBuy = false;
		try {
			merchant.buyItem(player, "wood");
		} catch (GameException e) {
			cannotBuy = true;
		}
		assertTrue(cannotBuy);
	}

	/**
	 * creates a general merchant and sells wood to it
	 */
	@Test
	public void sellTest_1() {
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Merchant merchant = new Merchant(worldLocation, tileLocation, MerchantType.GENERAL);
		player.setMoney(2);
		Wood wood = new Wood(tileLocation, tileLocation, 3);

		try {
			player.addToPocket(wood);
			merchant.sellItem(player, wood);
			assertTrue(wood.getAmount() == 2);// amount of that wood object
												// should decrease by 1
			assertTrue(player.getMoney() == 3);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * tries to sell an item which isn't in its pocket. A gameException should
	 * be thrown
	 */
	@Test
	public void sellInvalidTest_1() {
		Player player = new Player(0, "Chris", new Point(1, 1), new Point(0, 1));
		Point worldLocation = new Point(1, 1);
		Point tileLocation = new Point(1, 1);
		Merchant merchant = new Merchant(worldLocation, tileLocation, MerchantType.GENERAL);
		player.setMoney(2);
		Wood wood = new Wood(tileLocation, tileLocation, 3);
		boolean cannotSell = false;
		try {
			merchant.sellItem(player, wood);
			assertTrue(wood.getAmount() == 2);// amount of that wood object
												// should decrease by 1
			assertTrue(player.getMoney() == 3);
		} catch (GameException e) {
			cannotSell = true;
		}
		assertTrue(cannotSell);
	}
}
