package missing.ui.views;

import missing.game.world.World;
import missing.ui.assets.GWorld;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;

public class MapView extends View {

	private GWorld gWorld;

	public MapView(VControl controller, World w) {
		super(controller);
		gWorld = new GWorld(w);
	}

	@Override
	public void initialise() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
