/**
 * 
 */
package org.powerapi.jjoules;

import java.util.ArrayList;
import java.util.Map;

import org.powerapi.jjoules.domain.EnergyDomain;
import org.powerapi.jjoules.domain.NoSuchDomainException;


/**
 * Class to get energy consumption information for specified device
 * @author sanoussy
 *
 */
public abstract class EnergyDevice {
	
	private ArrayList<EnergyDomain> configuredDomains;
	private ArrayList<EnergyDomain> availableDomains;

	/**
	 * @throws NoSuchEnergyDeviceException 
	 * 
	 */
	public EnergyDevice() throws NoSuchEnergyDeviceException {
		this.configuredDomains = new ArrayList<EnergyDomain>();
		this.availableDomains = this.availableDomains();
	}
	
	/**
	 * Give all domains those could be monitored on the device 
	 * @param domains all domains to configure
	 * @throws NoSuchDomainException 
	 */
	public void configure(ArrayList<EnergyDomain> domains) throws NoSuchDomainException{
		for(EnergyDomain domain : domains) {
			if(! this.availableDomains.contains(domain))
				throw new NoSuchDomainException(domain);
		}
		this.configuredDomains = domains;
	}
	
	/**
	 * @return configured domain for device
	 * @throws DeviceNotConfiguredException
	 */
	public ArrayList<EnergyDomain> getConfiguredDomains() throws DeviceNotConfiguredException{
		if (this.configuredDomains.isEmpty())
			throw new DeviceNotConfiguredException("No configured device");
		return this.configuredDomains;
	}
	
	/**
	 * @return available Domains
	 */
	public ArrayList<EnergyDomain> getAvailableDomains(){
		return this.availableDomains;
	}
	
	/**
	 * @return all available domain that could be monitored on the device
	 */
	public abstract ArrayList<EnergyDomain> availableDomains() throws NoSuchEnergyDeviceException;
	
	/**
	 * @return the energy consumed by device
	 * @throws DeviceNotConfiguredException 
	 */
	public abstract Map<String,Double> getEnergyConsumed() throws DeviceNotConfiguredException;

}
