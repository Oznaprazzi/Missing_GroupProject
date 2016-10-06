/*	File: MenuView.java
 * 	Authors:				ID:
 *  Linus Go				300345571
 *  Casey Huang				300316284
 * 	
 * 	Date:					Author					Changes
 *	14 Sep 16				Linus Go				Added the MenuView Class.
 *	15 Sep 16				Linus Go				Added buttons for the MenuView class.
 *  3 Oct 16				Linus Go				Tidied up the MenuView class, made code cleaner and layout better.
 *  6 Oct 16				Casey Huang				Added new background and added 2 other JLabels
 */
package missing.ui.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import missing.datastorage.assetloader.GameAssets;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;

@SuppressWarnings("serial")
public class MenuView extends View{

	private List<JButton> jButtonList = new ArrayList<>();

	/**The credit message String */
	private String creditMessage = "\"Missing\" was Created for our SWEN 222 Group Project, and was intended to be an Adventure Game."
			+ "\nWe had to work with 5 people on this game, and spent many hours and days over this project."
			+ "\nThe Team: "
			+ "\n (1) Linus Go\n (2) Edward Kelly\n (3) Jian Wei Chong\n (4) Christiandel Rabe\n (5) Casey Huang";



	private JPanel buttonPanel;

	public MenuView(VControl controller) {
		super(controller);
		this.initialise();
	}

	@Override
	public void initialise() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		JLabel title = MenuFactory.createHeading("Missing");
		add(title,c);

		c.gridy = 1;
		JLabel label = MenuFactory.createLabel2("Game Menu"); 
		add(label,c);

		createButtons();

		c.weighty = 2;
		c.anchor = GridBagConstraints.NORTH;
		c.gridy = 2;
		add(buttonPanel,c);
	}

	/**
	 * Creates button panel
	 */
	private void createButtons(){
		GridLayout layout = new GridLayout(3,0);
		layout.setVgap(20);
		buttonPanel = new JPanel(layout);
		buttonPanel.setOpaque(false);

		JButton btnHostGame = MenuFactory.createButton("Play Game");
		jButtonList.add(btnHostGame);
		btnHostGame.addActionListener(e -> {
			controller.changeView(controller.getPlayGameView()); // change to main Menu
		});

		JButton btnJoinGame = MenuFactory.createButton("About \"Missing\"");
		jButtonList.add(btnJoinGame);
		btnJoinGame.addActionListener(e -> {
			JOptionPane.showConfirmDialog(null, creditMessage, "About Missing: The Game", JOptionPane.PLAIN_MESSAGE);
		});

		JButton btnBack = MenuFactory.createButton("Exit");
		jButtonList.add(btnBack);
		btnBack.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if(option == 0) System.exit(0);
			return;
		});

		for(JButton j : jButtonList){
			j.setFocusable(false);
			buttonPanel.add(j);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(GameAssets.getSplashBackgroundImage(), 0, 0, null);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
