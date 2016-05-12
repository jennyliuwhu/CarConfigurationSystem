/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import adapter.BuildAuto;
import adapter.CreateAuto;
import adapter.GetLinkedHashMap;
import driver.Driver;
import model.Automobile;

public class DefaultSocketClient extends Thread {
	String hostName = "localHost";
	int portNumber = Driver.getPortNum();
	private Socket socket = null;
	private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;

	public DefaultSocketClient() {}

	public DefaultSocketClient(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		openConnection();
		handleSession();
		closeSession();
	}

	private void handleSession() {
		String mode = (String)handleSessionRead();

		// upload properties file
		if (mode.equals("1")) {
			System.out.println("Uploading Properties file...");
			Properties prop = null;
			CreateAuto auto = new BuildAuto();
			handleSessionWrite("1");
			prop = (Properties) handleSessionRead();
			System.out.println("Properties received = " + prop);

			handleSessionWrite("Object Get :)");
			handleSessionWrite(null);
			auto.appendToMap(auto.buildAutoWithProp(prop));
		}

		// configure a car
		else if (mode.equals("2")) {
			System.out.println("Configuring a car...");
			GetLinkedHashMap get = new BuildAuto();
			Map<String, Automobile> linkedHashMap = get.getLinkedHashMap();
			Set<String> set = new HashSet<String>();
			Iterator<Automobile> itr = linkedHashMap.values().iterator();
			Automobile mobile = null;
			while (itr.hasNext()) {
				Automobile mob = itr.next();
				set.add(mob.getName());
			}

			handleSessionWrite("2");
			handleSessionWrite(set);

			String option = (String)handleSessionRead();
			System.out.println("Client chose the Automobile with name " + option);
			Iterator<Automobile> itr1 = linkedHashMap.values().iterator();
			while (itr1.hasNext()){
				Automobile mob = itr1.next();
				if(mob.getName().equals(option)) {
					mobile = mob;
					break;
				}
			}
			handleSessionWrite(mobile);
			handleSessionWrite(null);
		}

		// send available cars' names to client
		else if (mode.equals("3")) {
			System.out.println("Sending cars' names to client...");
			GetLinkedHashMap get = new BuildAuto();
			Map<String, Automobile> linkedHashMap = get.getLinkedHashMap();
			Set<String> set = new HashSet<String>();
			Iterator<Automobile> itr = linkedHashMap.values().iterator();
			while (itr.hasNext()) {
				Automobile mob = itr.next();
				set.add(mob.getName());
			}
			handleSessionWrite("3");
			handleSessionWrite(set);
			handleSessionWrite(null);
		}

		// send the selected Automobile with its respective options to client
		else if (mode.equals("4")) {
			System.out.println("Sending selected Automobile to client...");
			GetLinkedHashMap get = new BuildAuto();
			Map<String, Automobile> linkedHashMap = get.getLinkedHashMap();
			Set<String> set = new HashSet<String>();
			Iterator<Automobile> itr = linkedHashMap.values().iterator();
			Automobile mobile = null;
			while (itr.hasNext()){
				Automobile mob = itr.next();
				set.add(mob.getName());
			}
			handleSessionWrite("4");
			String option = (String)handleSessionRead();
			//find the option
			Iterator<Automobile> itr1 = linkedHashMap.values().iterator();
			while (itr1.hasNext()){
				Automobile mob = itr1.next();
				if(mob.getName().equals(option)) {
					mobile = mob;
					break;
				}
			}
			handleSessionWrite(mobile);
			handleSessionWrite(null);
		}
	}

	private void openConnection() {
		try {
			inputStream = new ObjectInputStream(socket.getInputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
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
			e.printStackTrace();
		}  
	}
}