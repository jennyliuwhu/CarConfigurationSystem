/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionSet implements Serializable{
	private static final long serialVersionUID = 1L;

	// opt = [different Colors]/[different kinds of Transmissions]/...etc
	private ArrayList<Option> opt;

	// name = Color/Transmission/...etc
	private String name;
	
	//
	private Option choice = new Option();
	// default constructor
	public OptionSet() {}
	
	public OptionSet(String name) {
		this.opt = new ArrayList<>();
		this.name = name;
	}
	// overload constructor
	public OptionSet(String name, int size) {
		this.opt = new ArrayList<>();
		this.name = name;
		for (int i = 0; i < size; i++) {
			this.opt.add(new Option());
		}
	}

	// find Option with name
	public Option findOption(String name) {
		for (int i = 0; i < this.opt.size(); i++) {
			if (name.equals(this.opt.get(i).getName())) {
				return this.opt.get(i);
			}
		}
		return null;
	}

	public void setOption(String[] names, float[] prices) {
		for (int i = 0; i < names.length; i++) {
			this.opt.get(i).setName(names[i]);
			this.opt.get(i).setPrice(prices[i]);
		}
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public ArrayList<Option> getOption() {
		return this.opt;
	}
	
	public void setOptionChoice(String optionName) {
		for (int i = 0; i < this.opt.size(); i++) {
			String option = this.opt.get(i).getName();
			if (option == optionName) {
				this.choice = this.opt.get(i);
				return;
			}
		}
		System.out.println("Did not find this option!");
	}
	
	public void addOption(String optionName, float optionPrice) {
		Option newOption = new Option();
		newOption.setName(optionName);
		newOption.setPrice(optionPrice);
		this.opt.add(newOption);
	}
	
	public Option getOptionChoice() {
		return this.choice;
	}
	// delete an option with name, set it to a new Option, then return the original
	public String deleteOption(String name) {
		Option find = findOption(name);
		if (find == null) {
			return null;
		} else {
			find = new Option();
			return name;
		}
	}

	// update the price of an option
	public void updateOptionPrice(String option, float newPrice) {
		Option find = findOption(option);
		if (find == null) {
			return;
		} else {
			find.setPrice(newPrice);
		}
	}

	// update values of option
	public String updateOption(String name, String newName, float newPrice) {
		Option find = findOption(name);
		if (find == null) {
			return null;
		} else {
			find.setName(newName);
			find.setPrice(newPrice);
			return name;
		}
	}
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("::");
		String prefix = "";
		for (int i = 0; i < this.opt.size(); i++) {
			sb.append(prefix);
			prefix = ",";
			sb.append(this.opt.get(i).print());
		}
		return sb.toString();
	}
	// Inner Class Option of OptionSet
	protected class Option implements Serializable{
		private static final long serialVersionUID = 1L;
		private String name;
		private float price;
		protected void setName(String name) {
			this.name = name;
		}
		protected void setPrice(float price) {
			this.price = price;
		}
		protected String getName() {
			return this.name;
		}
		protected float getPrice() {
			return this.price;
		}
		protected String print() {
			StringBuilder sb = new StringBuilder();
			sb.append(name);
			sb.append(':');
			sb.append(price);
			return sb.toString();
		}
	}
}