/* File: HostGameView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 23 Sep 16		Edward Kelly	created class
 * 24 Sep 16		Edward Kelly	added function to start server
 */
package missing.ui.views.playgamemenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import missing.networking.NetworkingHelper;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuComponent;

/**
 * Displays the view for entering details for hosting a game
 *
 */
public class HostGameView extends View{
	
	/** Displays components for entering info for game */
	private JPanel gameInfoPanel;
	/** Panel that holds buttons */
	private JPanel buttonPanel;
	/** Holds gameInfo and button panels */
	private JPanel centrePanel;
	/** The player's name */
	private String playerName;
	/** Number of players to be in game */
	private int numPlayers;
	/** Port game is hosted on */
	private int port;
	/** Text entry for playerName */
	private TextField playerNameEntry;
	/** Text entry for numPlayers */
	private TextField numPlayersEntry;
	/** Text entry for port */
	private TextField portEntry;
	
	public HostGameView(VControl controller) {
		super(controller);
		this.initialise();
		repaint();
		controller.pack();
	}

	@Override
	public void initialise() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		createCentrePanel();

		JLabel title = MenuComponent.createHeading("Host Game");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 1;
		add(title,c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(centrePanel,c);
	}
	
	/**
	 * Creates centrePanel containing gameInfoPanel and buttonPanel
	 */
	private void createCentrePanel() {
		createInfoPanel();
		createButtonPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		centrePanel = new JPanel();
		centrePanel.setLayout(layout);
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,50,0);
		centrePanel.add(gameInfoPanel, c);
		c.gridx = 0;
		c.gridy = 1;
		centrePanel.add(buttonPanel, c);
		centrePanel.setOpaque(false);
		
	}
	
	/**
	 * Creates infoPanel containing numPlayersEntry, portEntry
	 * and corresponding labels
	 */
	private void createInfoPanel(){
		GridLayout layout = new GridLayout(3,2);
		layout.setVgap(20);
		layout.setHgap(20);
		gameInfoPanel = new JPanel(layout);
		
		// Create labels
		JLabel playerNameLabel = MenuComponent.createLabel("Player Name");
		JLabel numPlayerLabel = MenuComponent.createLabel("Number of Players");
		JLabel portLabel = MenuComponent.createLabel("Port");
		playerNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numPlayerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Create text fields
		playerNameEntry = MenuComponent.createTextField(200);
		numPlayersEntry = MenuComponent.createTextField(200);
		portEntry = MenuComponent.createTextField(200);
		
		// Add to panel
		gameInfoPanel.add(playerNameLabel);
		gameInfoPanel.add(playerNameEntry);
		gameInfoPanel.add(numPlayerLabel);
		gameInfoPanel.add(numPlayersEntry);
		gameInfoPanel.add(portLabel);
		gameInfoPanel.add(portEntry);
		gameInfoPanel.setOpaque(false);
	}
	
	/**
	 * Creates buttonPanel containing Start Server and Back
	 * buttons
	 */
	private void createButtonPanel(){
		GridLayout layout = new GridLayout(2,0);
		layout.setVgap(20);
		buttonPanel = new JPanel(layout);
		buttonPanel.setOpaque(false);
		
		//Create Start Server button
		JButton btnStartServer = MenuComponent.createButton("Start Server");
		btnStartServer.addActionListener(e -> {
			if (this.setInputs()){
				// Valid numPlayers and port have been entered
				try {
					//Start server
					NetworkingHelper.runServer(numPlayers, port, controller, playerName);
				} catch (IOException | IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(this, "Could not connect to server at " + NetworkingHelper.getIPAddress() + " : " +port);
				}
				controller.changeView(controller.getClientWaitingView());
			}
		});
		// Create Back button
		JButton btnBack = MenuComponent.createButton("Back");
		btnBack.addActionListener(e -> {
			controller.changeView(controller.getPlayGameView());
		});

		buttonPanel.add(btnStartServer);
		buttonPanel.add(btnBack);
	}
	
	/** 
	 * Sets numPlayers and port entry fields to their corresponding 
	 * variables
	 * @return true if inputs are valid and variables set, false
	 * if inputs invalid
	 */
	private boolean setInputs(){
		try {
			playerName = playerNameEntry.getText();
			if (playerName == null){
				JOptionPane.showMessageDialog(this, "Please enter a name");
				return false;
			}
			numPlayers = Integer.parseInt(numPlayersEntry.getText());
			port = Integer.parseInt(portEntry.getText());
			return true;
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Please enter numbers into both fields");
		}
		return false;
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
