/*	File: MapView.java
 * 	Author
 * 	Casey Huang		300316284
 *  Edward Kelly	300334192
 *  
 * 	Date			Author				Changes
 * 	19 Sep 16		Casey Huang			created MapPanel class
 * 	19 Sep 16		Casey Huang			renamed MapView to MapPanel
 *  23 Sep 16		Edward Kelly		removed World param from constructor
 *  3 Oct 16		Edward Kelly		implemented as View with MapPanel
 *  									added spectator mode
 */
package missing.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.panels.MapPanel;

@SuppressWarnings("serial")
public class MapView extends View {
	/** If spectator, player can see all players and only has an exit button */
	private boolean isSpectator;
	private MapPanel mapPanel;
	private JPanel spectatorButtonsPanel;
	private JPanel mapButtonsPanel;
	private JPanel topPanel;
	private JLabel title;
	public MapView(VControl controller) {
		super(controller);
		isSpectator = false;
	}

	@Override
	public void initialise() {
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		createSpectatorButtonsPanel();
		createMapViewButtons();
		createTopPanel();
		mapPanel = new MapPanel(controller.getGGame().getGWorld());
//		mapPanel.setPreferredSize(this.getPreferredSize());
		add(mapPanel, BorderLayout.CENTER);
		add(mapButtonsPanel, BorderLayout.EAST);
		add(topPanel, BorderLayout.NORTH);
		controller.pack();
	}
	
	private void createTopPanel(){
		topPanel = new JPanel();
		topPanel.setOpaque(false);
		title = new JLabel("World Map");
		title.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		title.setForeground(Color.WHITE);
		topPanel.add(title);
	}
	
	private void createSpectatorButtonsPanel(){
		spectatorButtonsPanel = new JPanel();
		spectatorButtonsPanel.setOpaque(false);
		
		JButton exitBtn = new JButton("Main Menu");
		exitBtn.setBackground(Color.yellow);
		exitBtn.addActionListener(e->{
			new VControl();
//			controller.disconnectClient();
			controller.dispose();
		});
		spectatorButtonsPanel.add(exitBtn);
	}

	
	private void createMapViewButtons(){
		mapButtonsPanel = new JPanel();
		mapButtonsPanel.setOpaque(false);
		
		JButton backBtn = new JButton("Back");
		backBtn.setBackground(Color.yellow);
		backBtn.addActionListener(e->{
			controller.requestFocus();
			controller.changeView(controller.getGameView());
		});
		mapButtonsPanel .add(backBtn);
	}
	/**
	 * Updates the MapPanel with the GWorld for the updated game
	 * @param controller updated controller
	 */
	public void updateMapPanel(VControl controller){
		this.controller = controller;
		mapPanel.updateGWorld(controller.getGGame().getGWorld());
	}
	
	public void setSpectator(boolean isSpectator){
		this.isSpectator = isSpectator;
		this.remove(mapButtonsPanel);
		this.add(spectatorButtonsPanel, BorderLayout.EAST);
		this.title.setText("Spectator Mode");
		
	}
	
	public boolean isSpectator(){
		return isSpectator;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
