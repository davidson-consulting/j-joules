/**
 * 
 */
package jJoules.energyDomain.rapl;

/**
 * @author sanoussy
 *
 */
public class RaplPackageDomain extends RaplDomain {
	
	public static String RAPL_PKG_PATH_NAME = RaplDomain.RAPL_PATH_NAME+"/intel-rapl:";

	public RaplPackageDomain(int socket) {
		super(socket);
	}
	
	@Override
	public String getDomainName() {
		return "package";
	}
	
	@Override
	public String domainPath() {
		return RaplDomain.RAPL_PATH_NAME+"/intel-rapl:"+this.getSocket();
	}

	@Override
	public double getEneregyConsumed() {
		String pathName = RAPL_PKG_PATH_NAME+this.getSocket()+"/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}


}
