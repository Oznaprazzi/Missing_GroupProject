/* File: Server.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 19 Sep 16		Edward Kelly	created class
 * 23 Sep 16		Edward Kelly	allowed GameException to be sent to clients
 * 26 Sep 16		Edward Kelly	implemented receiving names
 * 27 Sep 16		Edward Kelly	implemented setUpGame, now can send game
 */
package missing.networking;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import missing.game.Game;
import missing.game.characters.Player;
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

	String[] playerNames;

	public Server(Socket[] sockets) {
		this.socket = sockets;
		// Create game. This could be done somewhere else
		// and instead the constructor is passed the game

	}

	private void setUpGame() throws GameException {
		Player[] players = new Player[socket.length];
		for (int i = 0; i < socket.length; i++) {
			// TODO: change for spawn points
			players[i] = new Player(playerNames[i], new Point(0, 0), new Point(9, i + 4));
		}
		game = new Game(players);
	}

	public void run() {
		System.out.println("Server running");
		try {
			// inputs from clients
			BufferedReader[] ins = new BufferedReader[socket.length];
			// outputs to clients
			ObjectOutputStream[] outs = new ObjectOutputStream[socket.length];

			playerNames = new String[socket.length];
			// add input and output streams for each client socket
			for (int i = 0; i < ins.length; i++) {
				ins[i] = new BufferedReader(new InputStreamReader(socket[i].getInputStream()));
				outs[i] = new ObjectOutputStream(socket[i].getOutputStream());
				// set playerName
				outs[i].writeObject("name");
				playerNames[i] = ins[i].readLine();
			}
			try {
				setUpGame();
			} catch (GameException e) {
				e.printStackTrace();
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

						if (input.equals("NORTH") || input.equals("SOUTH") || input.equals("EAST")
								|| input.equals("WEST")) {
							// move that player in given direction
							Direction[] directions = Direction.values();
							for (Direction direction : directions) {
								if (direction.toString().equals(input)) {
									try {
										game.movePlayer(playerNum, direction);
										System.out.println("player moved");
									} catch (GameException e) {
										System.out.println("move failed");
										outs[playerNum].reset();
										outs[playerNum].writeObject(e);
									}
								}
							}
						} else if (input == "E") {
							// player wants to perform action
							try {
								game.performAction(playerNum);
							} catch (SignalException | GameException e) {
								outs[playerNum].reset();
								outs[playerNum].writeObject(e);
							}
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
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
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
