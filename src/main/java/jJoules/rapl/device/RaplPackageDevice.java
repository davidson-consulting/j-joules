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
public class RaplPackageDevice extends RaplDevice {

	/**
	 * 
	 */
	public RaplPackageDevice() {
	}
	

	@Override
	public boolean raplPathExist(String pathName) {
		return false;
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
