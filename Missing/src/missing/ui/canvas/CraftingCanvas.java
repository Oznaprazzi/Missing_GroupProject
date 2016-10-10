/*	File: CraftingCanvas.java
 * 	Author
 *  Edward Kelly	300334192
 *  
 * 	Date			Author				Changes
 *  8 Oct 16		Edward Kelly		created class
 *  9 Oct 16		Edward Kelly		added buttons, completed functionality
 */
package missing.ui.canvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;

import javax.swing.BorderFactory;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import missing.datastorage.assetloader.GameAssets;
import missing.game.characters.Player;
import missing.game.items.movable.Craftable;
import missing.game.items.movable.Dirt;
import missing.game.items.movable.Movable;
import missing.game.items.movable.Resource;
import missing.game.items.movable.Stone;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.items.movable.Wood;
import missing.helper.GameException;
import missing.ui.controller.VControl;
import missing.ui.frames.CraftingFrame;
import missing.ui.frames.HandJFrame;
/** JPanel for displaying crafting window. */
@SuppressWarnings("serial")
public class CraftingCanvas extends JPanel implements MouseListener{
	
	private VControl controller;
	private final int NUM_ITEMS = 4;
	private final int RESOURCESIZE = 40;
	private final int TOOLSIZE = 70;
	private final Border TOOLBORDER = BorderFactory.createLineBorder(Color.GRAY, 5, true);
	private Player player;
	private JLabel selected;
	private CraftingFrame frame;
	
	public CraftingCanvas(VControl controller, CraftingFrame frame){
		this.controller = controller;
		this.frame = frame;
		this.player = controller.getGGame().getGame().getAvatars()[controller.getPlayerID()];
		initialise();
	}
	/**
	 * Initialise 
	 */
	private void initialise(){
		selected = null;
		setLayout(new BorderLayout());
		createItemsDetailsPane();
		this.setOpaque(false);
	}
	
	/**
	 * Creates main JPanel containing all components
	 */
	private void createItemsDetailsPane(){
		JPanel itemDetails = new JPanel();
		itemDetails.setOpaque(false);
		itemDetails.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		int cols = 2;
		int rows = NUM_ITEMS/cols;

		int index=0;
		ToolType[] tools = ToolType.values();
		for (int row=0; row<rows; row++){
			for (int col=0; col<cols; col++){
				JPanel panel = createItemDetails(tools[index]);
//				c.gridx = col;
				c.gridy = index+1;
				itemDetails.add(panel, c);
				index++;
			}
		}
		
		JPanel btns = createButtonPanel();
		c.gridy = index+1;
		itemDetails.add(btns,c);
		add(itemDetails, BorderLayout.CENTER);
	}
	
	/**
	 * Creates JPanel for buttons
	 * @return
	 */
	private JPanel createButtonPanel(){
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(20);
		JPanel panel = new JPanel(flowLayout);
		panel.setOpaque(false);
		
		panel.add(createCraftButton());
		panel.add(createBagButton());
		
		return panel;	
	}
	
