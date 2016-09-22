/*	File: SignalException.java
 * 	Author
 * 	Chris Rabe		300334207
 * 	
 * 	Date			Author				Changes
 * 	21 Sep 16		Chris Rabe			created signalException.java
 */

package missing.helper;

/**
 * This exception is thrown when a special action needs to be made when the
 * player is trying to interact with the item in front of it. An example of this
 * is interacting with the container class. The standard "performAction" method
 * cannot handle this, therefore an exception must be thrown.
 */
@SuppressWarnings("serial")
public class SignalException extends Exception {

	// List of signals used
	// CONTAINER - signals that player is trying to interact with a container
	// TRADE - signals that the player is trying to interact with another player

	public SignalException(String msg) {
		super(msg);
	}
}
