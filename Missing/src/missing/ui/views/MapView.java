package missing.ui.views;

import missing.game.world.World;
import missing.ui.assets.GWorld;

public class MapView extends View{
	
	private GWorld gWorld;
	
	public MapView(World w){
		gWorld = new GWorld(w);
	}

}
