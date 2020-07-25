/**
 * 
 */
package org.powerapi.jjoules.energy.domain.rapl;

/**
 * @author sanoussy
 *
 */
public class RaplDramDomain extends RaplSubDomain {
	
	/**
	 * @param socket
	 * @param subSocket
	 */
	public RaplDramDomain(int socket,int subSocket) {
		super(socket,subSocket);
	}

	@Override
	public String getDomainName() {
		return "dram";
	}

	@Override
	public double getEneregyConsumed() {
		String pathName = this.domainPath()+"/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}

}
