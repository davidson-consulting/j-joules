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
public abstract class EnergyDisplayHandler {
	
	public abstract void displayIt(Map<String, Double> energyConsumedByDevice);
	
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
