/* File: Client.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 19 Sep 16		Edward Kelly	created class
 * 24 Sep 16		Edward Kelly	added vControl setting up
 * 28 Sep 16		Edward Kelly	fixed minimize bug
 * 28 Sep 16		Edward Kelly	integrated GameView
 * 29 Sep 16		Edward Kelly	now receives instructions instead of game
 * 30 Sep 16		Edward Kelly	now sends player image number
 * 2 Oct 16			Edward Kelly	disconnects handled
 * 3 Oct 16			Edward Kelly	implemented dieing
 * 10 Oct 16		Edward Kelly	fixed health difference bug between clients
 * 10 Oct 16		Edward Kelly	added sendUseItem
 */
package missing.networking;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import missing.game.Game;
import missing.game.items.nonmovable.Shop;
import missing.game.items.movable.Craftable;
import missing.game.items.movable.Food;
import missing.game.items.movable.Food.FoodType;
import missing.game.items.movable.Tool;
import missing.game.items.movable.Tool.ToolType;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.helper.SignalException;
import missing.ui.controller.VControl;
import missing.ui.views.ShopView;

/**
 * The Client is responsible for sending inputs from the player to the server
 * and providing an instance of the game to the view package to be displayed to
 * the player.
 *
 */
public class Client extends Thread implements KeyListener {
	/** The socket representing this client */
	private Socket socket;
	/** Receives info from server */
	private ObjectInputStream in;
	/** Sends info to server */
	private PrintWriter out;
	/** Reference to the game sent by server. */
	private Game game;
	/** Unique ID representing player/client. If 0, the client is the host */
	private int clientID;
	/**
	 * VControl responsible for displaying game and taking inputs from player
	 */
	private VControl vControl;
	/** Name of player for this client */
	private String playerName;
	/** Number that represents player image for this client */
	private int imageNumber;

	public Client(Socket s, VControl vControl, String playerName, int imageNumber) {
		this.socket = s;
		this.vControl = vControl;
		this.playerName = playerName;
		this.imageNumber = imageNumber;
		vControl.setClient(this);
	}

	// Getters and Setters

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	// Methods

	public void run() {
		System.out.println("Client running");
		try {
			// setup input and output streams to server
			in = new ObjectInputStream(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);

			// wait for request for name
			String request = (String) in.readObject();
			if (request.equals("name")) {
				out.println(playerName);
				out.println(imageNumber);
			}

			// Set initial game state
			clientID = (int) in.readObject();
			vControl.setPlayerID(clientID);
			game = (Game) in.readObject();
			try {
				vControl.addKeyListener(this);
				vControl.setGGame(game);
				vControl.changeView(vControl.getGameView());
				vControl.requestFocusInWindow();

			} catch (GameException e) {
				e.printStackTrace();
			}

			// listen for updates from server
			boolean playerDied = false;
			while (true) {
				Object input = null;
				try {
					input = in.readObject();
				} catch (EOFException e) {
					vControl.displayException("Disconnected from host");
					vControl.dispose();
					new VControl();
				}
				if (input == null) {
					break;
				}
				if (input.getClass() == String.class) {
					// received instruction
					// player to be changed
					int movingPlayer = (Integer) (in.readObject());
					Direction direction = (Direction) (in.readObject());
					if (input.equals("move")) {
						try {
							if (game.validMove(movingPlayer, direction)) {
								game.movePlayer(movingPlayer, direction);
							}
						} catch (GameException e) {
							if (clientID == movingPlayer) {
								vControl.displayException(e.getMessage());
							}
						}
					} else if (input.equals("turn")) {
						try {
							game.turnPlayer(movingPlayer, direction);
						} catch (GameException e) {
							if (clientID == movingPlayer) {
								vControl.displayException(e.getMessage());
							}
						}
					} else if (input.equals("perform")) {
						// only receive actions from other players
						// actions for this player handled locally in
						// handleAction
						try {
							game.performAction(movingPlayer);
						} catch (GameException e) {
							// ignore it
						} catch (SignalException e1) {
							if (e1.getMessage().contains("DEAD")) {
								String[] msg = e1.getMessage().split(" ");
								int id = Integer.parseInt(msg[1]);
								if (clientID == id) {
									playerDied = true;
								}
							} else if (e1.getMessage().contains("SHOP")) {
								game.forceRemovePlayer(movingPlayer);
							}
						}
					} else if (input.equals("disconnect")) {
						// a player disconnected
						if (!game.getAvatars()[movingPlayer].isDead()) {
							game.getAvatars()[movingPlayer].setDead(true);
							game.convertPlayerToPile(movingPlayer);
						}
						vControl.displayTimedMessage(game.getAvatars()[movingPlayer].getName() + " disconnected");

					} else if (((String) input).contains("craft")) {
						String itemType = ((String) input).split(" ")[1];
						try {
							Tool tool = Craftable.createTool(ToolType.valueOf(itemType),
									game.getAvatars()[movingPlayer]);
							game.getAvatars()[movingPlayer].addToPocket(tool);
						} catch (GameException e) {
							// already handled by player crafting item
						}
						System.out.println(game.getAvatars()[movingPlayer].getPocket().getItems().toString());
					} else if (((String) input).contains("exit")) {
						int id = Integer.valueOf(((String) input).split(" ")[1]);
						game.forceEnterPlayer(id);
					} else if (((String) input).contains("use")) {
						String foodType = ((String) input).split(" ")[1];
						Food food = new Food(null, null, FoodType.valueOf(foodType));
						try {
							food.use(game.getAvatars()[movingPlayer]);
						} catch (GameException e) {
							//handled at player who used item
						}
					}
					try {
						vControl.updateGGame(game);
					} catch (GameException e) {
						e.printStackTrace();
					}
					vControl.repaint();
					if (playerDied) {
						vControl.displayDead();
						playerDied = false;
					}
				}
			}
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			if (e.getClass() == SocketException.class) {
				// host disconnected, send to main menu
				vControl.displayException("Disconnected from host");
				// vControl.reset();
				vControl.dispose();
				new VControl();
			} else {
				e.printStackTrace();
			}
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Direction moveDirection = null;
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			moveDirection = Direction.NORTH;
			break;
		case KeyEvent.VK_S:
			moveDirection = Direction.SOUTH;
			break;
		case KeyEvent.VK_A:
			moveDirection = Direction.WEST;
			break;
		case KeyEvent.VK_D:
			moveDirection = Direction.EAST;
			break;
		case KeyEvent.VK_Q:
			out.println("Q");
			break;
		case KeyEvent.VK_E:
			out.println("E");
			break;
		case KeyEvent.VK_F:
			handleAction();
			break;
		}
		if (moveDirection != null) {
			try {
				if (game.validMove(clientID, moveDirection)) {
					out.println(moveDirection.toString());
				}
			} catch (GameException e1) {
				vControl.displayException(e1.getMessage());
			}
		}
	}

