/*	File: MenuView.java
 * 	Authors:				ID:
 *  Linus Go				300345571
 * 	
 * 	Date:					Author					Changes
 *	14 Sep 16				Linus Go				Added the MenuView Class.
 *	15 Sep 16				Linus Go				Added buttons for the MenuView class.
 *  3 Oct 16				Linus					Tidied up the MenuView class, made code cleaner and layout better.
 */
package missing.ui.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;

public class MenuView extends View{
	
	
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
		
		JLabel title = MenuFactory.createHeading("Missing: The Game");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		createButtons();
		
		c.weighty = 0.2;
		add(title,c);
		c.anchor = GridBagConstraints.NORTH;
		c.gridy = 1;
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
		btnHostGame.setFocusable(false);
		btnHostGame.addActionListener(e -> {
			controller.changeView(controller.getPlayGameView()); // change to main Menu
		});
		
		JButton btnJoinGame = MenuFactory.createButton("About \"Missing\"");
		btnJoinGame.setFocusable(false);
		btnJoinGame.addActionListener(e -> {
			JOptionPane.showConfirmDialog(null, creditMessage, "About Missing: The Game", JOptionPane.PLAIN_MESSAGE);
		});
		
		JButton btnBack = MenuFactory.createButton("Exit");
		btnBack.setFocusable(false);
		btnBack.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == 0) System.exit(0);
			return;
			});
		
		buttonPanel.add(btnHostGame);
		buttonPanel.add(btnJoinGame);
		buttonPanel.add(btnBack);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int wd = super.getSize().width;
		int ht = super.getSize().height;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, wd, ht);
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
