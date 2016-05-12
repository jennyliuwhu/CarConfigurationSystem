/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package driver;

import java.util.Scanner;

import client.CarModelOptionsIO;
import client.SelectCarOption;

public class Driver {
	private static Scanner in;
	private static final int portNum = 9753;
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		
		CarModelOptionsIO carModelOptionIO = new CarModelOptionsIO();
		SelectCarOption selectCarOption = new SelectCarOption();

		System.out.println("Upload Properties file or Configure a Car?");
		System.out.println("Type 1 or 2 to indicate your option.");	
		String type = in.nextLine();
		
		if ("1".equals(type)) {
			System.out.println("Uploading Properties file...");
			carModelOptionIO.uploadProp();
		}
		else if ("2".equals(type)) {
			System.out.println("Configuring a car...");
			selectCarOption.configureCar();
		}
	}
	public static int getPortNum() {
		return Driver.portNum;
	}
}
