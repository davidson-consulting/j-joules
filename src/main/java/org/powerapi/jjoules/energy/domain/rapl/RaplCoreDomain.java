package org.powerapi.jjoules.energy.domain.rapl;

public class RaplCoreDomain extends RaplSubDomain {
	
	/**
	 * @param socket
	 * @param subSocket
	 */
	public RaplCoreDomain(int socket,int subSocket) {
		super(socket,subSocket);
	}

	@Override
	public String getDomainName(){
		return "core";
	}

	@Override
	public double getEneregyConsumed(){
		String pathName = this.domainPath()+"/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}
	
}
