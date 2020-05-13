/**
 * 
 */
package jJoules.energyDomain.rapl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import jJoules.energyDevice.EnergyDevice;
import jJoules.energyDomain.rapl.RaplDomain;
/**
 * @author sanoussy
 *
 */
class RaplDomainTest {
	
	private RaplDomain raplDomain;
	
	public RaplDomain createDomain(int socket) {
		return new MockRaplDomain(socket);
	}
	
	@BeforeEach
	public void initDomain() {
		raplDomain = this.createDomain(0);
	}

	@Test
	public void domainNameIsCorrect() throws FileNotFoundException,IOException{
		
		if (! (raplDomain instanceof MockRaplDomain)) {
			String pathName = raplDomain.domainPath() +this.raplDomain.getSocket()+ "/name";
			
			String packageName = RaplDomain.openAndReadFile(pathName);
			
			assertThat(packageName).isEqualTo(raplDomain.getDomainName()+"-"+raplDomain.getSocket());
		}
	}
	
	@Test
	public void domainConsomedEnergyFileExist() {
		String pathName = raplDomain.domainPath() +this.raplDomain.getSocket()+ "/energy_uj";
		
		assertThat(this.raplDomain.domainPathExist(pathName)).isTrue();
	}
	
	@Test
	public void domainConsumedEnergyFileContentIsNumeric() {
		assertThat(raplDomain.getEneregyConsumed()).isGreaterThanOrEqualTo(0);
	}
	
	
	@ParameterizedTest(name = "for id-{0} => domainName_{0}")
	@ValueSource(ints = {0,1})
	public void toStringReturnARepresentionWithSocketId(int arg) {
		RaplDomain domain = this.createDomain(arg);
		
		assertThat(domain.toString()).isEqualTo(domain.getDomainName()+"_"+arg);
	}
	
	@ParameterizedTest(name = "domain id-{0} is equals to domain id-{1} => {2}")
	@CsvSource({"0,0,true","0,1,false","1,0,false"})
	public void equalsReturnAGoodValueTest(int id1,int id2, boolean res) {
		
		RaplDomain pkg1 ,pkg2;
		pkg1 = this.createDomain(id1);
		pkg2 = this.createDomain(id2);
		
		assertThat(pkg1.equals(pkg2)).isEqualTo(res);
		
		
	}
	
	/**
	 * class for simulating the RaplDomain for the test
	 * @author sanoussy
	 *
	 */
	class MockRaplDomain extends RaplDomain{

		public MockRaplDomain(int socket) {
			super(socket);
		}

		@Override
		public String getDomainName() {
			return "moke";
		}
		public boolean domainPathExist(String pathName) {
			return true;
		}

		@Override
		public double getEneregyConsumed() {
			return 0;
		}
		
	}
	
	
	

}
