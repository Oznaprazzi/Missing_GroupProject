/*	File: SignalException.java
 * 	Author
 * 	Chris Rabe		300334207
 * 	
 * 	Date			Author				Changes
 * 	21 Sep 16		Chris Rabe			created signalException.java
 */

package missing.helper;

/**
 * <p>
 * This exception is thrown when a special action needs to be made when the
 * player is trying to interact with the item in front of it. An example of this
 * is interacting with the container class. When the player interacts with the
 * container, the GUI window must display the contents of the container via
 * pop-up window or changing the view. This interaction cannot be handled by the
 * standard 'performAction' method because values inside the container need to
 * be retrieved, therefore an exception must be thrown.
 * </p>
 * Here is a list of signals which need to be checked:
 * <ul>
 * <li>CONTAINER - player interacting with container.</li>
 * <li>TRADE - player interacting with another player.</li>
 * <li>PILE - player interacting with a pile </li>
 * </ul>
 */
@SuppressWarnings("serial")
public class SignalException extends Exception {

	public SignalException(String msg) {
		super(msg);
	}
}
