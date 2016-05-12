/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import driver.Driver;
import model.Automobile;
import util.FileIO;

public class DefaultSocketClient extends Thread {
	String hostName = "localHost";
	int portNumber = Driver.getPortNum();
	private Socket socket = null;
	private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;
	int mode = 1;
	private Scanner in;

	public DefaultSocketClient(int mode) {
		this.mode = mode;
	}

	public void run() {
		openConnection();
		HandleSession();
		closeSession();
	}

	protected Set<String> getSet() {
		openConnection();

		Set<String> set;
		handleSessionWrite("3");
		handleSessionRead();
		set = (HashSet<String>) handleSessionRead();

		closeSession();
		return set;
	}

	protected Automobile getAutomobile(String name) {
		Automobile auto;
		openConnection();

		handleSessionWrite("4");
		handleSessionRead();
		handleSessionWrite(name);
		auto = (Automobile)handleSessionRead();
		handleSessionRead();

		closeSession();
		return auto;
	}

	private synchronized void  HandleSession() {
		Object obj;
		Set<String> set;
		in = new Scanner(System.in);
		String choice;
		Automobile mob;
		String filepath;

		if (mode == 1)
			handleSessionWrite("1");
		else
			handleSessionWrite("2");

		while((obj = handleSessionRead()) != null) {
			if (((String)obj).equals("1")) {
				FileIO io = new FileIO();
				System.out.println("Give a filepath: ");
				filepath = in.nextLine();
				Properties prop = io.getProperties(filepath);
				handleSession(prop);
			}
			else if (((String)obj).equals("2")) {
				set = (Set<String>)handleSessionRead();

				System.out.println("The available Automobiles from server: " + set);
				System.out.println("Choose one to display");
				choice = in.nextLine();
				System.out.println("\nReceived a Automobile from the server\n");
				handleSessionWrite(choice);
				mob = (Automobile)handleSessionRead();
				mob.print();
			}
			else
				break;

		}
	}

	private void openConnection() {
		try {
			socket = new Socket(hostName, portNumber);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleSession(Properties prop) {	
		try {
			outputStream.writeObject(prop);
			verifySending();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void verifySending() {
		try {
			System.out.println("Response from server: " + inputStream.readObject());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	private void handleSessionWrite(Object prop) {	
		try {
			outputStream.writeObject(prop);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private Object handleSessionRead() {	
		Object obj = null;
		try {
			obj = inputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return obj;
	}

	private void closeSession(){
		try {
			outputStream = null;
			inputStream = null;
			socket.close();
		}
		catch (IOException e){
		}       
	}

}
