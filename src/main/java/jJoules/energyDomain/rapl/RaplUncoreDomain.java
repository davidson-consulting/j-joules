package jJoules.energyDomain.rapl;

public class RaplUncoreDomain extends RaplSubDomain {
		
	/**
	 * @param socket
	 * @param subSocket
	 */
	public RaplUncoreDomain(int socket,int subSocket) {
		super(socket,subSocket);
	}

	@Override
	public String getDomainName() {
		return "uncore";
	}

	@Override
	public double getEneregyConsumed() {
		String pathName = this.domainPath()+"/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}
}
