/**
 * 
 */
package jJoules.energyDevice.rapl;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jJoules.exceptions.DeviceNotConfiguredException;
import jJoules.exceptions.NoSuchDomainException;
import jJoules.exceptions.NoSuchEnergyDeviceException;

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
