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

	@Override
	public void displayIt(Map<String, Double> energyConsumedByDevice) {
		for(String domainName: energyConsumedByDevice.keySet()) {
			System.out.println(domainName+" => " + this.getEnergyToPrint(energyConsumedByDevice, domainName));
		}
	}

}
