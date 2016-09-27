/*File : playerMovingTests.java
 * 
 * Author			ID
 * Jian Wei Chong	300352789
 * 
 * Date				Author		Modification
 * 23/9/16			Jian Wei	Created the class
 * */
package missing.tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Scanner;

import org.junit.Test;

import missing.datastorage.assetloader.XMLHandler;
import missing.game.Game;
import missing.game.characters.Player;
import missing.game.world.World;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;

public class playerMovingTests {
	
	public static void main(String args[]){
		//movingTests_1();
	}


	@Test
	public void movingTests_1(){
		Player[] avatars = { new Player("Chris", new Point(1, 1), new Point(1, 1)) };
		boolean hitItem = false; //if the player tries to move onto an item square
		try {

			String xmlFile = "items.xml";
			XMLHandler.filename = xmlFile;
			Game game = new Game(avatars);
			Player chris = avatars[0];
			World world = game.getWorld();
			Scanner input = new Scanner(System.in);
			
			while(true){
				System.out.println("Choose the direction you want the player to move in(n,s,e,w)");
				System.out.println("press x to stop");
				Point wloc = chris.getWorldLocation();
				Point tloc = chris.getTileLocation();
				Direction dir = chris.getOrientation();
				boolean pressedx = false;
				if(input.hasNext()){
					switch(input.next()){
					case "n":
						game.movePlayer(0, Direction.NORTH);
						if(!(chris.getTileLocation().equals(tloc))){ //if player has moved
							assertTrue(chris.getOrientation() == Direction.NORTH); //assert direction has changed

						}
						System.out.println("old tile : "+tloc.getX() + " , "+tloc.getY());
						System.out.println("new tile : "+chris.getTileLocation().getX() + " , "+chris.getTileLocation().getY());
						System.out.println("Old direction = "+dir);
						System.out.println("New Direction = "+ chris.getOrientation());
						break;
					case "s":
						game.movePlayer(0, Direction.SOUTH);
						if(!(chris.getTileLocation().equals(tloc))){ //if player has moved
							assertTrue(chris.getOrientation() == Direction.SOUTH); //assert direction has changed
						}
						System.out.println("old tile : "+tloc.getX() + " , "+tloc.getY());
						System.out.println("new tile : "+chris.getTileLocation().getX() + " , "+chris.getTileLocation().getY());
						System.out.println("Old direction = "+dir);
						System.out.println("New Direction = "+ chris.getOrientation());
						break;
					case "e":
						game.movePlayer(0, Direction.EAST);
						if(!(chris.getTileLocation().equals(tloc))){ //if player has moved
							assertTrue(chris.getOrientation() == Direction.EAST); //assert direction has changed
						}
						System.out.println("old tile : "+tloc.getX() + " , "+tloc.getY());
						System.out.println("new tile : "+chris.getTileLocation().getX() + " , "+chris.getTileLocation().getY());
						System.out.println("Old direction = "+dir);
						System.out.println("New Direction = "+ chris.getOrientation());
						break;
					case "w":
						game.movePlayer(0, Direction.WEST);
						if(!(chris.getTileLocation().equals(tloc))){ //if player has moved
							assertTrue(chris.getOrientation() == Direction.WEST); //assert direction has changed
						}
						System.out.println("old tile : "+tloc.getX() + " , "+tloc.getY());
						System.out.println("new tile : "+chris.getTileLocation().getX() + " , "+chris.getTileLocation().getY());
						System.out.println("Old direction = "+dir);
						System.out.println("New Direction = "+ chris.getOrientation());
						break;
					case "x": 
						pressedx =true;
						break;
					default:
						System.out.println("press n,s,e,w or x");
						
					}
					
				}
				if(pressedx){
					break;
				}
			}
		}catch(GameException e){
			e.printStackTrace();
		}
	
	}
}
