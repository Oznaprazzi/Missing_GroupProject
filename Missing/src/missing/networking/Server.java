/* File: Server.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 19 Sep 16		Edward Kelly	created class
 */
package missing.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import missing.game.Game;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;
import missing.helper.SignalException;

/**
 * Represents the Server for the game. The server holds a Game object and is
 * responsible for receiving inputs from clients to make changes to the game and
 * sending the Game to connected clients when a change is made in the game to be
 * displayed.
 */
public class Server extends Thread {
	/** Client sockets connected to server */
	private Socket[] socket;
	/** The game being played which is sent to clients */
	private Game game;

	public Server(Socket[] sockets) {
		this.socket = sockets;

		// Create game. This could be done somewhere else
		// and instead the constructor is passed the game

	}

	public void run() {
		System.out.println("Server running");
		try {
			BufferedReader[] ins = new BufferedReader[socket.length]; // inputs
																		// from
																		// clients
			ObjectOutputStream[] outs = new ObjectOutputStream[socket.length]; // outputs
																				// to
																				// clients
			// add input and output streams for each client socket
			for (int i = 0; i < ins.length; i++) {
				ins[i] = new BufferedReader(new InputStreamReader(socket[i].getInputStream()));
				outs[i] = new ObjectOutputStream(socket[i].getOutputStream());
			}
			// Send initial game state
			for (int i = 0; i < outs.length; i++) {
				outs[i].writeObject(i);// send clientID
				outs[i].writeObject(game);
				outs[i].flush();
			}

			boolean update = false; // used to know if a new game needs to be
									// sent to clients
			// loop forever listening for inputs from clients
			while (true) {
				try {
					// listen for inputs
					for (int playerNum = 0; playerNum < ins.length; playerNum++) {
						if (!ins[playerNum].ready())
							continue;
						// new input from client. at this stage just a key
						// direction
						String input = ins[playerNum].readLine();
						System.out.println(input + " input received from player " + playerNum);

						if (input == "NORTH" || input == "SOUTH" || input == "EAST" || input == "WEST") {
							// move that player in given direction
							Direction[] directions = Direction.values();
							for (Direction direction : directions) {
								if (direction.toString().equals(input)) {
									game.movePlayer(playerNum, direction);
								}
							}
						} else if (input == "E") {
							game.performAction(playerNum);
						}
						update = true; // will need to send updated game
					}

					// Send updated game to clients if a player did something
					if (update) {
						for (int playerNum = 0; playerNum < outs.length; playerNum++) {
							outs[playerNum].reset();
							outs[playerNum].writeObject(game);
							outs[playerNum].flush();
						}
						update = false;
					}
				} catch (IOException e) {
					// TODO implement disconnects properly
					e.printStackTrace();
				} catch (SignalException e) {
					// TODO Must notify client about the signal exception
					e.printStackTrace();
				} catch (GameException e) {
					// TODO Must notify the client about the game exception
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				for (Socket s : socket) {
					s.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Server stopped");
	}
}
