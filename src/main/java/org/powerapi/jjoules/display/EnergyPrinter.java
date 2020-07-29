/**
 * 
 */
package org.powerapi.jjoules.display;

import java.util.Map;

/**
 * @author sanoussy
 *
 */
public class EnergyPrinter extends EnergyDisplayHandler {

	public static final EnergyPrinter ENERGY_PRINTER = new EnergyPrinter();
	public static Map<String, EnergySample> ALL_RESULTS;

	private EnergyPrinter() {
		super();
	}

	@Override
	public void displayIt() {
		for (String name : ALL_RESULTS.keySet()) {
			System.out.println("[" + name + "] energy consumed => " + this.getEnergyToPrint(ALL_RESULTS, name) + " uJ");
			System.out.println("[" + name + "] duration => " + this.getDuration(ALL_RESULTS, name) + " ms");
		}
	}

}
