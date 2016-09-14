package missing.main;

import java.util.ArrayList;

import missing.game.characters.Player;
import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.controller.VControl;
import missing.ui.views.MapView;

public class Main {
	
	
	public static void main(String[] args){
		Player player = new Player("Linus");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		try{
			World world = new World(players);
			VControl control = new VControl();
			MapView mapView = new MapView(control, world);
			Thread.sleep(1000);
			control.setCurrentView(mapView);
		}catch(GameException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			
		}
	}

}
