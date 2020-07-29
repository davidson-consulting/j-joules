/**
 * 
 */
package org.powerapi.jjoules.display;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.powerapi.jjoules.DeviceNotConfiguredException;
import org.powerapi.jjoules.EnergyDevice;

/**
 * @author sanoussy
 *
 */
public abstract class EnergyDisplayHandler {

	public static String OUTPUT_DIRECTORY;

	public static void initDir() {
		File newDir = new File("target/jjoules-report");
		newDir.mkdir();
		OUTPUT_DIRECTORY = newDir.getAbsolutePath();
	}

	/**
	 * Display energy consumed according to the way of representation either on the
	 * screen or saved in file ...
	 * 
	 * @param energyConsumedByDevice energy consumed by all configured domains
	 */
	public abstract void displayIt();

	/**
	 * @param device
	 * @return energy consumed by all domain in the device
	 */
	public Map<String, Double> getEnergyConsumedByDevice(EnergyDevice device) {
		Map<String, Double> energyConsumedByDevice = new HashMap<String, Double>();
		try {
			energyConsumedByDevice = device.getEnergyConsumed();
		} catch (DeviceNotConfiguredException e) {
			e.printStackTrace();
		}
		return energyConsumedByDevice;
	}

	/**
	 * @param energyConsumed energy consumed by all configured domains
	 * @param domainName     domain name which looking for energy consumed
	 * @return energy consumed by domain
	 */
	public double getEnergyToPrint(Map<String, EnergySample> energyConsumed, String domainName) {
		return energyConsumed.get(domainName).getEnergyConsumed();
	}

	/**
	 * @param energyConsumed energy consumed by all configured domains
	 * @param domainName     domain name which looking for energy consumed
	 * @return energy consumed by domain
	 */
	public long getDuration(Map<String, EnergySample> energyConsumed, String domainName) {
		return energyConsumed.get(domainName).getDuration();
	}

	/**
	 * @param energyConsumedByDevice
	 */
	public static void saveResultOfClass(Map<String, EnergySample> energyConsumedByDevice, String currentClassName,
			List<Data> allData) {
		List<EnergySample> results = new ArrayList<EnergySample>();
		for (String name : energyConsumedByDevice.keySet()) {
			results.add(new EnergySample(name, energyConsumedByDevice.get(name)));
		}
		Data data = new Data(currentClassName, results);
		allData.add(data);
	}

}