	/**
	 * Creates Bag button which opens bag
	 * @return
	 */
	private JButton createBagButton(){
		JButton bagBtn = new JButton("Open Bag");
		bagBtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
    	bagBtn.setForeground(new Color(86,40,17));
		bagBtn.setOpaque(false);
		bagBtn.setContentAreaFilled(false);
		bagBtn.setBorderPainted(false);
		bagBtn.setBorder(null);
		bagBtn.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	bagBtn.setForeground(new Color(255,190,0));
		    }
		    public void mouseExited(MouseEvent evt) {
		    	bagBtn.setForeground(new Color(86,40,17));
		    }
		});
		bagBtn.addActionListener(e->{
			controller.requestFocus();
			new HandJFrame(controller, player.getBag(), player.getPocket()).setVisible(true);
			frame.dispose();
		});
		return bagBtn;
	}
	
	/**
	 * Creates Craft button which crafts item
	 * @return
	 */
	private JButton createCraftButton(){
		JButton craftBtn = new JButton("Craft Item");
		craftBtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
    	craftBtn.setForeground(new Color(86,40,17));
		craftBtn.setOpaque(false);
		craftBtn.setContentAreaFilled(false);
		craftBtn.setBorderPainted(false);
		craftBtn.setBorder(null);
		craftBtn.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent evt) {
		    	craftBtn.setForeground(new Color(255,190,0));
		    }
		    public void mouseExited(MouseEvent evt) {
		    	craftBtn.setForeground(new Color(86,40,17));
		    }
		});
		craftBtn.addActionListener(e->{
			if (selected == null)return;
			ToolType toolType = null;
			for (ToolType tt : ToolType.values()){
				if (tt.toString().equals(selected.getName())){
					toolType = tt;
					break;
				}
			}
			try {
				
				Tool tool = Craftable.createTool(toolType, player);
				player.addToPocket(tool);
				System.out.println("pocket currentsize: "+player.getPocket().getCurrentSize());
				System.out.println("pocket size: "+player.getPocket().getSize());
				controller.sendCraftedItem(tool.getType().toString());
				new CraftingFrame(controller).setVisible(true);
				frame.dispose();
				return;
			} catch (GameException e1) {
				if (e1.getMessage().equals("Not enough space left.")){
					JOptionPane.showMessageDialog(this, "No room in pocket for tool", "Cannot Craft Item", JOptionPane.WARNING_MESSAGE);
				} else if (e1.getMessage().equals("Player does not have the required items")){
					JOptionPane.showMessageDialog(this, "You do not have the resources for this tool", "Cannot Craft Item", JOptionPane.WARNING_MESSAGE);
				}
				e1.printStackTrace();
			}
			
		});
		return craftBtn;
	}
	
	/**
	 * Creates JPanel containing details of a Craftable tool
	 * @param toolType type of toll to be created
	 * @return
	 */
	private JPanel createItemDetails(ToolType toolType){
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(20);
		JPanel panel = new JPanel(flowLayout);
		panel.setOpaque(false);
		
		// Create Tool Image
		JLabel toolLabel = null;
		Tool tool = new Tool(null,null, toolType);
		switch (toolType){
			case AXE:
				toolLabel = createToolLabel(tool,GameAssets.getAxeImage().getScaledInstance(TOOLSIZE, TOOLSIZE, Image.SCALE_SMOOTH));
				toolLabel.setName("AXE");
				break;
			case PICKAXE:
				toolLabel = createToolLabel(tool,GameAssets.getPickaxeImage().getScaledInstance(TOOLSIZE, TOOLSIZE, Image.SCALE_REPLICATE));
				toolLabel.setName("PICKAXE");
				break;
			case SHOVEL:
				toolLabel = createToolLabel(tool,GameAssets.getShovelImage().getScaledInstance(RESOURCESIZE, RESOURCESIZE, Image.SCALE_REPLICATE));
				toolLabel.setName("SHOVEL");
				break;
			case FISHINGROD:
				toolLabel = createToolLabel(tool,GameAssets.getFishingRodImage().getScaledInstance(RESOURCESIZE, RESOURCESIZE, Image.SCALE_REPLICATE));
				toolLabel.setName("FISHINGROD");
				break;
		}
		panel.add(toolLabel);

		// Create Resource images
		for (Resource ingredient : tool.getIngredients()){
			if (ingredient instanceof Wood){
				panel.add(createIngredientLabel(ingredient, GameAssets.getWoodImage().getScaledInstance(RESOURCESIZE, RESOURCESIZE, Image.SCALE_REPLICATE)));
			} else if (ingredient instanceof Stone){
				panel.add(createIngredientLabel(ingredient, GameAssets.getStoneImage().getScaledInstance(RESOURCESIZE, RESOURCESIZE, Image.SCALE_REPLICATE)));
			} else if (ingredient instanceof Dirt){
				panel.add(createIngredientLabel(ingredient, GameAssets.getDirtImage().getScaledInstance(RESOURCESIZE, RESOURCESIZE, Image.SCALE_REPLICATE)));
			}
		}
		
		
		
		
		return panel;
	}
	
	/**
	 * Creates a JLabel to display Tool
	 * @param tool
	 * @param image image for tool
	 * @return
	 */
	private JLabel createToolLabel(Tool tool, Image image){
		JLabel label;
		boolean enoughResources = true;
		for (Resource ingredient : tool.getIngredients()){
			if (!player.hasMultipleOfItem(ingredient, ingredient.getAmount())){
				enoughResources = false;
				break;
			}
		}
		if (enoughResources){
			label = new JLabel(new ImageIcon(image));
			label.setBorder(BorderFactory.createLineBorder(new Color(86,40,17), 5, true));			
		}
		else{
			ImageFilter filter = new GrayFilter(true, 40);  
			ImageProducer producer = new FilteredImageSource(image.getSource(), filter);  
			Image grayImg = Toolkit.getDefaultToolkit().createImage(producer); 
			label = new JLabel(new ImageIcon(grayImg));
			label.setBorder(TOOLBORDER);			
		}
		label.addMouseListener(this);
		label.setPreferredSize(new Dimension(TOOLSIZE, TOOLSIZE));
		
		String ingredientDetails = "";
		for (Resource ingr : tool.getIngredients()){
			ingredientDetails += ingr.getName()+"(x"+ingr.getAmount()+") ";
		}
		String tip = "<html>"+tool.getName()+": "+tool.getDescription()+"<br>"
				+ "Made from: "+ingredientDetails+"</html>";
		label.setToolTipText(tip);
		return label;
	}
	
	/**
	 * Creates JLabel for ingredient
	 * @param ingredient
	 * @param image image for ingredient
	 * @return
	 */
	private JLabel createIngredientLabel(Resource ingredient, Image image) {
		JLabel label;
		if (this.player.hasMultipleOfItem(ingredient, ingredient.getAmount())){
			label = new JLabel(new ImageIcon(image));
			label.setBorder(createIngredientBorder(true));
		} else{
			ImageFilter filter = new GrayFilter(true, 40);  
			ImageProducer producer = new FilteredImageSource(image.getSource(), filter);  
			Image grayImg = Toolkit.getDefaultToolkit().createImage(producer); 
			label = new JLabel(new ImageIcon(grayImg));
			label.setBorder(createIngredientBorder(false));
		}
		int playersAmt = 0;
		if (player.has(ingredient)){
			for (Movable item : player.getPocket().getItems()){
				if (item.equals(ingredient)){
					playersAmt = item.getAmount();
					break;
				}
			}
		}
		String tip = "<html>"+ingredient.getName()+"<br>"
				+ "Required: "+ingredient.getAmount()+"<br>"
				+ "In Pocket: "+playersAmt;
		label.setToolTipText(tip);
		return label;
	}
	
	/**
	 * Creates border for ingredient label
	 * @param hasIngredient
	 * @return
	 */
	private Border createIngredientBorder(boolean hasIngredient){
		Color c = Color.GRAY;
		if(hasIngredient)c = new Color(130,70,40);
		return BorderFactory.createLineBorder(c, 3, true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(GameAssets.getCraftingBackgroundImage(), 0, 0, this.getWidth(),this.getHeight(),null);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component comp = e.getComponent();
		if (comp instanceof JLabel){
			if (((JLabel) comp).getBorder().equals(TOOLBORDER)){
				System.out.println("gray");
				return;
			}
			if (selected != null){
				selected.setBorder(BorderFactory.createLineBorder(new Color(86,40,17), 5, true));
			}
			((JLabel) comp).setBorder(BorderFactory.createLineBorder(new Color(255,190,0), 5, true));
			selected = (JLabel) comp;
		}
	}
	
	//Not used
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	
}
