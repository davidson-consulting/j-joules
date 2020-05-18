/**
 * 
 */
package jJoules;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import jJoules.energyDomain.rapl.RaplPackageDomain;

/**
 * @author sanoussy
 *
 */
class EnergyMesureItTest {

	@Test
	public void mesureItGiveAnEnergyMesuredValueGreaterOrEqualsToValueBeforeCheking() {
		EnergyMesureIt energyMesureIt = new EnergyMesureIt(new RaplPackageDomain(0));
		energyMesureIt.begin();
		for(int i=0;i<1000;i++) {}
		assertThat(energyMesureIt.end()).isGreaterThanOrEqualTo(0);
	}

}
