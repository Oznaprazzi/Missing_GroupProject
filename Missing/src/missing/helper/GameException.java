/*	File: GameException.java
 * 	Authors:				ID:
 * 	Chris Rabe				300334207
 * 	
 * 	Date:					Author					Changes
 * 	7 Sep 16				Chris Rabe				create game exception class
 */
package missing.helper;

/**
 * This method should be thrown whenever an invalid game move has been made.
 */
@SuppressWarnings("serial")
public class GameException extends Exception {
	public GameException(String msg) {
		super(msg);
	}
}
