/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package util;

import java.io.*;
import java.util.Properties;

import exception.AutoException;
import model.Automobile;

public class FileIO {
	private static BufferedReader buff;
	/**
	 * build Automobile based on fileName and fileType
	 * @param fileName String
	 * @param fileType: properties / txt
	 * @return Automobile
	 * @throws AutoException 
	 */
	public Automobile buildAutomobileObject(String fileName, String fileType) throws AutoException {
		switch(fileType) {
		case "txt": return buildAutomobileFromTXT(fileName);
		case "properties": return buildAutomobileFromProp(getProperties(fileName));
		default: return buildAutomobileFromTXT(fileName);
		}
	}

	public Automobile buildAutomobileFromProp(Properties props) {
		Automobile auto = new Automobile();

		// read the property
		auto.setMake(props.getProperty("CarMake"));
		auto.setName(props.getProperty("CarName"));
		auto.setModel(props.getProperty("CarModel"));
		auto.setBasePrice(Float.parseFloat(props.getProperty("BasePrice")));

		for (int countOptionSet = 1;;countOptionSet++) {
			String optionSetName = props.getProperty(getOptionSetKey(countOptionSet));
			if (optionSetName != null) {
				auto.addOptionSet(optionSetName);
				for (int countOption = 1;;countOption++) {
					String optionName = props.getProperty(getOptionNameKey(countOptionSet, countOption));
					if (optionName != null) {
						//System.out.println(getOptionPriceKey(countOptionSet, countOption));
						float optionPrice = Float.parseFloat(props.getProperty(getOptionPriceKey(countOptionSet, countOption)));
						auto.addOption(optionSetName, optionName, optionPrice);
					} else {
						break;
					}
				}
			} else {
				break;
			}
		}

		return auto;
	}

	private String getOptionSetKey(int countOptionSet) {
		StringBuilder sb = new StringBuilder("Option");
		sb.append(countOptionSet);
		sb.append("Set");
		return sb.toString();
	}

	private String getOptionNameKey(int countOptionSet, int countOption) {
		StringBuilder sb = new StringBuilder("Option");
		sb.append(countOptionSet);
		sb.append("Value");
		sb.append(countOption);
		return sb.toString();
	}

	private String getOptionPriceKey(int countOpitonSet, int countOption) {
		StringBuilder sb = new StringBuilder("Option");
		sb.append(countOpitonSet);
		sb.append("Price");
		sb.append(countOption);
		return sb.toString();
	}
	/**
	 * get Properties with the given fileName
	 * @param fileName
	 * @return
	 */
	public Properties getProperties(String fileName) {
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream(fileName);
			prop.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	private Automobile buildAutomobileFromTXT(String fileName) throws AutoException {
		String make;
		String name = "";
		String model = "";
		float basePrice = 0;
		int size = 0;
		StringBuilder sb = new StringBuilder();
		while (true) {
			try {
				FileReader file = new FileReader(fileName);
				buff = new BufferedReader(file);
				make = buff.readLine();
				model = buff.readLine();
				name = buff.readLine();
				basePrice = Float.parseFloat(buff.readLine().split(":")[1]);
				size = 0;
				String prefix = "";
				String line = buff.readLine();
				while (line != null) {
					sb.append(prefix);
					prefix = "|";
					sb.append(line);
					line = buff.readLine();
					size++;
				}
				buff.close();
				break;
			} catch (IOException e) {
				System.out.println("File not found!");
				throw new AutoException(4);
			}
		}
		Automobile auto = new Automobile(name, basePrice, size, make, model);
		String[] lines = sb.toString().split("\\|");
		for (int i = 0; i < size; i++) {
			String optionSetName = lines[i].split("::")[0];
			if (optionSetName.length() == 0) {
				optionSetName = (String)new AutoException(2).getFixedResult();
			}
			String[] options = lines[i].split("::")[1].split(",");
			String[] optionName = new String[options.length];
			float[] optionPrice = new float[options.length];
			for (int j = 0; j < options.length; j++) {
				optionName[j] = options[j].split(":")[0];
				if (optionName[j].length() == 0) {
					throw new AutoException(3);
				}
				try {
					optionPrice[j] = Float.parseFloat(options[j].split(":")[1]);
				} catch (Exception e) {
					throw new AutoException(1);
				}
			}
			auto.setOptionSet(i, optionSetName, optionName, optionPrice);
		}
		return auto;
	}

	public static String serializeAuto(Automobile auto, String serName) {
		try {
			FileOutputStream fos = new FileOutputStream(serName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(auto);
			oos.close();
			return serName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Automobile deserializaAuto(String serName) {
		try {
			FileInputStream fis = new FileInputStream(serName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Automobile result = (Automobile) ois.readObject();
			ois.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}