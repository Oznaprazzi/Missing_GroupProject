package missing.tests;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import missing.game.characters.Player;
import missing.game.world.World;
import missing.ui.controller.VControl.View;
import missing.ui.panels.GamePanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.BorderLayout;

public class TestWindow extends JFrame {
	
	private GamePanel gPanel;
	private final int panelWd = 600;
	private final int panelHt = 600;
	
	public TestWindow(World w, Player p){
		super("Test Panel");
		this.setVisible(true);
		gPanel = new GamePanel(p);
		this.setSize(panelWd, panelHt);
		System.out.println();
		this.getContentPane().add(gPanel);
		gPanel.setLayout(new BorderLayout(0, 0));
		pack();
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(panelWd, panelHt);
	}
	
}
