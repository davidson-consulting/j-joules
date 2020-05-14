/**
 * 
 */
package jJoules.energyDisplay;

import java.util.HashMap;
import java.util.Map;

import jJoules.energyDevice.EnergyDevice;
import jJoules.exceptions.DeviceNotConfiguredException;

/**
 * @author sanoussy
 *
 */
public class EnergyPrinter extends EnergyDisplayHandler {

	@Override
	public void displayIt(EnergyDevice device) {
		Map<String, Double> energyConsumedByDevice = getEnergyConsumedByDevice(device);
		for(String domainName: energyConsumedByDevice.keySet()) {
			System.out.println(domainName+" => " + getEnergyToPrint(energyConsumedByDevice, domainName));
		}
	}
	/**
	 * @param device
	 * @return energy consumed by all domain in the device
	 */
	public Map<String, Double> getEnergyConsumedByDevice(EnergyDevice device) {
		Map<String, Double> energyConsumedByDevice = new HashMap<String,Double>();
		try {
			energyConsumedByDevice = device.getEnergyConsumed();
		} catch (DeviceNotConfiguredException e) {
			e.printStackTrace();
		}
		return energyConsumedByDevice;
	}
	public double getEnergyToPrint(Map<String, Double> energyConsumed,String domainName) {
		return energyConsumed.get(domainName);		
	}

}
