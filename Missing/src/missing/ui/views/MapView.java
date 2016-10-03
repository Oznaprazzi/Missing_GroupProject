/*	File: MapPanel.java
 * 	Author
 * 	Casey Huang		300316284
 *  Edward Kelly	300334192
 *  
 * 	Date			Author				Changes
 * 	19 Sep 16		Casey Huang			created MapPanel class
 * 	19 Sep 16		Casey Huang			renamed MapView to MapPanel
 *  23 Sep 16		Edward Kelly		removed World param from constructor
 */
package missing.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import missing.helper.GameException;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.panels.MapPanel;

@SuppressWarnings("serial")
public class MapView extends View {
	/** If spectator, player can see all players and only has an exit button */
	private boolean isSpectator;
	private MapPanel mapPanel;
	public MapView(VControl controller) {
		super(controller);
		isSpectator = false;
	}

	@Override
	public void initialise() {
		this.setLayout(new BorderLayout());
		mapPanel = new MapPanel(controller.getGGame().getGWorld());
//		mapPanel.setPreferredSize(this.getPreferredSize());
		add(mapPanel, BorderLayout.CENTER);
		controller.pack();
	}
	
	/**
	 * Updates the MapPanel with the GWorld for the updated game
	 * @param controller updated controller
	 */
	public void updateMapPanel(VControl controller){
		this.controller = controller;
		mapPanel.updateGWorld(controller.getGGame().getGWorld());
	}
	
	public void setSpectator(boolean isSpectator){
		this.isSpectator = isSpectator;
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
