/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;

public class Automobile implements Serializable{
	private static final long serialVersionUID = 1L;
	private float basePrice;
	private String name;
	private String model;
	private String make;
	private ArrayList<Option> choice;

	// opset = [Color, Transmission, Breaks/Traction Control, Side Impact Air Bags, Power Moonroof]
	private ArrayList<OptionSet> opset;

	/**
	 * Constructors
	 */
	public Automobile() {}

	public Automobile(String name, float basePrice, int size, String make, String model) {
		this.name = name;
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;

		this.opset = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			this.opset.add(new OptionSet()); 
		}
	}

	// Getters
	public synchronized String getName() {
		return this.name;
	}
	public synchronized String getMake() {
		return this.make;
	}
	public synchronized String getModel() {
		return this.model;
	}
	public synchronized float getBasePrice() {
		return this.basePrice;
	}

	// get OptionSet
	public synchronized ArrayList<OptionSet> getOptionSet() {
		return this.opset;
	}

	// get OptionSet by index values
	public synchronized OptionSet getOptionSet(int index) {
		return this.opset.get(index);
	}

	// get OptionSetName by index
	public synchronized String getOptionSetName(int index) {
		OptionSet optionSet = getOptionSet(index);
		return optionSet.getName();
	}
	// get the OptionName by optionSetIndex and optionIndex
	public synchronized String getOptionName(int optionSetIndex, int optionIndex) {
		OptionSet optionSet = getOptionSet(optionSetIndex);
		return optionSet.getOptionName(optionIndex);
	}

	// get the OptionPrice by optionSetIndex and optionIndex
	public synchronized float getOptionPrice(int optionSetIndex, int optionIndex) {
		OptionSet optionSet = getOptionSet(optionSetIndex);
		return optionSet.getOptionPrice(optionIndex);
	}

	// get Option Choice name
	public synchronized String getOptionChoice(String setName) {
		OptionSet optionSet = findOptionSet(setName);
		if (optionSet == null) {
			System.out.println("No such OptionSet!");
			return null;
		}
		return optionSet.getOptionChoice().getName();
	}

	// get Option Choice price
	public synchronized Float getOptionChoicePrice(String setName) {
		OptionSet optionSet = findOptionSet(setName);
		if (optionSet == null) {
			System.out.println("No such OptionSet!");
			return null;
		}
		return optionSet.getOptionChoice().getPrice();
	}

	// get total price
	public synchronized float getTotalPrice() {
		float totalPrice = 0;
		for (int i = 0; i < this.choice.size(); i++) {
			totalPrice += this.choice.get(i).getPrice();
		}
		return totalPrice;
	}

	// get the amount of optionSet
	public int getOptionSetAmount() {
		return this.opset.size();
	}

	public int getOptionAmount(int index) {
		OptionSet optionSet = this.opset.get(index);
		return optionSet.getOptionAmount();
	}

	// Setters
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized void setMake(String make) {
		this.make = make;
	}
	public synchronized void setModel(String model) {
		this.model = model;
	}
	public synchronized void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	public synchronized void setOptionSet(int index, String optionSetName, String[] optionNames, float[] prices) {
		this.opset.set(index, new OptionSet(optionSetName, optionNames.length));
		this.opset.get(index).setOption(optionNames, prices);
	}

	// add a new OptionSet to the existing opset
	public synchronized void addOptionSet(String setName) {
		this.opset.add(new OptionSet(setName));
	}

	// add an Option to a particular OptionSet
	public synchronized void addOption(String optionSetName, String optionName, float optionPrice) {
		OptionSet optionSet = findOptionSet(optionSetName);
		if (optionSet == null) {
			System.out.println("No such optionSet!");
			return;
		}
		optionSet.addOption(optionName, optionPrice);
	}

	// choose a particular option in an option set
	public synchronized void setOptionChoice(String setName, String optionName) {
		OptionSet optionSet = findOptionSet(setName);
		if (optionSet == null) {
			System.out.println("Did not find this OptionSet!");
			return;
		}
		optionSet.setOptionChoice(optionName);
		System.out.println("Find the OptionSet and Option, add it to choice.");
		this.choice.add(optionSet.getOptionChoice());
	}

	// find OptionSet with name
	public synchronized OptionSet findOptionSet(String name) {
		for (int i = 0; i < this.opset.size(); i++) {
			if (name.equals(this.opset.get(i).getName())) {
				return this.opset.get(i);
			}
		}
		return null;
	}

	// delete OptionSet with name, set its position as a new OptionSet, then return the original
	public synchronized String deleteOptionSet(String name) {
		OptionSet find = findOptionSet(name);
		if (find == null) {
			return null;
		} else {
			find = new OptionSet();
			return name;
		}
	}

	// update OptionSet name
	public synchronized void updateOptionSet(String optionSetName, String newName) {
		OptionSet find = findOptionSet(optionSetName);
		if (find == null) {
			System.out.println("No such OptionSet!");
			return;
		} else {
			find.setName(newName);
		}
	}

	// update OptionSet with name, replace it with a new set of options
	public synchronized String updateOptionSet(String name, String[] optionNames, float[] prices) {
		OptionSet find = findOptionSet(name);
		if (find == null) {
			return null;
		} else {
			find.setOption(optionNames, prices);
			return name;
		}
	}

	// update a particular option's price
	public synchronized void updateOptionPrice(String optionSet, String option, float newPrice) {
		OptionSet find = findOptionSet(optionSet);
		if (find == null) {
			System.out.println("No such OptionSet!");
			return;
		}
		find.updateOptionPrice(option, newPrice);
	}

	public synchronized void print() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ");
		sb.append(name);
		sb.append("\n");
		sb.append("Make: ");
		sb.append(this.make);
		sb.append('\n');
		sb.append("Model: ");
		sb.append(this.model);
		sb.append('\n');
		sb.append("BasePrice:");
		sb.append(this.getBasePrice());
		sb.append('\n');
		sb.append("OptionSets:\n");
		for (int i = 0; i < this.opset.size(); i++) {
			sb.append(this.opset.get(i).print());
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	public synchronized void printAvailableOptionSet() {
		StringBuilder sb = new StringBuilder("Available OptionSets: [");
		String prefix = "";
		for (int i = 0; i < this.opset.size(); i++) {
			sb.append(prefix);
			sb.append(this.opset.get(i).getName());
			prefix = ", ";
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

	public synchronized void printSpecificOptionSet(ArrayList<String> optionSets) {
		StringBuilder sb = new StringBuilder("Chosen OptionSets: \n");
		for (int i = 0; i < optionSets.size(); i++) {
			OptionSet optionSet = findOptionSet(optionSets.get(i));
			if (optionSet == null) {
				sb.append("Cannot find OptionSet ");
				sb.append(optionSets.get(i));
			} else {
				sb.append(optionSet.print());
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}