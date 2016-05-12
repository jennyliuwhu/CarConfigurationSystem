/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package scale;
import java.util.Iterator;
import java.util.Map;

import model.Automobile;
import adapter.*;

public class EditOption extends Thread implements EditThreads{
	private GetLinkedHashMap auto = new BuildAuto();
	private Map<String, Automobile> linkedHashMap = auto.getLinkedHashMap();
	private String modelName;
	private String optionSetName;
	private String optionName;
	private String newName;
	private float newPrice;
	private int mode = 0;
	
	public EditOption(String Modelname, String Optionsetname, String Optionname, float newprice) {
		modelName = Modelname;
		optionSetName = Optionsetname;
		optionName = Optionname;
		newPrice = newprice;
		mode = 1;
	}
	
	public EditOption(String Modelname, String OptionSetname, String NewName) {
		modelName = Modelname;
		optionSetName = OptionSetname;
		newName = NewName;
		mode = 0;
	}
	
	public void run() {
		if(mode == 1) {
			updateOptionPrice(modelName, optionSetName, optionName, newPrice);
		}
		else {
			updateOptionSetName(modelName, optionSetName, newName);
		}
    }
	
	public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) {
		Iterator<Automobile> itr = linkedHashMap.values().iterator();
		while (itr.hasNext()){
			Automobile mob = itr.next();
			if(mob.getModel().equals(Modelname)) {
				mob.updateOptionPrice(Optionname, Option, newprice);
				return;
			}
		}
		System.out.println("UpdatePrice Auto Error: No such model");		
	}
	
	public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
		Iterator<Automobile> itr = linkedHashMap.values().iterator();
		while (itr.hasNext()){
			Automobile mob = itr.next();
			if(mob.getModel().equals(Modelname)) {
				mob.updateOptionSet(OptionSetname, newName);
				return;
			}
		}
		System.out.println("UpdateName Auto Error: No such model");		
	}
}


