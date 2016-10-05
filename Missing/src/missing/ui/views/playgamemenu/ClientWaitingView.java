/* File: ClientWaitingView.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 27 Sep 16		Edward Kelly	created class
 * 6 Oct 16			Edward Kelly	added IPAddr label
 */
package missing.ui.views.playgamemenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
		JLabel connected = MenuFactory.createHeading("Connected to server");
		JLabel waiting = MenuFactory.createHeading("Waiting for game to start...");
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int wd = super.getSize().width;
		int ht = super.getSize().height;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, wd, ht);
		if (controller.isHost()){
			IPAddr.setText("Game hosted at IP: "+NetworkingHelper.getIPAddress());
		}
	}

}
