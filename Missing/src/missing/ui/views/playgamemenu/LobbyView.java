/* File: LobbyView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 * 25 Sep 16		Edward Kelly	created class
 * 6 Oct 16			Casey Huang		Added background
 */
package missing.ui.views.playgamemenu;

import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import missing.datastorage.assetloader.GameAssets;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;

/**
 * Displays the waiting image in the JFrame
 */
@SuppressWarnings("serial")
public class LobbyView extends View {
	public LobbyView(VControl controller) {
		super(controller);
		repaint();
		controller.pack();
	}

	@Override
	public void initialise() {
		setLayout(new GridBagLayout());
		createPlayerTablePanel();
		createButtonsPanel();

		JLabel title = MenuFactory.createHeading("Lobby");
		title.setHorizontalAlignment(SwingConstants.CENTER);

	}

	private void createPlayerTablePanel() {
		new JPanel(new GridBagLayout());

	}

	private void createButtonsPanel() {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(GameAssets.getSplashBackgroundImage(), 0, 0, null);
	}

	@Override
	public void setFocus() {
	}

}
