/*	File: SplashView.java
 * 	Authors:				ID:
 * 	Jian Wei Chong			300352789
 *  Linus Go				300345571
 * 	
 * 	Date:					Author					Changes
 * 	13 Sep 16				Jian Wei 				created SplashView
 * 	13 Sep 16				Linus Go				added implementation of SplashView.
 	14 Sep 16				Linus Go				added code to draw SplashScreen.
 	18 Sep 16				Linus Go				changed the next view to be the GameView
 */
package missing.ui.views;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import missing.datastorage.assetloader.GameAssets;
import missing.ui.controller.VControl;
import missing.ui.controller.VControl.View;

@SuppressWarnings("serial")
public class SplashView extends View implements ActionListener{
	private Timer timer = new Timer(100, this);
	private long end = System.currentTimeMillis() + 1500;
	private float alpha = 1f;
	private BufferedImage image;

	public SplashView(VControl controller) {
		super(controller);
		timer.start();
		image = GameAssets.getLogoImage();
	}

	@Override
	public void initialise() {
		try {
			Thread.sleep(6000);
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
		super.paint(g);
		g.drawImage(GameAssets.getSplashBackgroundImage(), 0, 0, null);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(image, (this.getWidth()/2)-image.getWidth()/2, this.getHeight()/2 - image.getHeight()/2, null);
	}

	public void actionPerformed(ActionEvent e) {
		if (System.currentTimeMillis() > end) {
			if(image.equals(GameAssets.getLogoImage())){
				alpha += -0.05f;
				if (alpha <= 0) {
					alpha = 0;
					timer.restart();
					image = GameAssets.getMissingLogoImage();
				}
			}
			if(image.equals(GameAssets.getMissingLogoImage())){
				if(alpha >= 0 && alpha < 1){
					alpha += 0.05f;
					if(alpha >= 1){
						alpha = 1;
					}
				}
			}
			repaint();
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
