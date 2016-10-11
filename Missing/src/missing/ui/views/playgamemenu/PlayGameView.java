/*	File: PlayGameView.java
 * 	Authors:				ID:
 *  Edward Kelly			300334192
 *  Casey Huang				300316284
 * 	
 * 	Date:			Author				Changes
 *  23 Sep 16		Edward Kelly		Created class
 *  25 Sep 16		Edward Kelly		Integrated with MenuComponent methods
 *  6 Oct 16		Casey Huang			Added background
 */
package missing.ui.views.playgamemenu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import missing.datastorage.assetloader.GameAssets;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;

/**
 * Contains buttons for hosting and joining the game
 */
@SuppressWarnings("serial")
public class PlayGameView extends View {

	private JPanel buttonPanel;

	public PlayGameView(VControl controller) {
		super(controller);
		this.initialise();
	}

	@Override
	public void initialise() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel title = MenuFactory.createHeading("Play Game");
		title.setHorizontalAlignment(SwingConstants.CENTER);

		createButtons();

		c.weighty = 0.2;
		add(title, c);
		c.anchor = GridBagConstraints.NORTH;
		c.gridy = 1;
		add(buttonPanel, c);
	}

	/**
	 * Creates button panel
	 */
	private void createButtons() {
		GridLayout layout = new GridLayout(3, 0);
		layout.setVgap(20);
		buttonPanel = new JPanel(layout);
		buttonPanel.setOpaque(false);

		JButton btnHostGame = MenuFactory.createButton("Host Game");
		btnHostGame.addActionListener(e -> {
			controller.changeView(controller.getHostGameView());
		});

		JButton btnJoinGame = MenuFactory.createButton("Join Game");
		btnJoinGame.addActionListener(e -> {
			controller.changeView(controller.getJoinGameView());
		});

		JButton btnBack = MenuFactory.createButton("Back");
		btnBack.addActionListener(e -> {
			controller.changeView(controller.getMenuView());
		});

		buttonPanel.add(btnHostGame);
		buttonPanel.add(btnJoinGame);
		buttonPanel.add(btnBack);
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
