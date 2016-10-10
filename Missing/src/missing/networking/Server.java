/* File: Server.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date			Author			Modification
 * 19 Sep 16	Edward Kelly	created class
 * 23 Sep 16	Edward Kelly	allowed GameException to be sent to clients
 * 26 Sep 16	Edward Kelly	implemented receiving names
 * 27 Sep 16	Edward Kelly	implemented setUpGame, now can send game
 * 28 Sep 16	Edward Kelly	added random spawns
 * 28 Sep 16	Edward Kelly	added support for rotate
 * 29 Sep 16	Edward Kelly	now sends instructions instead of whole game
 * 30 Sep 16	Edward Kelly	now creates players can have character images
 * 2 Oct 16		Edward Kelly	disconnects handled
 * 3 Oct 16		Edward Kelly	implemented killing
 */
package missing.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Random;

import missing.datastorage.initialisers.WorldInitialiser;
import missing.game.Game;
import missing.game.Game.Spawn;
import missing.game.characters.Player;
import missing.game.world.nodes.WorldTile.TileObject.Direction;
import missing.helper.GameException;

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
	/** Inputs from clients */
	private BufferedReader[] ins;
	/** Outputs to clients */
	private ObjectOutputStream[] outs;
	/** Names of all players */
	private String[] playerNames;
	/** Player image numbers for each player */
	private int[] playerImageNumbers;

	public Server(Socket[] sockets) {
		this.socket = sockets;

	}

	private void setUpGame() throws GameException {
		Player[] players = new Player[socket.length];
		List<Spawn> spawns = WorldInitialiser.getSpawnPoints();
		Random random = new Random();
		for (int i = 0; i < socket.length; i++) {
			int index = random.nextInt(spawns.size());
			players[i] = new Player(i, playerNames[i], spawns.get(index).worldLocation, spawns.get(index).tileLocation);
			players[i].setImageID(playerImageNumbers[i]);
			spawns.remove(index);
		}
		game = new Game(players);
	}

	public void run() {
		System.out.println("Server running");
		try {
			ins = new BufferedReader[socket.length];
			outs = new ObjectOutputStream[socket.length];

			playerNames = new String[socket.length];
			playerImageNumbers = new int[socket.length];
			// add input and output streams for each client socket
			for (int i = 0; i < socket.length; i++) {
				ins[i] = new BufferedReader(new InputStreamReader(socket[i].getInputStream()));
				outs[i] = (new ObjectOutputStream(socket[i].getOutputStream()));
				// set playerName
				outs[i].writeObject("name");
				playerNames[i] = ins[i].readLine();
				playerImageNumbers[i] = Integer.parseInt(ins[i].readLine());
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

			String instruction = null; // type of instruction to be sent
			Direction direction = null; // direction for the move/turn, null if
										// performAction
			// loop forever listening for inputs from clients
			while (true) {
				try {
					// listen for inputs
					for (int playerID = 0; playerID < ins.length; playerID++) {
						if (ins[playerID] == null)
							continue;
						if (!ins[playerID].ready())
							continue;
						// new input from client.
						String input = ins[playerID].readLine();
						System.out.println("Server: received " + input + " input from player +" + playerID);
						// move that player in given direction
						if (input.equals("NORTH") || input.equals("SOUTH") || input.equals("EAST")
								|| input.equals("WEST")) {
							Direction[] directions = Direction.values();
							for (Direction d : directions) {
								if (d.toString().equals(input)) {
									instruction = "move";
									direction = d;
								}
							}
						} else if (input.equals("E")) {
							// player wants to turn to EAST
							instruction = "turn";
							direction = Direction.EAST;
						} else if (input.equals("Q")) {
							// player wants to turn to WEST
							instruction = "turn";
							direction = Direction.WEST;
						} else if (input.equals("F")) {
							// player wants to perform action
							instruction = "perform";
						} else if (input.equals("disconnect")) {
							// player wants to perform action
							if (playerID==0)return;
							instruction = "disconnect";
						} else if (input.contains("craft")) {
							// player crafted an item
							instruction = input;
						} else if (input.contains("exit")) {
							instruction = input;
						} else if (input.contains("use")) {
							instruction = input;
						}
						// send instructions to clients to update game
						this.sendInstruction(instruction, playerID, direction);
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

	private void sendInstruction(String action, int playerID, Direction direction) throws IOException {
		boolean disconnect = false;
		int disconnectedPlayer = -1;
		for (int playerNum = 0; playerNum < outs.length; playerNum++) {
			if (outs[playerNum] == null)
				continue; // disconnected player
			// action already performed in client
			if (action.equals("perform") && playerNum == playerID)
				continue;
			if (action.equals("disconnect") && playerNum == playerID)
				continue;
			if (action.contains("craft") && playerNum == playerID)
				continue;
			if (action.contains("exit") && playerNum == playerID)
				continue;
			try {
				outs[playerNum].reset();
				outs[playerNum].writeObject(action);
				outs[playerNum].writeObject(playerID);
				outs[playerNum].writeObject(direction);
				outs[playerNum].flush();
			} catch (IOException e) {
				if (e.getClass() == SocketException.class) {
					System.out.println("disconnect");
					outs[playerNum] = null;
					ins[playerNum] = null;
					socket[playerNum].close();
					socket[playerNum] = null;
					disconnectedPlayer = playerNum;
					disconnect = true;
				} else {
					e.printStackTrace();
				}
			}
		}
		if (disconnect) {
			sendInstruction("disconnect", disconnectedPlayer, null);
		}
	}

}
