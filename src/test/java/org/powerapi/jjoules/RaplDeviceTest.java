/**
 * 
 */
package org.powerapi.jjoules;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
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
		Assumptions.assumeTrue(this.device.isDeviceAvailable(), "Intel RAPL is not available on this computer");
		Assumptions.assumeTrue(this.device.isDeviceEnabled(), "Intel RAPL is not enabled on this computer");
	}

	@Test
	public void listOfAvailableDomainsShouldBeAboveZero() throws NoSuchDomainException {
		Assertions.assertTrue(this.device.listAvailableDomains().size() > 0);
	}

	@Test
	public void reportEnergyShoudBeAboveZero() throws NoSuchDomainException {
		Map<String, Long> report = this.device.recordEnergy().stop();
		for (long value : report.values())
			Assertions.assertTrue(value > 0);
	}
}
