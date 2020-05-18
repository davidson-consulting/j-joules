/**
 * 
 */
package jJoules;

import jJoules.energyDomain.EnergyDomain;

/**
 * @author sanoussy
 *
 */
public class EnergyMesureIt {
	
	private double enegyBefore;
	
	private EnergyDomain domain;
	
	public EnergyMesureIt(EnergyDomain domain) {
		this.domain = domain;
	}
	
	/**
	 * @return the energy consumed before checking 
	 */
	public double getEnergyBefore() {
		return this.enegyBefore;
	}
	/**
	 * 
	 */
	public void begin() {
		this.enegyBefore = this.domain.getEneregyConsumed();
		//System.out.println("Start => "+ this.getEnergyBefore());
	}
	/**
	 * 
	 */
	public double end() {
		double end = this.domain.getEneregyConsumed();
		//System.out.println("end => "+end);
		//System.out.println("diff => "+ (end - this.getEnergyBefore()));
		return end - this.getEnergyBefore();
	}

}
