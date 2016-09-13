package missing.ui.views;

import java.awt.Graphics;
import java.awt.Point;

import missing.game.world.World;
import missing.helper.GameException;
import missing.ui.assets.GWorld;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;

public class MapView extends View {

	private GWorld gWorld;

	public MapView(VControl controller, World w) {
		super(controller);
		try{
			gWorld = new GWorld(w,this, new Point(0,0));
		}catch(GameException e){
			e.printStackTrace();
		}
		// TODO Fill this in
	}

	@Override
	public void initialise() {
		// TODO Auto-generated method stub
		

	}
	
	/**
	 * Draws the map*/
	@Override
	public void draw(Graphics g){
		try{
			gWorld.draw(g);
		}catch(GameException e){
			e.printStackTrace();
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
