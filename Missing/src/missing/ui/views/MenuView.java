/*	File: MenuView.java
 * 	Authors:				ID:
 *  Linus Go				300345571
 * 	
 * 	Date:					Author					Changes
 *	14 Sep 16				Linus Go				Added the MenuView Class.
 */
package missing.ui.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

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

	/**
	 * This constructs a new instance of a MenuView.
	 * 
	 * @param controller
	 */
	public MenuView(VControl controller) {
		super(controller);
		this.initialiseLayout();
	}
	/**
	 * This method defines the action listeners and allows actions to be performed once the button is pressed.
	 */
	private void setupActionListeners() {
		btnSinglePlayer.addActionListener(e->{
			
		});
		
		btnMultiPlayer.addActionListener(e->{
			
		});
		
		btnAbout.addActionListener(e->{
			
		});
		
		btnExit.addActionListener(e->{
		System.exit(0);
		});
		
		
	}

	/**
	 * This method initializes all of the buttons and the layout.
	 */
	private void initialiseLayout() {
		setLayout(null);

		btnSinglePlayer = new JButton("Single Player");
		btnSinglePlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSinglePlayer.setBackground(Color.YELLOW);
		btnSinglePlayer.setBounds(370, 87, 200, 61);
		add(btnSinglePlayer);

		btnMultiPlayer = new JButton("Multi Player");
		btnMultiPlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMultiPlayer.setBackground(Color.YELLOW);
		btnMultiPlayer.setBounds(370, 156, 200, 61);
		add(btnMultiPlayer);

		btnAbout = new JButton("About \"Missing\"");
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAbout.setBackground(Color.YELLOW);
		btnAbout.setBounds(370, 224, 200, 61);
		add(btnAbout);

		btnExit = new JButton("Exit Game");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBackground(Color.YELLOW);
		btnExit.setBounds(369, 290, 200, 61);
		add(btnExit);

		btnSinglePlayer = new JButton("Start Single Player");
		btnMultiPlayer = new JButton("Multi Player");
		btnAbout = new JButton("About Game");
		btnExit = new JButton("Exit Game");
		//this.repaint();
		this.setupActionListeners();

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
	}

	@Override
	public void setFocus() {
	}
}
