/**
 * 
 */
package jJoules.energyDevice;

import java.util.ArrayList;

import jJoules.energyDomain.rapl.RaplDomain;
import jJoules.exceptions.NoSuchDomainException;
import jJoules.exceptions.NoSuchEnergyDeviceException;
import jJoules.exceptions.DeviceNotConfiguredException;

/**
 * @author sanoussy
 *
 */
public class EnergyDevice {
	
	private ArrayList<RaplDomain> configuredDomains;
	private ArrayList<RaplDomain> availableDomains;

	/**
	 * 
	 */
	public EnergyDevice() {
	}
	
	/**
	 * @param domains all domains to configure
	 */
	public void configure(ArrayList<RaplDomain> domains) throws NoSuchDomainException{
		
	};
	
	/**
	 * @return configured domain for device
	 */
	public ArrayList<RaplDomain> getConfiguredDomains() throws DeviceNotConfiguredException{
		return null;
	}
	
	/**
	 * @return available Domains
	 */
	public ArrayList<RaplDomain> getAvailableDomains(){
		return null;
	}
	
	/**
	 * @return all available domain that could be monitored on the device
	 */
	public ArrayList<RaplDomain> availableDomains() throws NoSuchEnergyDeviceException {
		return null;
	}
	
	/**
	 * @return the energy consumed by device
	 */
	public long getEnergyConsumed() {
		return 0;
	}
	
	public static boolean isANumber(String s){
		for(Character c : s.toCharArray()) {
			if(!Character.isDigit(c))
				return false;
		}return true;
	}

}
