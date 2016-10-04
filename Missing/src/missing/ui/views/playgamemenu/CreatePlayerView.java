/* File: MenuFactory.java
 * 
 * Authors			ID
 * Linus Go			300345571
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * ** Sep 16		Linus Go		Created class
 * 30 Sep 16		Edward Kelly	merged with server/client and join/host views
 * 3 Oct 16			Linus Go		Tidied up player chooser and stopped erroneous input being entered.
 */
package missing.ui.views.playgamemenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import missing.datastorage.assetloader.GameAssets;
import missing.networking.NetworkingHelper;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;

@SuppressWarnings("serial")
public class CreatePlayerView extends View{
	/*Dimensions for the Game Panels width and height */
	private final int panelWd = 800;
	private final int panelHt = 600;
	
	private int wd, ht;
	
	private List<Image> imgList;

	private int imgIndex = 0;

	/*Image rectangle dimensions*/
	private final int RECT_SIZE = 250;
	private final int RECT_LEFT = 375;
	private final int RECT_TOP = 0;
	

	private TextField textField;
	private JPanel imgPanel;
	private JButton btnBack;
	private JButton btnNext;
	private JButton btnCreatePlayer;

	/*Stores the playerName and the image Number chosen */
	private String playerName;
	private int imageNumber;

	/*Swing components for this window */
	public CreatePlayerView(VControl controller) {
		super(controller);
		initialise();
		setupActionListeners();
		repaint();
		controller.pack();
	}
	private void initialiseList(){
		imgList = new LinkedList<Image>();

		for(int i = 0; i <= 5; ++i){
			imgList.add(GameAssets.getPlayerImage(i, "south"));
		}

	}
	@Override
	public void initialise() {
	
		setSize(panelWd, panelHt);
		setBackground(Color.black);
		GridBagLayout gbl_panel = new GridBagLayout();

		gbl_panel.columnWidths = new int[] { 89, 0 };
		gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gbl_panel);

		JLabel lblCurrentPlayer = MenuFactory.createHeading("Create your Player!");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 0;
		add(lblCurrentPlayer, gbc_lblCurrentPlayer);

		initialiseList();
		imgPanel = new JPanel(){
			@Override
			public void paint(Graphics g){
				drawImage(g);
			}

		};
		
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		imgPanel.setBackground(Color.gray.darker());
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;

		add(imgPanel, gbc_panel_1);

		btnBack = MenuFactory.createButton("Previous");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 8;
		add(btnBack, gbc_btnBack);

		btnNext = MenuFactory.createButton("Next");
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 5, 0);
		gbc_btnNext.gridx = 0;
		gbc_btnNext.gridy = 9;
		add(btnNext, gbc_btnNext);

		JLabel lblPlayerName = MenuFactory.createLabel("What is your Survivors name?");
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);

		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.anchor = GridBagConstraints.CENTER;
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 10;
		add(lblPlayerName, gbc_lblPlayerName);

		textField = MenuFactory.createTextField(1);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.CENTER;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 11;
		add(textField, gbc_textField);
		textField.setColumns(10);

		btnCreatePlayer = MenuFactory.createButton("Ok, put me in!");
		GridBagConstraints gbc_btnCreatePlayer = new GridBagConstraints();
		gbc_btnCreatePlayer.anchor = GridBagConstraints.NORTH;
		gbc_btnCreatePlayer.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreatePlayer.gridx = 0;
		gbc_btnCreatePlayer.gridy = 12;
		add(btnCreatePlayer, gbc_btnCreatePlayer);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 13;
		add(layeredPane, gbc_layeredPane);
		
	}
	/**
	 * Private method to allow you to draw stuff onto the current graphics context.
	 * @param g
	 */
	private void drawImage(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(imgList.get(imgIndex), RECT_LEFT, RECT_TOP, RECT_SIZE,RECT_SIZE, null);
		//g.drawImage(GameAssets.getBackdropImage(), 0,0, getWidth(), getHeight(), null);
	}

	/**
	 * Used to set up the action listeners.
	 */
	private void setupActionListeners() {

		btnBack.addActionListener(e->{
			if(imgIndex == 0){
				imgIndex = 5;
			}else{
				imgIndex--;
			}
			drawImage(imgPanel.getGraphics());
			imgPanel.repaint();
			repaint();
		});

		btnNext.addActionListener(e->{
			if(imgIndex == imgList.size()-1){
				imgIndex = 0;
			}else{
				imgIndex++;
			}
			drawImage(imgPanel.getGraphics());
			imgPanel.repaint();
			repaint();
		});

		btnCreatePlayer.addActionListener(e->{
			//SANITY check: does the textField have numbers or is empty?
			if(textField.getText().isEmpty() || textField.getText().matches(".*\\d+.*"))
				JOptionPane.showMessageDialog(null, "Please give your character a VALID name first!");
			else{
				playerName = textField.getText();
				imageNumber = imgIndex;
				int numPlayers;
				int port;
				String IPAddress;
				if (controller.isHost()){
					numPlayers = ((HostGameView) (controller.getView(controller.getHostGameView()))).getNumPlayers();
					port = ((HostGameView) (controller.getView(controller.getHostGameView()))).getPort();
					// Valid numPlayers and port have been entered
					try {
						//Start server
						NetworkingHelper.runServer(numPlayers, port, controller, playerName, imageNumber);
					} catch (IOException | IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(this, "Could not connect to server at " + NetworkingHelper.getIPAddress() + " : " +port);
					}
				} else {
					IPAddress = ((JoinGameView)(controller.getView(controller.getJoinGameView()))).getIP();
					port = ((JoinGameView) (controller.getView(controller.getJoinGameView()))).getPort();
					try {
						NetworkingHelper.runClient(IPAddress, port, false, controller, playerName, imageNumber);
						controller.changeView(controller.getClientWaitingView());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, "Could not connect to server at " + IPAddress + " : " +port);
					}
				}
				controller.changeView(controller.getClientWaitingView());
			
			}
		});
	}
	
	
	
	
	/**
	 * Returns the Player Name that the player has chosen.
	 * @return
	 */
	public String getPlayerName(){
		return this.playerName;
	}

	/**
	 * Returns the imageIndex of the players chosen image.
	 * @return int - imageNumber
	 */
	public int getImageIndex(){
		return this.imageNumber;
	}


	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
