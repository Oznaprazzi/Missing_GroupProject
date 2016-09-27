/* File: Client.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 19 Sep 16		Edward Kelly	created class
 * 24 Sep 16		Edward Kelly	added vControl setting up
 */
package missing.networking;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import missing.game.Game;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.helper.SignalException;
import missing.ui.assets.GGame;
import missing.ui.controller.VControl;
/**
 * The Client is responsible for sending inputs from the player to the server
 * and providing an instance of the game to the view package to be displayed to
 * the player.
 *
 */
public class Client extends Thread {
	/** The socket representing this client */
	private Socket socket;
	/** Receives info from server */
	private ObjectInputStream in;
	/** Sends info to server */
	private PrintWriter out;
	/** Reference to the game sent by server. */
	private Game game;
	/** Unique ID representing player/client. If 0, the client is the host*/
	private int clientID;
	/** VControl responsible for displaying game and taking inputs from player */
	private VControl vControl;
	private String playerName;

	public Client(Socket s, VControl vControl, String playerName) {
		this.socket = s;
		this.vControl = vControl;
		this.playerName = playerName;
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
			
			out.write(playerName);
			boolean vControlGameSet = false; // used to know if game has been passed to VControl
			// listen for updates from server
			while (true) {
				Object input = in.readObject();
				if (input == null) {
					break;
				}
				if (input.getClass() == Game.class) {
					// received game
					game = (Game)input;
					if (!vControlGameSet){
						//TODO: may need to set game each time
						try {
							vControl.setGGame(new GGame(game, null));
							vControl.changeView(vControl.getGameView());
						} catch (GameException e) {
							// TODO forward to controller
							e.printStackTrace();
						}
						vControlGameSet = true;
//						continue; // dont want to paint until game started
					}
					vControl.repaint();
				} else if (input.getClass() == Integer.class) {
					// received clientID
					clientID = (Integer)input;
					vControl.setPlayerID(clientID);
				} else if (input.getClass() == GameException.class){
					System.out.println(((GameException)input).toString());
					//TODO: forward to controller
				} else if (input.getClass() == SignalException.class){
					System.out.println(((SignalException)input).toString());
					//TODO: forward to controller
				}
			}
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Server has gone offline");
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void movePlayer(String direction){
		out.println(direction);
	}
	
	public void performAction(){
		out.println("E");
	}
}