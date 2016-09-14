/*	File: MenuView.java
 * 	Authors:				ID:
 *  Linus Go				300345571
 * 	
 * 	Date:					Author					Changes
 *	14 Sep 16				Linus Go				Added the MenuView Class.
 *	15 Sep 16				Linus Go				Added buttons for the MenuView class.
 */
package missing.ui.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class represents the MenuView - that is the main menu that appears once
 * the game is loaded. It contains JButtons that perform actions once they have
 * been pressed.
 *
 */
@SuppressWarnings("serial")
public class MenuView extends View {

	private JButton btnSinglePlayer;
	private JButton btnMultiPlayer;
	private JButton btnAbout;
	private JButton btnExit;
	private final int buttonWd = 250;
	private final int buttonHt = 61;
	private final int buttonX = 360;
	/**The credit message String */
	private String creditMessage = "\"Missing\" was Created for our SWEN 222 Group Project, and was intended to be an Adventure Game."
			+ "\nWe had to work with 5 people on this game, and spent many hours and days over this project."
			+ "\nThe Team: "
			+ "\n (1) Linus Go\n (2) Edward Kelly\n (3) Jian Wei Chong\n (4) Christiandel Rabe\n (5) Casey Huang";

	/**
	 * This constructs a new instance of a MenuView.
	 * 
	 * @param controller
	 */
	public MenuView(VControl controller) {
		super(controller);
		this.initialise();
	}

	/**
	 * This method initializes all of the buttons and the layout.
	 */
	private void initialiseLayout() {
		setLayout(null); // use the absolute layout.

		btnSinglePlayer = new JButton("Single Player");
		btnSinglePlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSinglePlayer.setBackground(Color.YELLOW);
		btnSinglePlayer.setBounds(buttonX, 87, buttonWd, buttonHt);
		add(btnSinglePlayer);
		btnSinglePlayer.addActionListener(e -> {
			controller.changeView(controller.getMapView()); // change to main												// Menu
		});
		
		btnMultiPlayer = new JButton("Multi Player");
		btnMultiPlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMultiPlayer.setBackground(Color.YELLOW);
		btnMultiPlayer.setBounds(buttonX, 156, buttonWd, buttonHt);
		add(btnMultiPlayer);
		btnMultiPlayer.addActionListener(e -> {
		 //TODO : fill me once multiplayer is implemented.
		});

		btnAbout = new JButton("About \"Missing\"");
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAbout.setBackground(Color.YELLOW);
		btnAbout.setBounds(buttonX, 224, buttonWd, buttonHt);
		add(btnAbout);
		btnAbout.addActionListener(e -> {
		JOptionPane.showConfirmDialog(null, creditMessage, "About Missing: The Game", JOptionPane.PLAIN_MESSAGE);
		});

		btnExit = new JButton("Exit Game");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBackground(Color.YELLOW);
		btnExit.setBounds(buttonX, 290, buttonWd, buttonHt);
		add(btnExit);
		btnExit.addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Confirmation",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == 0){
			Runtime.getRuntime().exit(0);
			}else{
				return;
			}
			
		});

		btnSinglePlayer = new JButton("Start Single Player");
		btnMultiPlayer = new JButton("Multi Player");
		btnAbout = new JButton("About Game");
		btnExit = new JButton("Exit Game");
		// this.repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int wd = super.getSize().width;
		int ht = super.getSize().height;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, wd, ht);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.ITALIC, 36));
		g.drawString("MAIN MENU", (wd / 3), 50);
	}

	@Override
	public void initialise() {
		this.initialiseLayout();
	}

	@Override
	public void setFocus() {
	}
}
