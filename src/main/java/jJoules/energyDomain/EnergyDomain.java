/**
 * 
 */
package jJoules.energyDomain;

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

}
