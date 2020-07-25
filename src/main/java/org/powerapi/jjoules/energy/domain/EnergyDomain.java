/**
 * 
 */
package org.powerapi.jjoules.energy.domain;

import org.powerapi.jjoules.energy.domain.rapl.RaplDomain;

/**
 * @author sanoussy
 *
 */
public abstract class EnergyDomain {

	/**
	 * 
	 */
	public EnergyDomain() {
		
	}
	
	/**
	 * @return a device type name
	 */
	public abstract String getDeviceType();
	
	/**
	 * @return energy consumed by domain 
	 */
	public abstract double getEneregyConsumed();
	/**
	 * @return domain name
	 */
	public abstract String getDomainName();
	
	/**
	 * @return true if other object is equals to the domain and false otherwise
	 */
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o instanceof RaplDomain) {
			RaplDomain other = (RaplDomain) o;
			return this.toString().equals(other.toString());
		} return false;
	}
	
	/**
	 * @return domain path name
	 */
	public abstract String domainPath();
	
	

}
