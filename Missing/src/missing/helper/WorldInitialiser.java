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
package missing.helper;
/*	File: WorldInitialiser.java
 * 	Author:
 * 	Chris Rabe		300334207
 * 
 * 	Date			Author				Changes
 * 	12 Sep 16		Chris Rabe			fixed initialiser
 * 	13 Sep 16		Chris Rabe			expanded node size to 10
 */

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import missing.game.world.World;
import missing.game.world.nodes.WorldNode;
import missing.game.world.nodes.WorldTile;
import missing.game.world.nodes.WorldTile.TileType;

/**
 * Helper class containing static methods used to help with creation of the
 * world
 *
 */
public class WorldInitialiser {
	/** Size of row and column of one world node */
	private static final int NODE_SIZE = 10;
	private static final String WORLD_FILE_PATH = "/missing/datastorage/world/node/";

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
				input = WorldInitialiser.class.getResourceAsStream(WORLD_FILE_PATH + x + "," + y + ".txt");
				WorldNode worldNode = parseWorldNode(input, x, y);
				worldNodes[y][x] = worldNode;
			}
		}
		return worldNodes;
	}

	public static WorldNode[][] linkNodes(WorldNode[][] worldNodes) {
		WorldNode[][] tmp = worldNodes;
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[i].length; j++) {
				tmp[j][i] = linkNode(tmp[j][i], tmp);
			}
		}
		return tmp;
	}

	// Helper methods

	private static WorldNode linkNode(WorldNode node, WorldNode[][] nodes) {
		WorldNode tmp = node;
		
		return tmp;
	}
	
//	/**
//	 * Maps each node of the board to its neighbour as long as it's within the
//	 * boundaries of the board.
//	 * 
//	 * @param temp
//	 * @return
//	 */
//	public static Node[][] mapNodesToNeighbours(Node[][] temp) {
//		for (int i = 0; i < temp.length; i++) {
//			for (int j = 0; j < temp[i].length; j++) {
//				addNeighbour(temp, temp[i][j], new Point(i, j - 1));
//				addNeighbour(temp, temp[i][j], new Point(i, j + 1));
//				addNeighbour(temp, temp[i][j], new Point(i - 1, j));
//				addNeighbour(temp, temp[i][j], new Point(i + 1, j));
//			}
//		}
//		return temp;
//	}
//
//	/**
//	 * Adds neighbour to the given node if the point is within the boundaries of
//	 * the board
//	 */
//	private static void addNeighbour(Node[][] temp, Node node, Point point) {
//		if ((-1 < point.x && point.x < 25) && (-1 < point.y && point.y < 25)) {
//			node.addNeighbour(temp[point.x][point.y]);
//		}
//	}

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
