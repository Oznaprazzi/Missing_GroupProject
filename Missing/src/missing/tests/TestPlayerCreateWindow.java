package missing.tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import missing.datastorage.assetloader.GameAssets;
import missing.game.Game;
import missing.game.characters.Player;
import missing.game.world.World;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.ui.menustyle.MenuFactory;
import missing.ui.panels.GamePanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TestPlayerCreateWindow extends JFrame{
	/*Dimensions for the Game Panels width and height */
	private final int panelWd = 800;
	private final int panelHt = 600;
	
	private List<Image> imgList;
	
	private int imgIndex = 0;
	
	
	/*Image rectangle dimensions*/
	private final int RECT_SIZE = 100;
	private final int RECT_LEFT = (this.getWidth()/2)-75;
	private final int RECT_TOP = 0;
	
	private TextField textField;
	private JPanel imgPanel;
	private JButton btnBack;
	private JButton btnNext;
	private JButton btnCreatePlayer;
	
	/*Swing components for this window */
	
	
	public TestPlayerCreateWindow() {
		super("Test Panel");
		setVisible(true);
		setSize(panelWd, panelHt);
		initializeGUI();
		setupActionListeners();
		setResizable(false);
		pack();
	}
	
	private void initializeList(){
		imgList = new LinkedList<Image>();
		imgList.add(GameAssets.getPlayerImage("boy"));
		imgList.add(GameAssets.getPlayerImage("girl"));
		System.out.println(imgList.size());
		System.out.println(imgList.toString());
	}
	
	
	/**
	 * Helper method to initialize the Windows GUI components.
	 */
	private void initializeGUI(){
		JPanel panel = new JPanel();
		panel.setSize(panelWd, panelHt);
		panel.setBackground(Color.black);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();

		gbl_panel.columnWidths = new int[] { 89, 0 };
		gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblCurrentPlayer = MenuFactory.createLabel("Create your Player!");
		GridBagConstraints gbc_lblCurrentPlayer = new GridBagConstraints();
		gbc_lblCurrentPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentPlayer.gridx = 0;
		gbc_lblCurrentPlayer.gridy = 0;
		panel.add(lblCurrentPlayer, gbc_lblCurrentPlayer);

		initializeList();
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
		
		panel.add(imgPanel, gbc_panel_1);
		
		btnBack = MenuFactory.createButton("<--");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 8;
		panel.add(btnBack, gbc_btnBack);
		
		btnNext = MenuFactory.createButton("-->");
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 5, 0);
		gbc_btnNext.gridx = 0;
		gbc_btnNext.gridy = 9;
		panel.add(btnNext, gbc_btnNext);
		
		JLabel lblPlayerName = MenuFactory.createLabel("Enter your name:");
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.anchor = GridBagConstraints.WEST;
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 10;
		panel.add(lblPlayerName, gbc_lblPlayerName);
		
		textField = MenuFactory.createTextField(1);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 11;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnCreatePlayer = MenuFactory.createButton("Ok, put me in!");
		GridBagConstraints gbc_btnCreatePlayer = new GridBagConstraints();
		gbc_btnCreatePlayer.anchor = GridBagConstraints.NORTH;
		gbc_btnCreatePlayer.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreatePlayer.gridx = 0;
		gbc_btnCreatePlayer.gridy = 12;
		panel.add(btnCreatePlayer, gbc_btnCreatePlayer);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 13;
		panel.add(layeredPane, gbc_layeredPane);

	}
	
	/**
	 * Private method to allow you to draw stuff onto the current graphics context.
	 * @param g
	 */
	private void drawImage(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(imgList.get(imgIndex), (getWidth()/2-75), 0 , RECT_SIZE,RECT_SIZE, null);
	}
	
	
	
	/**
	 * Used to set up the action listeners.
	 */
	private void setupActionListeners() {
	
	btnBack.addActionListener(e->{
		if(imgIndex <= 0) {
			return;
		}
		imgIndex--;
		drawImage(imgPanel.getGraphics());
		imgPanel.repaint();
		repaint();
	});
	
	btnNext.addActionListener(e->{
		if(imgIndex >= imgList.size()-1){
			return;
		}
		imgIndex++;
		drawImage(imgPanel.getGraphics());
		imgPanel.repaint();
		repaint();
	});
	
	btnCreatePlayer.addActionListener(e->{
		JOptionPane.showMessageDialog(null, "Created your player.");
		drawImage(imgPanel.getGraphics());
		imgPanel.repaint();
		repaint();
	});

	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(panelWd, panelHt);
	}

	public static void main(String[] args){
		new TestPlayerCreateWindow();
	}


	

}
