package missing.tests;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.game.world.World;
import missing.ui.controller.VControl.View;
import missing.ui.panels.GamePanel;

public class TestWindow extends JFrame {
	
	private GamePanel gPanel;
	
	public TestWindow(World w){
		super("Test Panel");
		this.setVisible(true);
		gPanel = new GamePanel(new Point(2,2));
		this.setSize(800, 600);
		System.out.println();
		this.getContentPane().add(gPanel);
		pack();
	}
}
