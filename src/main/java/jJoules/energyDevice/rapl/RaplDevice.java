/**
 * 
 */
package jJoules.energyDevice.rapl;

import java.util.ArrayList;

import jJoules.energyDevice.EnergyDevice;
import jJoules.energyDomain.rapl.RaplDomain;
import jJoules.exceptions.NoSuchEnergyDeviceException;

/**
 * @author sanoussy
 *
 */
public class RaplDevice extends EnergyDevice{
	
	private ArrayList<RaplDomain> configuredDomains;
	private ArrayList<RaplDomain> availableDomains;

	/**
	 * 
	 */
	public RaplDevice() {
	}
	
	
	/**
	 * @param pathName path to check if it is existent path
	 * @return true if rapl path exist and false otherwise
	 */
	public static boolean raplPathExist(String pathName) {
		return false;
	}
	
	/**
	 * @return all available domain that could be monitored on the device
	 */
	public ArrayList<RaplDomain> availableDomains() throws NoSuchEnergyDeviceException {
		return null;
	}
	
	/**
	 * @param domains all domains to configure
	 */
	public void configure(ArrayList<RaplDomain> domains) {
		
	};
	
	/**
	 * @return the energy consumed by device
	 */
	public long getEnergyConsumed() {
		return 0;
	};
	

}
