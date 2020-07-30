/**
 * 
 */
package org.powerapi.jjoules;

import java.util.Collection;
import java.util.Map;

/**
 * Class that abstract any device reporting energy consumption metrics
 *
 */
public abstract class EnergyDevice {
	private final Collection<EnergyDomain> availableDomains;
	private Collection<EnergyDomain> selectedDomains;

	/**
	 * @throws NoSuchEnergyDeviceException thrown if no domain was found for the
	 *                                     current device
	 * 
	 */
	public EnergyDevice() {
		this.availableDomains = this.listAvailableDomains();
		this.selectedDomains = this.availableDomains;
	}

	/**
	 * Selects specific energy domains to be monitored
	 * 
	 * @param domains all domains to configure
	 * @throws NoSuchDomainException
	 */
	public void selectDomain(Collection<EnergyDomain> domains) throws NoSuchDomainException {
		for (EnergyDomain domain : domains)
			if (!this.availableDomains.contains(domain))
				throw new NoSuchDomainException(domain);
		this.selectedDomains = domains;
	}

	/**
	 * Lists the specific domains to be monitored (by default all the available
	 * domains)
	 */
	public Collection<EnergyDomain> listSelectedDomains() {
		return this.selectedDomains;
	}

	public EnergySample recordEnergy() {
		return new EnergySample(this);
	}

	/**
	 * @return all available domain that could be monitored on the device
	 */
	public abstract Collection<EnergyDomain> listAvailableDomains();
	
	/**
	 * @return the energy consumed by device
	 * @throws DeviceNotConfiguredException
	 */
	protected abstract Map<String, Long> getDomainCounters();


	/**
	 * @return the energy consumed by device
	 * @throws DeviceNotConfiguredException
	 */
	protected abstract Map<String, Long> getMaxDomainCounters();
}
