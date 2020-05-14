package jJoules.energyDomain.rapl;

public class RaplUncoreDomain extends RaplDomain {
	
	public static String RAPL_UNCORE_PATH_NAME = RaplPackageDomain.RAPL_PKG_PATH_NAME + "0/intel-rapl:0:";

	public RaplUncoreDomain(int socket) {
		super(socket);
	}

	@Override
	public String getDomainName() {
		return "uncore";
	}

	@Override
	public double getEneregyConsumed() {
		String pathName = RaplUncoreDomain.RAPL_UNCORE_PATH_NAME+this.getSocket()+"/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}
	
	public String domainPath() {
		return RaplUncoreDomain.RAPL_UNCORE_PATH_NAME;
	}

}
