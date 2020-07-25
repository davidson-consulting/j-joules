/**
 * 
 */
package org.powerapi.jjoules.energy.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.powerapi.jjoules.energy.device.NoSuchEnergyDeviceException;
import org.powerapi.jjoules.energy.device.rapl.RaplDevice;
import org.powerapi.jjoules.energy.domain.EnergyDomain;
import org.powerapi.jjoules.energy.domain.rapl.RaplDomain;

/**
 * @author sanoussy
 *
 */
public class RaplDomainTest {
	
	public static RaplDevice device;
	public static  ArrayList<EnergyDomain> availableDomains;
	
	@BeforeAll
	public static void init() {
		try {
			device = new RaplDevice();
			availableDomains = device.getAvailableDomains();
		} catch (NoSuchEnergyDeviceException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void domainNameIsCorrect() {
		for(EnergyDomain domain : availableDomains) {
			RaplDomain raplDomain = (RaplDomain) domain;
			String domainName = RaplDomain.openAndReadFile(raplDomain.domainPath()+"/name");
			assertThat(domainName).isIn(raplDomain.getDomainName(),raplDomain.getDomainName()+"-"+raplDomain.getSocket(),"");
		}
	}
	
	@Test
	public void domainConsumedEnergyFileExist() {
		for(EnergyDomain domain : availableDomains) {
			String path = domain.domainPath()+ "/energy_uj";
			assertThat(RaplDomain.domainPathExist(path)).isTrue();
		}
	}
	

	@Test
	public void domainConsumedEnergyFileContentIsNumeric() {
		for(EnergyDomain domain : availableDomains) {
			if(RaplDomain.domainPathExist(domain.domainPath()))
				assertThat(domain.getEneregyConsumed()).isGreaterThanOrEqualTo(0);
		}
	}
	
	@Test
	public void domainRepresentationIsCorrect() {
		for(EnergyDomain domain : availableDomains) {
			RaplDomain raplDomain = (RaplDomain) domain;
			assertThat(domain.toString()).isIn(raplDomain.getDomainName()+"_"+raplDomain.getSocket());
		}
	}
	
}
