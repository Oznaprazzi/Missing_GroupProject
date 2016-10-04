/*File: Merchant.java
 * 
 * Authors			ID
 * Jian Wei Chong	300352789
 * 
 * Date				Author			Modification
 * 4 oct 16			Jian Wei		created the class, implemented initialiseCosts and buyitem, sellitem*/
package missing.game.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import missing.game.items.movable.Dirt;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Wood;
import missing.helper.GameException;
import missing.helper.SignalException;

public class Merchant extends Character{

	public enum MerchantType{
		GENERAL, FOOD, TOOLS //the 3 types of merchants
	}
	private MerchantType mType;
	private HashMap<String, Movable> namesToItems = new HashMap<String, Movable>();
	private HashMap<Movable, Integer> costs = new HashMap<Movable, Integer> (); 

	
	public Merchant(String name, String description, Point worldLocation, Point tileLocation, Direction orientation,
			Direction approach, MerchantType mType) {
		super(name, description, worldLocation, tileLocation, orientation, approach);
		// TODO Auto-generated constructor stub
		this.mType = mType;
		initialiseCosts();
	}

	@Override
	public void performAction(Player player) throws GameException, SignalException {
		// TODO Auto-generated method stub
		
	}
	
	
	/**Fills up the map of trades. Initialises the cost of each item*/
	public void initialiseCosts(){
		switch(mType){
		case GENERAL:
			initialiseGeneralMerchantCosts();
		case FOOD:
			initialiseFoodMerchantCosts();
		case TOOLS:
			initialiseToolsMerchantCosts();
		}
	}
	
	
	public void initialiseGeneralMerchantCosts(){
		//food
		Movable apple= new Food(null,null, FoodType.APPLE);
		Movable berry = new Food(null,null ,FoodType.BERRY);
		Movable fish= new Food(null,null,FoodType.FISH);
		//tools
		Movable axe = new Tool(null,null,ToolType.AXE);
		Movable rod= new Tool(null,null,ToolType.FISHINGROD);
		Movable pickaxe= new Tool(null,null,ToolType.PICKAXE);
		//resources
		Movable wood = new Wood(null,null);
		Movable stone = new Stone(null,null);
		Movable dirt = new Dirt(null,null);
		
		namesToItems.put("apple", apple);
		namesToItems.put("berry", berry);
		namesToItems.put("fish", fish);
		namesToItems.put("axe", axe);
		namesToItems.put("rod", rod);
		namesToItems.put("pickaxe", pickaxe);
		namesToItems.put("wood", wood);
		namesToItems.put("stone", stone);
		namesToItems.put("dirt", dirt);
		
		costs.put(apple, 1);
		costs.put(berry, 1);
		costs.put(fish, 1);
		costs.put(axe, 1);
		costs.put(rod, 1);
		costs.put(pickaxe, 1);
		costs.put(wood, 1);
		costs.put(stone, 1);
		costs.put(dirt, 1);
	}
	
	
	public void initialiseFoodMerchantCosts(){
		Movable apple= new Food(null,null, FoodType.APPLE);
		Movable berry = new Food(null,null ,FoodType.BERRY);
		Movable fish= new Food(null,null,FoodType.FISH);
		
		namesToItems.put("apple", apple);
		namesToItems.put("berry", berry);
		namesToItems.put("fish", fish);
		
		costs.put(apple, 1);
		costs.put(berry, 1);
		costs.put(fish, 1);
		
	}
	
	
	public void initialiseToolsMerchantCosts(){
		Movable axe = new Tool(null,null,ToolType.AXE);
		Movable rod= new Tool(null,null,ToolType.FISHINGROD);
		Movable pickaxe= new Tool(null,null,ToolType.PICKAXE);
		
		namesToItems.put("axe", axe);
		namesToItems.put("rod", rod);
		namesToItems.put("pickaxe", pickaxe);
		
		costs.put(axe, 1);
		costs.put(rod, 1);
		costs.put(pickaxe, 1);
	}

	/**
	 * buys item from merchant*/
	public void buyItem(Player player, String item) throws GameException{
		Movable i = namesToItems.get(item);
		if(i==null) throw new GameException("the merchant doesn't have this item");
		int costOfItem = costs.get(i);
		if(player.getMoney()<costOfItem){
			throw new GameException("player doesn't have enough moolah");
		}
		player.setMoney(player.getMoney() - costOfItem); //deducts the cost from the players money
		Movable playerItem = i;
		player.addToPocket(playerItem);
	}
	
	/**
	 * player sells one amount of an item to the merchant*/
	public void sellItem(Player p, Movable item) throws GameException{
		if(!p.has(item)) throw new GameException("cannot sell item you dont have");
		int sellAmount = costs.get(findItemInMap(item));
		p.setMoney(p.getMoney() + sellAmount);
		item.setAmount(item.getAmount() - 1); //reduces the amount of the item, as the player is giving it to the merchant
		if(item.getAmount() == 0) p.removeFromPocket(item);
		
	}
	
	
	public Movable findItemInMap(Movable item){
		for(Movable key : costs.keySet()){
			if(item.getClass() == key.getClass()){
				return key;
			}
		}
		return null;
	}
}
