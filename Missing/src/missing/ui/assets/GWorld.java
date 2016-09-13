/* File: GWorld.java
 * Author:
 * Jian Wei Chong	300352789
 * Chris Rabe		300334207
 * 
 * Date				Author				Changes
 * 13 Sep 16		Jian Wei			create GWorld.java
 * 13 Sep 16		Chris Rabe			added draw method
 */

package missing.ui.assets;

import java.awt.Graphics;

import missing.game.world.World;

public class GWorld {

	private World world;
	private GWNode[][] GWNodes = new GWNode[World.WORLD_WIDTH][World.WORLD_HEIGHT];

	public GWorld(World world) {
		this.world = world;
	}

	public void draw(Graphics g) {

	}
}
