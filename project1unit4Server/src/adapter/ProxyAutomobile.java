/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package adapter;
import exception.AutoException;
import model.*;
import util.FileIO;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import driver.Driver;
import scale.*;
import server.BuildCarModelOptions;

public abstract class ProxyAutomobile {
	private static BuildCarModelOptions server = new BuildCarModelOptions(Driver.getPortNum());
	private static Automobile a1;
	private static Map<String, Automobile> linkedHashMap = new LinkedHashMap<String, Automobile>();

	public void buildAuto(String filename, String fileType) {
		FileIO io = new FileIO();
		try {
			a1 = io.buildAutomobileObject(filename, fileType);
		} catch (AutoException e) {
			try {
				a1 = io.buildAutomobileObject((String)e.getFixedResult(), fileType);
			}
			catch(AutoException e1) {	
			}
		}
		appendToMap(a1);
	}
	
	public Automobile buildAutoWithProp(Properties prop) {
		FileIO io = new FileIO();
		return io.buildAutomobileFromProp(prop);
	}
	public void appendToMap(Automobile auto) {
		linkedHashMap.put(Integer.toString(linkedHashMap.size() + 1), auto);
	}
	
	public void printAuto(String ModelName) {
		Iterator<Automobile> itr = linkedHashMap.values().iterator();
		while (itr.hasNext()){
			Automobile mob = itr.next();
			if(mob.getModel().equals(ModelName)) {
				mob.print();
				return;
			}
		}
		System.out.println("Print Auto Error: No such model");
	}
	
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
		EditThreads editOpt = new EditOption(Modelname, OptionSetname, newName);
		Thread t1 = new Thread((Runnable) editOpt);
		t1.start();
	}
	
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) {
		EditThreads editOpt = new EditOption(Modelname, Optionname, Option, newprice);
		Thread t1 = new Thread((Runnable) editOpt);
		t1.start();
	}
	
	public void fix(int errno) {
		AutoException exc = new AutoException(errno);
		exc.fix(errno);
	}
	
	public Map<String, Automobile> getLinkedHashMap() {
		return linkedHashMap;
	}
	
	public void acceptPropObject() {
		server.acceptPropObject();
	}
	
	public void availableAutoList() {
		server.availableAutoList();
	}

}
