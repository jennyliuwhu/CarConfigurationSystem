/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package adapter;

public interface UpdateAuto {
	// This method searches the model for a given OptionSet and sets the name of OptionSet to newName
	public void updateOptionSetName(String modelName, String optionSetName, String newName);
	
	// This method searches the model for a given OptionSet and Option name, and sets the price to newPrice
	public void updateOptionPrice(String modelName, String optionName, String option, float newPrice);
}
