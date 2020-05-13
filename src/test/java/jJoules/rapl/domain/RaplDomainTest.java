/**
 * 
 */
package jJoules.rapl.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
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
		
		assertThat(this.raplPKG.domainPathExist(pathName)).isTrue();
	}
	
	@ParameterizedTest(name = "for id-{0} => package_{0}")
	@ValueSource(ints = {0,1})
	public void toStringReturnARepresentionWithSocketId(int arg) {
		RaplPackageDomain pkg = new RaplPackageDomain(arg);
		
		assertThat(pkg.toString()).isEqualTo("package_"+arg);
	}
	
	@ParameterizedTest(name = "domain id-{0} is equals to domain id-{1} => {2}")
	@CsvSource({"0,0,true","0,1,false","1,0,false"})
	public void equalsReturnAGoodValueTest(int id1,int id2, boolean res) {
		
		RaplPackageDomain pkg1 ,pkg2;
		pkg1 = new RaplPackageDomain(id1);
		pkg2 = new RaplPackageDomain(id2);
		
		assertThat(pkg1.equals(pkg2)).isEqualTo(res);
		
		
	}
	
	
	

}
