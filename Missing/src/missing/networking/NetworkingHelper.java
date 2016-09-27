/* File: NetworkingHelper.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 24 Sep 16		Edward Kelly	created class
 */
package missing.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import missing.ui.controller.VControl;
/**
 * Helper class to start server and clients
 *
 */
public class NetworkingHelper {
	/**
	 * Starts client
	 * @param addr IP address host is at
	 * @param port port host is on
	 * @param isHost whether this client is host or not
	 * @param vControl vControl for client
	 * @throws IOException
	 */
	public static void runClient(String addr, int port, boolean isHost, VControl vControl, String playerName) throws IOException {
		Socket s = new Socket(addr, port);
		if (isHost) System.out.println("Connected to server as the host");
		else System.out.println("You have connected to game at " + addr + " : " + port);
		new Client(s, vControl, playerName).start();
	}
	
	/**
	 * Starts server
	 * @param numClients number of clients to connect
	 * @param port port server is hosted on
	 * @param vControl vControl for the host player
	 * @throws IOException
	 */
	public static void runServer(int numClients, int port, VControl vControl, String playerName) throws IOException {
		// Start listening for players trying to join
		SocketListener socketListener = new SocketListener(numClients, port);
		socketListener.start();
		// Join server by creating a client for this player
		runClient(getIPAddress(), port, true, vControl, playerName);
	}
	
	/**
	 * Retrieves the IPAddress of the network which the host is connected to. It
	 * follows the basis of :
	 * 
	 * <ul>
	 * <li>Any address in the range 127.xxx.xxx.xxx is a loopback address. It is
	 * only visible to "this" host.</li>
	 * <li>Any address in the range 192.168.xxx.xxx is a private (aka site
	 * local) IP address. These are reserved for use within an organization. The
	 * same applies to 10.xxx.xxx.xxx addresses, and 172.16.xxx.xxx through
	 * 172.31.xxx.xxx.</li>
	 * <li>Addresses in the range 169.254.xxx.xxx are link local IP addresses.
	 * These are reserved for use on a single network segment.</li>
	 * <li>Addresses in the range 224.xxx.xxx.xxx through 239.xxx.xxx.xxx are
	 * multicast addresses.</li>
	 * <li>The address 255.255.255.255 is the broadcast address.</li>
	 * <li>Anything else should be a valid public point-to-point IPv4 address.
	 * </li>
	 * </ul>
	 *
	 */
	public static String getIPAddress() {
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				NetworkInterface n = (NetworkInterface) e.nextElement();
				Enumeration<InetAddress> ee = n.getInetAddresses();
				while (ee.hasMoreElements()) {
					InetAddress i = (InetAddress) ee.nextElement();
					// IP of internet connection is the same as the host name
//					if (i.getHostName().equals(i.getHostAddress())) {
						// Only accept private networks
						if (i.getHostAddress().startsWith("192.168.") || i.getHostAddress().startsWith("10.")
								|| i.getHostAddress().startsWith("172.16.")) {
							return i.getHostAddress();
						}
//					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}
}
