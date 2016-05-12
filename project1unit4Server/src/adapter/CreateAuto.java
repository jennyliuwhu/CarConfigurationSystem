/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package adapter;

import java.util.Properties;

import model.Automobile;

public interface CreateAuto {
	
	// Given a text file name, a method called buildAuto can be writted to build an instance of Automobile. 
	// This method does not have to return the Auto instance
	public void buildAuto(String filename, String fileType);
	
	// Build an auto with Properties file
	public Automobile buildAutoWithProp(Properties prop);
	
	// This method searches and prints the properties of a given Automodel
	public void printAuto(String modelName);
	
	public void appendToMap(Automobile auto);
}
