/**
 * 
 */
package org.powerapi.jjoules.display;

/**
 * class for saving a result of one sample
 * 
 * @author sanoussy
 *
 */
public class EnergySample {

	private String sampleName;
	private double energy;
	private long duration;

	public EnergySample(double energy, long duration) {
		this.energy = energy;
		this.duration = duration;
	}

	public EnergySample(String sampleName, double energyConsumed, long duration) {
		this.energy = energyConsumed;
		this.duration = duration;
		this.sampleName = sampleName;
	}

	public EnergySample(String sampleName, EnergySample res) {
		this.sampleName = sampleName;
		this.energy = res.getEnergyConsumed();
		this.duration = res.getDuration();
	}

	/**
	 * @return the duration
	 */
	public String getTestName() {
		return sampleName;
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
