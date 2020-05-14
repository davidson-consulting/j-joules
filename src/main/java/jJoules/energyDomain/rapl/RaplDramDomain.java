/**
 * 
 */
package jJoules.energyDomain.rapl;

/**
 * @author sanoussy
 *
 */
public class RaplDramDomain extends RaplDomain {
	
	public static String RAPL_DRAM_PATH_NAME = RaplPackageDomain.RAPL_PKG_PATH_NAME + "0/intel-rapl:0:";

	/**
	 * @param socket
	 */
	public RaplDramDomain(int socket) {
		super(socket);
	}

	@Override
	public String getDomainName() {
		return "dram";
	}

	@Override
	public double getEneregyConsumed() {
		String pathName = RaplDramDomain.RAPL_DRAM_PATH_NAME+this.getSocket()+"/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}
	
	public String domainPath() {
		return RaplDramDomain.RAPL_DRAM_PATH_NAME;
	}

}
