/**
 * 
 */
package jJoules.energyDomain.rapl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jJoules.energyDevice.rapl.RaplDevice;
import jJoules.energyDomain.EnergyDomain;
import jJoules.exceptions.NoSuchEnergyDeviceException;
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
			assertThat(domainName).isIn(raplDomain.getDomainName(),raplDomain.getDomainName()+"-"+raplDomain.getSocket());
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
	
//	@Test
//	public void domainPkgNameIsCorrect(){
//			RaplPackageDomain pkg = new RaplPackageDomain(0);
//			String pathName = pkg.domainPath()+ "/name";
//			String domainName = RaplDomain.openAndReadFile(pathName);
//			assertThat(domainName).isEqualTo(pkg.getDomainName()+"-"+pkg.getSocket());
//	}
//	
//	@Test
//	public void domainCoreNameIsCorrect(){
//			RaplCoreDomain core = new RaplCoreDomain(0,0);
//			String pathName = core.domainPath() + "/name";
//			String domainName = RaplDomain.openAndReadFile(pathName);
//			assertThat(domainName).isIn("",core.getDomainName());
//	}
//	
//	@Test
//	public void domainUncoreNameIsCorrect() throws FileNotFoundException,IOException{
//			RaplUncoreDomain uncore = new RaplUncoreDomain(0,1);
//			String pathName = uncore.domainPath()+ "/name";
//			String domainName = RaplDomain.openAndReadFile(pathName);
//			assertThat(domainName).isIn("",uncore.getDomainName());
//	}
//	
//	@Test
//	public void domainDramNameIsCorrect() throws FileNotFoundException,IOException{
//			RaplDramDomain dram = new RaplDramDomain(0,2);
//			String pathName = dram.domainPath() + "/name";
//			String domainName = RaplDomain.openAndReadFile(pathName);
//			assertThat(domainName).isIn("",dram.getDomainName());
//	}
	
//	@ParameterizedTest(name = "{2} energy consumed file exist")
//	@CsvSource({"0,/intel-rapl:,package","0,/intel-rapl:0/intel-rapl:0:,core","1,/intel-rapl:0/intel-rapl:0:,uncore","2,/intel-rapl:0/intel-rapl:0:,dram"})
//	public void domainConsomedEnergyFileExist(int socket,String pathEnd,String name) {
//		String path = RaplDomain.RAPL_PATH_NAME + pathEnd + socket+ "/energy_uj";
//		String energy = RaplDomain.openAndReadFile(path);
//		assertThat(RaplDomain.domainPathExist(path)).isTrue();
//	}
	
//	@Test
//	public void pkgDomainConsumedEnergyFileContentIsNumeric() {
//		assertThat(new RaplPackageDomain(0).getEneregyConsumed()).isGreaterThanOrEqualTo(0);
//	}
//	
//	@Test
//	public void coreDomainConsumedEnergyFileContentIsNumeric() {
//		assertThat(new RaplCoreDomain(0,0).getEneregyConsumed()).isGreaterThanOrEqualTo(0);
//	}
//	@Test
//	public void uncoreDomainConsumedEnergyFileContentIsNumeric() {
//		assertThat(new RaplUncoreDomain(0,1).getEneregyConsumed()).isGreaterThanOrEqualTo(0);
//	}
//	@Test
//	public void dramDomainConsumedEnergyFileContentIsNumeric() {
//		assertThat(new RaplDramDomain(0,2).getEneregyConsumed()).isGreaterThanOrEqualTo(0);
//	}
	
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
