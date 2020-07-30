/**
 * 
 */
package org.powerapi.jjoules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powerapi.jjoules.rapl.RaplDevice;

/**
 * Basic tests for running J-Joules
 *
 */
class RaplDeviceTest {

	private RaplDevice device;

	@BeforeEach
	public void initDevice() {
		this.device = RaplDevice.RAPL;
	}

	@Test
	public void listOfAvailableDomainsShouldBeAboveZero() throws NoSuchDomainException {
		if (this.device.isDeviceAvailable() && this.device.isDeviceEnabled()) {
			assertThat(this.device.listAvailableDomains().size()).isGreaterThan(0);
		} else
			fail("Intel RAPL is not available on this computer");
	}

	@Test
	public void reportEnergyShoudBeAboveZero() throws NoSuchDomainException {
		if (this.device.isDeviceAvailable() && this.device.isDeviceEnabled()) {
			Map<String, Long> report = this.device.recordEnergy().stop();

			for (long value : report.values()) {
				assertThat(value).isGreaterThanOrEqualTo(0);
			}
		} else
			fail("Intel RAPL is not available on this computer");
	}
}
