/* File: Client.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 19 Sep 16		Edward Kelly	created class
 */
package missing.networking;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import missing.game.Game;

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
	/** Unique ID representing player/client */
	private int clientID;

	public Client(Socket s) {
		this.socket = s;
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

		// TODO: needs a swing component to listen to keys
		// maybe init VControl here, passing game and clientID

		try {
			// setup input and output streams to server
			in = new ObjectInputStream(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);

			// listen for updates from server
			while (true) {
				Object input = in.readObject();
				if (input == null) {
					break;
				}
				if (input.getClass() == Game.class) {
					// received game
					game = (Game) input;
					// TODO: repaint view
				} else if (input.getClass() == Integer.class) {
					// received clientID
					clientID = (Integer) input;
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

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP) {
			out.println("NORTH");
			System.out.println("north pressed");
		} else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_KP_DOWN) {
			out.println("SOUTH");
			System.out.println("south pressed");
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_KP_LEFT) {
			out.println("WEST");
			System.out.println("west pressed");
		} else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT) {
			out.println("EAST");
			System.out.println("east pressed");
		} else if (key == KeyEvent.VK_E) {
			out.println("E");
			System.out.println("E pressed");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}