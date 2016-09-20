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

public class TestWindow extends JFrame {
	
	private GamePanel gPanel;
	private final int panelWd = 800;
	private final int panelHt = 600;
	
	public TestWindow(World w, Player p){
		super("Test Panel");
		this.setVisible(true);
		gPanel = new GamePanel(p);
		this.setSize(panelWd, panelHt);
		System.out.println();
		this.getContentPane().add(gPanel);
		GridBagLayout gbl_gPanel = new GridBagLayout();
		gbl_gPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_gPanel.rowHeights = new int[]{0, 0};
		gbl_gPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_gPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		gPanel.setLayout(gbl_gPanel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 20;
		gbc_panel.gridy = 0;
		gPanel.add(panel, gbc_panel);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		JLabel lblPlayerViewControls = new JLabel("Player View Controls");
		panel.add(lblPlayerViewControls);
		pack();
	}
	
	
	
}
