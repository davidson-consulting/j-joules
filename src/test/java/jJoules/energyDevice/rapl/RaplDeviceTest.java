/**
 * 
 */
package jJoules.energyDevice.rapl;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author sanoussy
 *
 */
class RaplDeviceTest {

	private RaplDevice raplDevice;
		
	public void initDevice() {
		this.raplDevice = new RaplDevice();
	}
	
	@Test
	public void getConsumedEnergy() {
		double consumedEnergy = this.raplDevice.getEnergyConsumed();
		
		assertThat(consumedEnergy).isGreaterThanOrEqualTo(0);
	}
}
