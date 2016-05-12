/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;

public class BuildCarModelOptions implements AutoServer {
	private ServerSocket serverSocket = null;
	private DefaultSocketClient socket = null;
	private int iport;
	
	public BuildCarModelOptions(int iport) {
		this.iport = iport;
		try {
			serverSocket = new ServerSocket(iport);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openAccept() {
		try {
			serverSocket = new ServerSocket(iport);
			socket = new DefaultSocketClient(serverSocket.accept());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*step 3*/
	public void acceptPropObject() {
//		openAccept();
		DefaultSocketClient socket1;
		try {
			socket1 = new DefaultSocketClient(serverSocket.accept());
			Thread t1 = new Thread(socket1);
	    	t1.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
    			
	}
	
	/*step 4*/
	public void availableAutoList() {
//		openAccept();
    	Thread t1 = new Thread(socket);
    	t1.start();
		
	}
	
	public void closeSocket() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}