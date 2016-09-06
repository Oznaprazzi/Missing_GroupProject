/* File: GameException.java
 * 
 * Authors:				ID:
 * Chris Rabe			300334207
 * 
 * Date					Author					Changes
 * 7 Sep 16				Chris Rabe				created game exception
 */

package missing.helper;

/**
 * This class represents the error thrown if an invalid player action has been
 * taken.
 */
@SuppressWarnings("serial")
public class GameException extends Exception {

	public GameException(String msg) {
		super(msg);
	}
}
