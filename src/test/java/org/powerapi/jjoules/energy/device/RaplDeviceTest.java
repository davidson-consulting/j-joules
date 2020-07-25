/**
 * 
 */
package org.powerapi.jjoules.energy.device;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powerapi.jjoules.energy.device.rapl.RaplDevice;
import org.powerapi.jjoules.energy.domain.NoSuchDomainException;

/**
 * @author sanoussy
 *
 */
class RaplDeviceTest {

	private RaplDevice raplDevice;
		
	@BeforeEach
	public void initDevice() throws NoSuchEnergyDeviceException {
		this.raplDevice = new RaplDevice();
	}
	
	@Test
	public void getConsumedEnergy() throws DeviceNotConfiguredException, NoSuchDomainException {
		raplDevice.configure(raplDevice.getAvailableDomains());
		
		Collection<Double> consumedEnergy = raplDevice.getEnergyConsumed().values();

		for(Double d : consumedEnergy) {
			assertThat(d).isGreaterThanOrEqualTo(0);
		}
	}
}
