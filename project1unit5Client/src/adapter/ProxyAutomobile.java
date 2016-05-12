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
import scale.*;

public abstract class ProxyAutomobile {
	private static Automobile a1;
	private static Automobile a2;
	private static Automobile a3;
	private static Map<String, Automobile> linkedHashMap = new LinkedHashMap<String, Automobile>();

	public void buildAuto(String filename, String filename1, String filename2) {
		FileIO io = new FileIO();
		try {
			a1 = io.buildAutomobileObject(filename);
		} catch (AutoException e) {
			try {
				a1 = io.buildAutomobileObject((String)e.getFixedResult());
			}
			catch(AutoException e1) {	
			}
		}
		
		try {
			a2 = io.buildAutomobileObject(filename1);
		} catch (AutoException e) {
			try {
				a2 = io.buildAutomobileObject((String)e.getFixedResult());
			}
			catch(AutoException e1) {	
			}
		}
		
		try {
			a3 = io.buildAutomobileObject(filename2);
		} catch (AutoException e) {
			try {
				a3 = io.buildAutomobileObject((String)e.getFixedResult());
			}
			catch(AutoException e1) {	
			}
		}
		/*add these mobile to linked hash map*/
		linkedHashMap.put("1", a1);
		linkedHashMap.put("2", a2);
		linkedHashMap.put("3", a3);
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
	

}
