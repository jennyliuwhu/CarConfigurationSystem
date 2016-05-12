/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package client;

import java.util.Set;

import model.Automobile;

/**
 * This class does following functions:
 * a. Prompts the user for available models
 * b. Allows the user to select a model
 * c. Displays the selected options for a class
 * @author jialingliu
 *
 */
public class SelectCarOption {
	public void configureCar() {
		DefaultSocketClient defaultSocketClient = new DefaultSocketClient(2);
		Thread t = new Thread(defaultSocketClient);
		t.start();
	}
	
	public Set<String> getSet() {
		DefaultSocketClient defaultSocketClient = new DefaultSocketClient(3);
		return defaultSocketClient.getSet();
	}
	
	public Automobile getAuto(String name) {
		DefaultSocketClient defaultSocketClient = new DefaultSocketClient(4);
		return defaultSocketClient.getAutomobile(name);
	}
}
