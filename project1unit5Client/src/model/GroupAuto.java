/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package model;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * This class does CRUD operations on groups of Automobile
 * @author jialingliu
 *
 */
public class GroupAuto {
	private static LinkedHashMap<String, Automobile> autos = new LinkedHashMap<>();
	
	/**
	 * add new Automobile to existed autos
	 * @param name String
	 * @param auto Automobile
	 */
	public void addAuto(String name, Automobile auto) {
		System.out.println("Add this Automobile to groups.");
		autos.put(name, auto);
	}
	
	/**
	 * print all auto information in the autos
	 */
	public void printGroup(String modelName) {
		Iterator<Automobile> itr = autos.values().iterator();
		while (itr.hasNext()){
			Automobile mob = itr.next();
			if(mob.getModel().equals(modelName)) {
				mob.print();
				return;
			}
		}
		System.out.println("Print Auto Error: No such model");
	}
}
