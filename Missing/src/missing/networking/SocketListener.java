/* File: SocketListener.java
 * 
 * Authors			ID
 * Edward Kelly 	300334192
 * 
 * Date				Author			Modification
 * 20 Sep 16		Edward Kelly	created class
 */
package missing.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listens for sockets trying to join the server socket.
 * Starts server for connected sockets
 *
 */
public class SocketListener extends Thread{
	/** Number of players/clients that are going to join server	 */
	private int numClients;
	/** Port the server will running on */
	private int port;
	
	public SocketListener(int numClients, int port){
		this.numClients = numClients;
		this.port = port;
	}
	@Override
	public void run(){
		try {
			// listens for clients trying to join server
			ServerSocket listener = new ServerSocket(port);
			Socket[] clientSockets = new Socket[numClients]; // client sockets
			int clientNum = 0; // current client number
			
			while (true) {
				Socket s = listener.accept(); // accept connection from client
				if (clientNum != 0){ // not host
					System.out.println("Player at IP addr. " +s.getInetAddress() + " joined");
				}
				// add sockets in order of joining
				clientSockets[clientNum] = s;

				if (numClients == 1) { // all players connected
					System.out.println("All players connected");
					new Server(clientSockets).start(); // start server
					listener.close();
					return;
				}
				clientNum++;
				numClients--;
				System.out.println("Waiting for " + (numClients) + " more players to join...");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
