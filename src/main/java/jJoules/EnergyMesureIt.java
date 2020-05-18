/**
 * 
 */
package jJoules;

import jJoules.energyDomain.EnergyDomain;
import jJoules.energyDomain.rapl.RaplPackageDomain;

/**
 * @author sanoussy
 *
 */
public class EnergyMesureIt {
	
	private double enegyBefore;
	private double energyAfter;
	
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
	 * @return the energy consumed before checking 
	 */
	public double getEnergyAfter() {
		return this.energyAfter;
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
		this.energyAfter = this.domain.getEneregyConsumed();
		//System.out.println("end => "+end);
		//System.out.println("diff => "+ (end - this.getEnergyBefore()));
		return this.energyAfter - this.enegyBefore;
	}
	
	public static void main(String[] args) {
		RaplPackageDomain pkg = new RaplPackageDomain(0);
		EnergyMesureIt mesureIt = new EnergyMesureIt(pkg);
		
		mesureIt.begin();
		System.out.println("before => "+mesureIt.getEnergyBefore());
		
		for(int i=0;i<10000; i++) {}
		
		double diff = mesureIt.end();
		System.out.println("after => "+ mesureIt.getEnergyAfter());
		System.out.println("diff => "+diff);
	}

}
