/* File: GWorld.java
 * Author:
 * Jian Wei Chong	300352789
 * 
 * Date				Author				Changes
 * 13 Sep 16		Jian Wei			create GWorld.java
 */

package missing.ui.assets;

import missing.game.world.World;

public class GWorld {

	private World world;
	private GWNode[][] GWNodes = new GWNode[World.WORLD_WIDTH][World.WORLD_HEIGHT];

	public GWorld(World world) {
		this.world = world;

	}

}
