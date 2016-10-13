/* File: HostGameView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Casey Huang 		300316284
 * 
 * Date				Author			Modification
 * 23 Sep 16		Edward Kelly	created class
 * 24 Sep 16		Edward Kelly	added function to start server
 * 30 Sep 16		Edward Kelly	merged with CreatePlayerView
 * 6 Oct 16			Casey Huang		Removed commented code and added background image
 */
package missing.ui.views.playgamemenu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import missing.datastorage.assetloader.GameAssets;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;

/**
 * Displays the view for entering details for hosting a game
 *
 */
@SuppressWarnings("serial")
public class HostGameView extends View{
	
	/** Displays components for entering info for game */
	private JPanel gameInfoPanel;
	/** Panel that holds buttons */
	private JPanel buttonPanel;
	/** Holds gameInfo and button panels */
	private JPanel centrePanel;
	/** Number of players to be in game */
	private int numPlayers;
	/** Port game is hosted on */
	private int port;
	
	private JComboBox<String> numPlayersBox;
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

		JLabel title = MenuFactory.createHeading("Host Game");
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
		JLabel numPlayerLabel = MenuFactory.createLabel("Number of Players");
		JLabel portLabel = MenuFactory.createLabel("Port");
		numPlayerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Create text fields
		String[] choices = {"1","2","3","4","5"};
		numPlayersBox = new JComboBox<String>(choices);
		
		portEntry = MenuFactory.createTextField(200);
		
		// Add to panel
		gameInfoPanel.add(numPlayerLabel);
		gameInfoPanel.add(numPlayersBox);
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
		JButton btnStartServer = MenuFactory.createButton("Start Server");
		btnStartServer.addActionListener(e -> {
			if (this.setInputs()){
				controller.setIsHost(true);
				controller.changeView(controller.getCreatePlayerView());
			}
		});
		// Create Back button
		JButton btnBack = MenuFactory.createButton("Back");
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
			numPlayers = Integer.valueOf((String) this.numPlayersBox.getSelectedItem());
			if(!(Integer.parseInt(portEntry.getText()) >= 1024 && Integer.parseInt(portEntry.getText()) <= 65535)){
				JOptionPane.showMessageDialog(this, "You must enter a valid port number between 1024 and 65535.");
				return false;
			}
			
			port = Integer.parseInt(portEntry.getText());
			return true;
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Please enter numbers into both fields");
		}
		return false;
	}
	public int getNumPlayers(){
		return numPlayers;
	}
	public int getPort(){
		return port;
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
