/**
 * 
 */
package org.powerapi.jjoules;

/**
 * Class that provides an abstraction of any domain exposed by a device.
 * 
 */
public abstract class EnergyDomain {
	/**
	 * @return energy consumed by domain
	 */
	public abstract long getDomainCounter();

	/**
	 * @return energy consumed by domain
	 */
	public abstract long getMaxDomainCounter();

	/**
	 * @return domain name
	 */
	public abstract String getDomainName();

	public abstract boolean isDomainAvailable();

	/**
	 * @return true if other object is equals to the domain and false otherwise
	 */
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other instanceof EnergyDomain) {
			EnergyDomain domain = (EnergyDomain) other;
			return getDomainName().equals(domain.getDomainName());
		}
		return false;
	}

}
