/* File: GameView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 28 Sep 16		Edward Kelly	created class
 */
package missing.ui.views;

import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.panels.GamePanel;

/**
 * Represents the View which displays the GamePanel and other
 * components to be displayed when playing the game
 *
 */
public class GameView extends View{
	private GamePanel gamePanel;
	
	//TODO: 
	public GameView(VControl controller) {
		super(controller);
	}

	@Override
	public void initialise() {
		gamePanel = new GamePanel(controller);
		gamePanel.initialise();
		this.add(gamePanel);
		
	}
	
	/**
	 * Updates the GamePanel with the controller with the updated game
	 * @param controller updated controller
	 */
	public void updateGamePanel(VControl controller){
		this.controller = controller;
		gamePanel.setController(controller);
		gamePanel.initialise();
	}

	@Override
	public void setFocus() {
		gamePanel.setFocusable(true);
		
	}

}
