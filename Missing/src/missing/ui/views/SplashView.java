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

@SuppressWarnings("serial")
public class SplashView extends View {
	private String opening;

	public SplashView(VControl controller) {
		super(controller);
		this.opening = "Team 3\u2122 Presents: Missing - The Game";
	}

	@Override
	public void initialise() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controller.changeView(controller.getMenuView()); // change to main Menu
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	/**
	 * When called, this method will draw the splash screen message onto the
	 * screen.
	 * 
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		// Create black background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		Font serif = new Font("Comic Sans MS", Font.BOLD, 50);
		g.setFont(serif);
		int width = g.getFontMetrics().stringWidth(opening);
		int height = g.getFontMetrics().getHeight();
		// Center the text
		g.setColor(Color.WHITE);
		int x = getPreferredSize().width / 2 - width / 2;
		int y = getPreferredSize().height / 2 - height / 2;
		g.drawString(opening, x, y);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
