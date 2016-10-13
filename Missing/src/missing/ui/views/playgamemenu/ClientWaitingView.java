/* File: ClientWaitingView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * Casey Huang		300316284
 * 
 * Date				Author			Modification
 * 27 Sep 16		Edward Kelly	created class
 * 6 Oct 16			Edward Kelly	added IPAddr label
 * 6 Oct 16			Casey Huang		Added background
 * 7 Oct 16			Casey Huang		Added gif background
 */
package missing.ui.views.playgamemenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import missing.datastorage.assetloader.GameAssets;
import missing.networking.NetworkingHelper;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;
import missing.ui.menustyle.MenuFactory;

/**
 * The view displayed to clients after they have connected to server
 * and are waiting for game to start
 */
@SuppressWarnings("serial")
public class ClientWaitingView extends View {
	
	JLabel IPAddr;
	
	public ClientWaitingView(VControl controller) {
		super(controller);
		initialise();
		repaint();
		controller.pack();
	}

	@Override
	public void initialise() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JLabel connected = MenuFactory.createHeading2("Connected to server");
		JLabel waiting = MenuFactory.createHeading2("Waiting for game to start...");
		IPAddr = MenuFactory.createLabel("");
		
		connected.setHorizontalAlignment(SwingConstants.CENTER);
		waiting.setHorizontalAlignment(SwingConstants.CENTER);
		add(connected);
		c.gridy = 1;
		add(waiting,c);
		c.gridy = 2;
		add(IPAddr, c);
	}

	@Override
	public void setFocus() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(GameAssets.getServerBackgroundImage(), 0, 0, this);
		if (controller.isHost()){
			Font f = new Font("Courier New", Font.PLAIN, 20);
			IPAddr.setText("Game hosted at IP: "+NetworkingHelper.getIPAddress());
			IPAddr.setFont(f);
			IPAddr.setBorder(new EmptyBorder(20,0,0,0));
			IPAddr.setForeground(Color.WHITE);
		}
	}

}
