/**
 * 
 */
package jJoules.rapl.device;

import java.util.ArrayList;

import jJoules.rapl.domain.RaplDomain;

/**
 * @author sanoussy
 *
 */
/**
 * @author sanoussy
 *
 */
public abstract class RaplDevice {
	
	private ArrayList<RaplDomain> configuredDomains;
	private ArrayList<RaplDomain> availableDomains;

	/**
	 * 
	 */
	public RaplDevice() {
	}
	
	
	/**
	 * @return configured domain for device
	 */
	public ArrayList<RaplDomain> getConfiguredDomains(){
		return null;
	}
	
	/**
	 * @return available Domains
	 */
	public ArrayList<RaplDomain> getAvailableDomains(){
		return null;
	}
	
	/**
	 * @param pathName path to check if it is existent path
	 * @return true if rapl path exist and false otherwise
	 */
	public boolean raplPathExist(String pathName) {
		return false;
	}
	
	/**
	 * @return give all available domain for device
	 */
	public abstract ArrayList<RaplDomain> availableDomains();
	
	/**
	 * @param domains all domains to configure
	 */
	public abstract void configure(ArrayList<RaplDomain> domains);
	
	/**
	 * @return the energy consumed by device
	 */
	public abstract long getEnergyConsumed();
	

}
