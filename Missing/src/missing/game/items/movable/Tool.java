/*File : Tool.java
 * 
 * Authors    		ID
 * Jian Wei Chong	300352789
 * 
 * Date 		Author		Modification
 * 18/9/16		Jian Wei	created the class
 * 19/9/16		Jian Wei	added the creation of axe and pickaxe
 * 
 * */
package missing.game.items.movable;
import java.awt.Point;
import java.util.ArrayList;

import missing.game.items.Item;
import missing.game.items.nonmovable.Source;
/**
 * This class represents a Tool item in the Game. It extends the Craftable,
 * Usable, Movable and Item subclasses
 */
import missing.helper.GameException;
public class Tool extends Craftable{
	
	public enum ToolType{
		AXE, PICKAXE
	}
	
	private int health = 10; // number of times tool can be used before it breaks
	private ToolType toolType;
	
	private ArrayList<Resource> resources = new ArrayList<Resource>();
	
	/**
	 * Creates instance of Tool class.
	 * 
	 * @param name
	 * @param description
	 * @param worldLocation
	 * @param tileLocation
	 * @param amount
	 * @param size
	 */
	
	public Tool(ToolType toolType , ArrayList<Resource> resources ,Point worldLocation, Point tileLocation)throws GameException {
		super(null, null, worldLocation, tileLocation,1,1);
		this.resources = resources;
		if(!createTool(toolType)) throw new GameException("You had the wrong items"); //if creating the tool was unsuccessful
	}
	
//	public Tool(String name, String description, Point worldLocation, Point tileLocation, int amount, int size) {
//		super("Tool", "Can be used to collect resources", worldLocation, tileLocation, amount, size);
//		// TODO Auto-generated constructor stub
//	}
	
	
	private ToolType getType(){
		return toolType;
	}
	
	private int getHealth(){
		return health;
	}
	
	/**
	 * Determines what type of tool is being created, then tries to create that tool.
	 * if valid, return true*/
	public boolean createTool(ToolType type){
		
		switch(type){
			case AXE:
				toolType = type;
				if(createAxe()) return true;
				return false;
			case PICKAXE:
				toolType = type;
				if(createPickaxe())return true;
				return false;
			default:
				return false;
		}
		
	}
	
	/**
	 * checks that there are 2 wood and 3 stone inside the resources list, 
	 * which are the required resources to create an Axe*/
	public boolean createAxe(){
		
		int woodCount = 0;
		int stoneCount = 0;
		
		for(Item item : resources){
			if(item instanceof Wood) woodCount++;
			else if(item instanceof Stone) stoneCount++;
		}
		if(woodCount!=2)return false;
		if(stoneCount!=3)return false;
		return true;
	}
	
	/**
	 * checks that there are 2 wood and 3 stone inside the resources list
	 *  which are the required resources to create an Pickaxe*/
	public boolean createPickaxe(){
		int woodCount = 0;
		int stoneCount = 0;
		
		for(Item item : resources){
			if(item instanceof Wood) woodCount++;
			else if(item instanceof Stone) stoneCount++;
		}
		if(woodCount!=2)return false;
		if(stoneCount!=3)return false;
		return true;
	}
	
	/**
	 * uses the tool.*/
	public void useTool(){
		health--;
		//TODO: verify that what they are trying to do requires that tool
	}

}
