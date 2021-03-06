/*	File: WorldInitialiser.java
 * 	Author:
 * 	Edward Kelly		300334192
 *  Chris Rabe			300334207
 * 
 * 	Date			Author				changes
 * 	8 Sep 16		Edward Kelly		created class
 *  8 Sep 16		Edward Kelly		created unimplemented Loading World and Loading Items methods
 *  8 Sep 16		Edward Kelly		implemented Loading World methods
 *  12 Sep 16		Chris Rabe			fixed initialiser
 */
package missing.datastorage.initialisers;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import missing.datastorage.assetloader.GameAssets;
import missing.game.Game.Spawn;
import missing.game.world.World;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.TileType;
import missing.helper.GameException;

/**
 * Helper class containing static methods used to help with creation of the
 * world
 *
 */
public class WorldInitialiser {
	/** Size of row and column of one world node */
	public static final int NODE_SIZE = 10;

	public static List<Spawn> getSpawnPoints() {
		List<Spawn> tmp = new ArrayList<Spawn>();
		tmp.add(new Spawn(new Point(0, 2), new Point(2, 3)));
		tmp.add(new Spawn(new Point(3, 0), new Point(2, 2)));
		tmp.add(new Spawn(new Point(1, 1), new Point(8, 7)));
		tmp.add(new Spawn(new Point(2, 3), new Point(7, 5)));
		tmp.add(new Spawn(new Point(0, 1), new Point(3, 5)));
		return tmp;
	}

	/**
	 * Loads the nodes inside the world using predefined text files.
	 * 
	 * @return
	 * @throws GameException
	 */
	public static WorldNode[][] createWorldNodes() throws GameException {
		WorldNode[][] worldNodes = new WorldNode[World.WORLD_WIDTH][World.WORLD_HEIGHT];
		// Create worldNodes (loaded from .txt file)
		InputStream input;
		for (int y = 0; y < World.WORLD_HEIGHT; y++) {
			for (int x = 0; x < World.WORLD_WIDTH; x++) {
				input = GameAssets.getWorldFile(x, y);
				WorldNode worldNode = parseWorldNode(input, x, y);
				worldNodes[y][x] = worldNode;
			}
		}
		return worldNodes;
	}

	/**
	 * Links the nodes to the neighbours.
	 * 
	 * @param nodes
	 * @return
	 */
	public static WorldNode[][] linkNodes(WorldNode[][] nodes) {
		for (int y = 0; y < nodes.length; y++) {
			for (int x = 0; x < nodes[y].length; x++) {
				linkNode(nodes, nodes[y][x], new Point(x, y - 1), 'N');
				linkNode(nodes, nodes[y][x], new Point(x, y + 1), 'S');
				linkNode(nodes, nodes[y][x], new Point(x, y + 1), 'E');
				linkNode(nodes, nodes[y][x], new Point(x, y - 1), 'W');
			}
		}
		return nodes;
	}

	// Helper methods

	/**
	 * Links the node to a single neighbour. The position of the neighbour is
	 * indicated by the char character.
	 * 
	 * @param nodes
	 * @param node
	 * @param point
	 * @param c
	 */
	private static void linkNode(WorldNode[][] nodes, WorldNode node, Point point, char c) {
		if (-1 < point.x && point.x < World.WORLD_WIDTH && -1 < point.y && point.y < World.WORLD_HEIGHT) {
			switch (c) {
			case 'N':
				node.setNorth(nodes[point.y][point.x]);
				break;
			case 'S':
				node.setSouth(nodes[point.y][point.x]);
				break;
			case 'E':
				node.setEast(nodes[point.y][point.x]);
				break;
			case 'W':
				node.setWest(nodes[point.y][point.x]);
				break;
			}
		}
	}

	/**
	 * Reads data for one worldNode from a file and returns a new WorldNode
	 * 
	 * @param input
	 *            InputStream to read data from
	 * @param x
	 *            x position of WorldNode in World
	 * @param y
	 *            y position of WorldNode in World
	 * @return
	 */
	private static WorldNode parseWorldNode(InputStream input, int x, int y) throws GameException {
		BufferedReader br = null;
		WorldTile[][] tiles = new WorldTile[NODE_SIZE][NODE_SIZE];
		try {
			br = new BufferedReader(new InputStreamReader(input));
			String line = "";
			int row = 0; // each line represents a row in the board
			while ((line = br.readLine()) != null) {
				tiles[row] = scanRow(line, row);
				row++;
			}
		} catch (IOException e) {
			throw new GameException(e.getMessage());
		}
		return new WorldNode(new Point(x, y), tiles);
	}

	/** Scans one row in the file */
	private static WorldTile[] scanRow(String line, int row) throws GameException {
		if (line.length() != NODE_SIZE) {
			throw new GameException("Incorrect file type");
		}

		WorldTile[] tiles = new WorldTile[NODE_SIZE];

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			tiles[i] = parseTile(c, row, i);
			if (tiles[i] == null) {
				throw new GameException("Parsing error");
			}
		}

		return tiles;
	}

	/** Returns a tile based on the character */
	private static WorldTile parseTile(char c, int row, int col) {
		switch (c) {
		case 'G': // grass
			return new WorldTile(TileType.GRASS, new Point(col, row));
		case 'W': // water
			return new WorldTile(TileType.WATER, new Point(col, row));
		case 'S': // sand
			return new WorldTile(TileType.SAND, new Point(col, row));
		case 'R': // road
			return new WorldTile(TileType.ROAD, new Point(col, row));
		default:
			return null;
		}
	}

}
