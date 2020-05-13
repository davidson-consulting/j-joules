/**
 * 
 */
package jJoules.rapl.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author sanoussy
 *
 */
class RaplDomainTest {
	
	private RaplPackageDomain raplPKG;
	
	@BeforeEach
	public void initDomain() {
		raplPKG = new RaplPackageDomain(0);
	}

	@Test
	public void domainPKGNameIsCorrect() throws FileNotFoundException,IOException{
		// Arrange
		String pathName = RaplPackageDomain.RAPL_PKG_PATH_NAME +this.raplPKG.getSocket()+ "/name";
		
		// Act
		String packageName = raplPKG.openAndReadFile(pathName);
		
		// Assert
		assertThat(packageName).isEqualTo(raplPKG.getDomainName());
	}
	
	@Test
	public void domainPKGConsomedEnergyFileExist() {
		String pathName = RaplPackageDomain.RAPL_PKG_PATH_NAME +this.raplPKG.getSocket()+ "/energy_uj";
		
		File file = new File(pathName);
		
		assertThat(file.exists()).isTrue();
	}
	
	
	

}
