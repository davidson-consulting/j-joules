/**
 * 
 */
package jJoules.energyDevice.rapl;

import java.util.ArrayList;

import jJoules.energyDomain.rapl.RaplDomain;

/**
 * @author sanoussy
 *
 */
public class RaplPackageDevice extends RaplDevice {

	/**
	 * 
	 */
	public RaplPackageDevice() {
	}
	
	@Override
	public ArrayList<RaplDomain> availableDomains() {
		return null;
	}


	@Override
	public void configure(ArrayList<RaplDomain> domains) {
		
	}


	@Override
	public long getEnergyConsumed() {
		return 0;
	}

}