	/**
	 * Performs an action in the game. If action needs to be applied to all
	 * clients games the action is sent to the server
	 */
	public void handleAction() {
		try {
			game.performAction(clientID);
			out.println("F");
			vControl.updateGGame(game);
			vControl.repaint();
		} catch (SignalException | GameException e) {
			if (e.getClass() == SignalException.class) {
				System.out.println(e.getMessage());
				if (e.getMessage().equals("CONTAINER")) {
					vControl.displayContainerItems();
				} else if (e.getMessage().equals("PILE")) {
					vControl.displayPileItems();
				} else if (e.getMessage().contains("DEAD")) {
					String[] msg = e.getMessage().split(" ");
					int id = Integer.parseInt(msg[1]);
					out.println("F");
					try {
						vControl.updateGGame(game);
					} catch (GameException e1) {
						vControl.displayException(e.getMessage());
					}
					vControl.repaint();
					if (id == clientID) {
						// this player died
						vControl.displayDead();
					}
				} else if (e.getMessage().contains("SHOP")) {
					out.println("F");
					try {
						// first grab the shop in front of the player
						Shop shop = (Shop) game.getObjectInFront(clientID);
						// remove player from the current tile
						game.forceRemovePlayer(clientID);
						// update the shop of the player
						ShopView view = (ShopView) vControl.getView(vControl.getShopView());
						view.setPlayer(game.getAvatars()[clientID]);
						view.updateDisplay(shop);
						vControl.changeView(vControl.getShopView());
					} catch (GameException e1) {
						vControl.displayException(e.getMessage());
					}
				}
			} else if (e.getClass() == GameException.class) {
				vControl.displayException(e.getMessage());
				out.println("F");
			}
		}
	}

	/**
	 * Disconnects client from server and closes socket
	 */
	public void disconnectClient() {
		try {
			out.println("disconnect");
		} catch (NullPointerException e) {
		}
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public void sendCraftedItem(String item) {
		out.println("craft " + item);
	}

	public void sendExitSignal(int id) {
		game.forceEnterPlayer(id);
		out.println("exit " + id);
	}

	public void sendUseItem(String foodType) {
		out.println("use "+foodType);		
	}
}