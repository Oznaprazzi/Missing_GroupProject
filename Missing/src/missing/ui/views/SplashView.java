/*	File: SplashView.java
 * 	Authors:				ID:
 * 	Jian Wei Chong			300352789
 *  Linus Go				300345571
 * 	
 * 	Date:					Author					Changes
 * 	13 Sep 16				Jian Wei 				created SplashView
 * 	13 Sep 16				Linus Go				added implementation of SplashView.
 	14 Sep 16				Linus Go				added code to draw SplashScreen.
 */
package missing.ui.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;

public class SplashView extends View{
	 
	public SplashView(VControl controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialise() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	/**
	 * When called, this method will draw the splash screen message onto the screen.
	 * @param g
	 */
	public void draw(Graphics g){
		int xPos = super.getPreferredSize().width;
		int yPos = super.getPreferredSize().height;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0,xPos, yPos);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
		g.drawString("Team 3 Presents: Missing - The Game", (int) (xPos*0.02), yPos/2);
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
