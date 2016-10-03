/*	File: PlayGameView.java
 * 	Authors:				ID:
 *  Edward Kelly			300334192
 * 	
 * 	Date:			Author				Changes
 *  23 Sep 16		Edward Kelly		Created class
 *  25 Sep 16		Edward Kelly		Integrated with MenuComponent methods
 */
package missing.ui.views.playgamemenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;

@SuppressWarnings("serial")
public class PlayGameView extends View{
	
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
