/* File: JoinGame.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 * 24 Sep 16		Edward Kelly	created class
 * 24 Sep 16		Edward Kelly	added function to start client
 * 30 Sep 16		Edward Kelly	merged with CreatePlayerView
 * 6 Oct 16			Casey Huang		Added background and removed commented code
 * 
 */
package missing.ui.views.playgamemenu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import missing.datastorage.assetloader.GameAssets;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;
/**
 * Displays the view for entering details for joining a game
 *
 */
@SuppressWarnings("serial")
public class JoinGameView extends View{
	/** Displays components for entering info for the game */
	private JPanel gameInfoPanel;
	/** Panel that holds buttons */
	private JPanel buttonPanel;
	/** Holds gameInfo and button panels */
	private JPanel centrePanel;
	/** IP address for game trying to join */
	private String IPAddress;
	/** Port game is hosted on */
	private int port;
	/** Entry field for IP address */
	private TextField IPEntry;
	/** Entry field for port */
	private TextField portEntry;
	
	public JoinGameView(VControl controller) {
		super(controller);
		this.initialise();
		repaint();
		controller.pack();
	}

	@Override
	public void initialise() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		createCentrePanel();

		JLabel title = MenuFactory.createHeading("Join Game");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.2;
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
		createButtonsPanel();
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
	 * Creates infoPanel containing IPEntry, portEntry
	 * and corresponding labels
	 */
	private void createInfoPanel(){
		GridLayout layout = new GridLayout(3,2);
		layout.setVgap(20);
		layout.setHgap(20);
		gameInfoPanel = new JPanel(layout);
		// Create labels
		JLabel IPLabel = MenuFactory.createLabel("IP Address");
		JLabel portLabel = MenuFactory.createLabel("Port");
		IPLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		// Create text fields
		IPEntry = MenuFactory.createTextField(150);
		portEntry = MenuFactory.createTextField(150);

		gameInfoPanel.add(IPLabel);
		gameInfoPanel.add(IPEntry);
		gameInfoPanel.add(portLabel);
		gameInfoPanel.add(portEntry);
		gameInfoPanel.setOpaque(false);
	}
	/**
	 * Creates buttonPanel containing Start Server and Back
	 * buttons
	 */
	private void createButtonsPanel(){
		GridLayout layout = new GridLayout(2,0);
		layout.setVgap(20);
		buttonPanel = new JPanel(layout);
		buttonPanel.setOpaque(false);
		
		//Create join button
		JButton btnStartServer = MenuFactory.createButton("Join");
		btnStartServer.addActionListener(e -> {
			if (setInputs()){
				controller.setIsHost(false);
				controller.changeView(controller.getCreatePlayerView());
			}
		});
		
		//Create back button
		JButton btnBack = MenuFactory.createButton("Back");
		btnBack.addActionListener(e -> {
			controller.changeView(controller.getPlayGameView());
		});
		buttonPanel.add(btnStartServer);
		buttonPanel.add(btnBack);
	}
	
	/** 
	 * Sets IPAddress and port entry fields to their corresponding 
	 * variables
	 * @return true if inputs are valid and variables set, false
	 * if inputs invalid
	 */
	private boolean setInputs(){
		try {
			IPAddress = (IPEntry.getText());
			
			if(!(Integer.parseInt(portEntry.getText()) >= 1024 && Integer.parseInt(portEntry.getText()) <= 65535)){
				JOptionPane.showMessageDialog(this, "You must enter a valid port number between 1024 and 65535.");
				return false;
			}
			
			port = Integer.parseInt(portEntry.getText());
			return true;
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Please enter a valid  IP and port");
		}
		return false;
	}
	
	public String getIP(){
		return this.IPAddress;
	}
	public int getPort(){
		return this.port;
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
