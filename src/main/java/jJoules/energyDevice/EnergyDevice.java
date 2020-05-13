/**
 * 
 */
package jJoules.energyDevice;

import java.util.ArrayList;

import jJoules.energyDomain.EnergyDomain;
import jJoules.exceptions.DeviceNotConfiguredException;
import jJoules.exceptions.NoSuchDomainException;
import jJoules.exceptions.NoSuchEnergyDeviceException;

/**
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
	 * @param domains all domains to configure
	 */
	public void configure(ArrayList<EnergyDomain> domains) throws NoSuchDomainException{
		for(EnergyDomain domain : domains) {
			if(! this.availableDomains.contains(domain))
				throw new NoSuchDomainException();
		}
		this.configuredDomains = domains;
	}
	
	/**
	 * @return configured domain for device
	 */
	public ArrayList<EnergyDomain> getConfiguredDomains() throws DeviceNotConfiguredException{
		if (this.configuredDomains.isEmpty())
			throw new DeviceNotConfiguredException();
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
	public abstract ArrayList<Double> getEnergyConsumed() throws DeviceNotConfiguredException;
	
	
	public static boolean isANumber(String s){
		for(Character c : s.toCharArray()) {
			if(!Character.isDigit(c))
				return false;
		}return true;
	}

}
