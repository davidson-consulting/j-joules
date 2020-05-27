/**
 * 
 */
package jJoules.energyDisplay;

import java.util.Map;


/**
 * @author sanoussy
 *
 */
public class EnergyPrinter extends EnergyDisplayHandler {
	
	public static final EnergyPrinter ENERGY_PRINTER = new EnergyPrinter();
	
	private EnergyPrinter() {
		super();
	}

	@Override
	public void displayIt(Map<String, Double> energyConsumedByDevice) {
		for(String domainName: energyConsumedByDevice.keySet()) {
			System.out.println(domainName+" => " + this.getEnergyToPrint(energyConsumedByDevice, domainName));
		}
	}

}
