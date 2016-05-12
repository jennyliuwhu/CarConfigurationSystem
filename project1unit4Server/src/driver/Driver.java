/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package driver;
import java.io.IOException;

import adapter.BuildAuto;
import adapter.CreateAuto;
import server.AutoServer;

public class Driver{
	private final static int portNum = 9753;
	public static void main(String[] args) throws IOException {
		//CreateAuto auto = new BuildAuto();
//		auto.buildAuto("model1.properties", "properties");
//		auto.buildAuto("model2.properties", "properties");
//		auto.buildAuto("model3.txt", "txt");
		AutoServer server = new BuildAuto();
		while(true) {
			System.out.println("Server running...");
			server.acceptPropObject();
		}
	}
	public static int getPortNum() {
		return Driver.portNum;
	}
}