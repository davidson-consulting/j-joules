/**
 * 
 */
package jJoules.energyDomain.rapl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
/**
 * @author sanoussy
 *
 */
public class RaplDomainTest {
	
	@Test
	public void domainPkgNameIsCorrect() throws FileNotFoundException,IOException{
			RaplPackageDomain pkg = new RaplPackageDomain(0);
			String pathName = pkg.domainPath() +pkg.getSocket()+ "/name";
			String domainName = RaplDomain.openAndReadFile(pathName);
			assertThat(domainName).isEqualTo(pkg.getDomainName()+"-"+pkg.getSocket());
	}
	
	@Test
	public void domainCoreNameIsCorrect() throws FileNotFoundException,IOException{
			RaplCoreDomain core = new RaplCoreDomain(0);
			String pathName = core.domainPath() +core.getSocket()+ "/name";
			String domainName = RaplDomain.openAndReadFile(pathName);
			assertThat(domainName).isEqualTo(core.getDomainName());
	}
	
	@Test
	public void domainUncoreNameIsCorrect() throws FileNotFoundException,IOException{
			RaplCoreDomain uncore = new RaplCoreDomain(0);
			String pathName = uncore.domainPath() +uncore.getSocket()+ "/name";
			String domainName = RaplDomain.openAndReadFile(pathName);
			assertThat(domainName).isEqualTo(uncore.getDomainName());
	}
	
	@Test
	public void domainDramNameIsCorrect() throws FileNotFoundException,IOException{
			RaplCoreDomain dram = new RaplCoreDomain(0);
			String pathName = dram.domainPath() +dram.getSocket()+ "/name";
			String domainName = RaplDomain.openAndReadFile(pathName);
			assertThat(domainName).isEqualTo(dram.getDomainName());
	}
	
	@ParameterizedTest(name = "{2} energy consumed file exist")
	@CsvSource({"0,/intel-rapl:,package","0,/intel-rapl:0/intel-rapl:0:,core","1,/intel-rapl:0/intel-rapl:0:,uncore","2,/intel-rapl:0/intel-rapl:0:,dram"})
	public void domainConsomedEnergyFileExist(int socket,String pathEnd,String name) {
		String path = RaplDomain.RAPL_PATH_NAME + pathEnd + socket+ "/energy_uj";
		
		assertThat(RaplDomain.domainPathExist(path)).isTrue();
	}
	
	@Test
	public void pkgDomainConsumedEnergyFileContentIsNumeric() {
		assertThat(new RaplPackageDomain(0).getEneregyConsumed()).isGreaterThanOrEqualTo(0);
	}
	
	@Test
	public void coreDomainConsumedEnergyFileContentIsNumeric() {
		assertThat(new RaplCoreDomain(0).getEneregyConsumed()).isGreaterThanOrEqualTo(0);
	}
	@Test
	public void uncoreDomainConsumedEnergyFileContentIsNumeric() {
		assertThat(new RaplUncoreDomain(1).getEneregyConsumed()).isGreaterThanOrEqualTo(0);
	}
	@Test
	public void dramDomainConsumedEnergyFileContentIsNumeric() {
		assertThat(new RaplDramDomain(2).getEneregyConsumed()).isGreaterThanOrEqualTo(0);
	}
	
//	@ParameterizedTest(name = "for id-{0} => domainName_{0}")
//	@ValueSource(ints = {0,1})
//	public void toStringReturnARepresentionWithSocketId(int arg) {
//		RaplDomain domain = this.createDomain(arg);
//		assertThat(domain.toString()).isEqualTo(domain.getDomainName()+"_"+arg);
//	}
//	
//	@ParameterizedTest(name = "domain id-{0} is equals to domain id-{1} => {2}")
//	@CsvSource({"0,0,true","0,1,false","1,0,false"})
//	public void equalsReturnAGoodValueTest(int id1,int id2, boolean res) {
//		
//		RaplDomain pkg1 ,pkg2;
//		pkg1 = this.createDomain(id1);
//		pkg2 = this.createDomain(id2);
//		
//		assertThat(pkg1.equals(pkg2)).isEqualTo(res);
//		
//	}
}
