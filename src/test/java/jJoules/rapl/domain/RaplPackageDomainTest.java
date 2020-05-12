/**
 * 
 */
package jJoules.rapl.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author sanoussy
 *
 */
class RaplPackageDomainTest {
	
	private RaplPackageDomain raplPKG;
	
	@BeforeEach
	public void initDomain() {
		raplPKG = new RaplPackageDomain();
	}

	@Test
	public void domainNameIsCorrect() throws FileNotFoundException,IOException{
		// Arrange
		String pathName = RaplPackageDomain.PATH_NAME + "/name";
		
		// Act
		String packageName = raplPKG.openAndRead(pathName);
		
		// Assert
		assertThat(packageName).isEqualTo(raplPKG.getDomainName());
	}
	
	@Test
	public void domainConsomedEnergyFileExist() {
		String pathName = RaplPackageDomain.PATH_NAME + "/energy_uj";
		File file = new File(pathName);
		
		assertThat(file.exists()).isTrue();
	}
	
	@Test
	public void domainMaxEnergyConsomedFileExist() {
		String pathName = RaplPackageDomain.PATH_NAME + "/max_energy_range_uj";
		File file = new File(pathName);
		
		assertThat(file.exists()).isTrue();
	}

}
