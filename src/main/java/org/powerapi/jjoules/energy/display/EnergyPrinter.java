/**
 * 
 */
package org.powerapi.jjoules.energy.display;

import java.util.Map;

import org.powerapi.jjoules.energy.display.utils.Result;



/**
 * @author sanoussy
 *
 */
public class EnergyPrinter extends EnergyDisplayHandler {
	
	public static final EnergyPrinter ENERGY_PRINTER = new EnergyPrinter();
	public static Map<String, Result> ALL_RESULTS;
	private EnergyPrinter() {
		super();
	}

	@Override
	public void displayIt() {
		for(String name: ALL_RESULTS.keySet()) {
			System.out.println("["+name+"] energy consumed => " + this.getEnergyToPrint(ALL_RESULTS, name)+" microJ");
			System.out.println("["+name+"] duration => " + this.getDuration(ALL_RESULTS, name)+" ms");
		}
	}

}
