/**
 * 
 */
package jJoules.energyDomain.nvidia;

import jJoules.energyDomain.EnergyDomain;

/**
 * @author sanoussy
 *
 */
public class NvidiaGPUDomain extends EnergyDomain {
	
	private int nvidiaId;

	/**
	 * 
	 */
	public NvidiaGPUDomain(int nvidiaId) {
		this.nvidiaId = nvidiaId;
	}
	
	public int getNvidiaId() {
		return this.nvidiaId;
	}

	@Override
	public String getDeviceType() {
		return "NvidiaGPUDevice";
	}

	@Override
	public double getEneregyConsumed() {
		return 0;
	}

	@Override
	public String getDomainName() {
		return "nvidia_gpu";
	}
	
	public String toString() {
		return this.getDomainName()+"_"+this.getNvidiaId();
	}

}
