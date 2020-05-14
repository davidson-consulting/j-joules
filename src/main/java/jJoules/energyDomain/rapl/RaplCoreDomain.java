package jJoules.energyDomain.rapl;

public class RaplCoreDomain extends RaplDomain {

	
	public static String RAPL_CORE_PATH_NAME = RaplPackageDomain.RAPL_PKG_PATH_NAME + "0/intel-rapl:0:";
	
	public RaplCoreDomain(int socket) {
		super(socket);
	}

	@Override
	public String getDomainName() {
		return "core";
	}

	@Override
	public double getEneregyConsumed() {
		String pathName = RaplCoreDomain.RAPL_CORE_PATH_NAME+this.getSocket()+"/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}
	
	public String domainPath() {
		return RaplCoreDomain.RAPL_CORE_PATH_NAME;
	}
}
