/**
 * 
 */
package org.powerapi.jjoules.energy.display.utils;


/**
 * class for saving a result of one test method
 * @author sanoussy
 *
 */
public class Result {
	
	private String testName;
	private double energy;
	private long duration;

	/**
	 * 
	 */
	public Result(double energyConsumed,long duration) {
		this.energy = energyConsumed;
		this.duration = duration;
	}
	public Result(String testName,double energyConsumed,long duration) {
		this.energy = energyConsumed;
		this.duration = duration;
		this.testName = testName;
	}
	
	public Result(String testName,Result res) {
		this.testName = testName;
		this.energy = res.getEnergyConsumed();
		this.duration = res.getDuration();
	}

	
	/**
	 * @return the duration
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * @return the energyConsumed
	 */
	public double getEnergyConsumed() {
		return energy;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}
}
