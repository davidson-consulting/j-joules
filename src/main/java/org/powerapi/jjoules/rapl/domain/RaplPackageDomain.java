/**
 * 
 */
package org.powerapi.jjoules.rapl.domain;

/**
 * @author sanoussy
 *
 */
public class RaplPackageDomain extends RaplDomain {

	private static final String PACKAGE = "package";

	public RaplPackageDomain(int socket) {
		super(socket);
	}

	@Override
	public String getDomainName() {
		return PACKAGE;
	}

	@Override
	public String domainPath() {
		return RAPL_PKG_PATH + this.getSocket();
	}

	@Override
	public double getDomainEnergy() {
		String pathName = RAPL_PKG_PATH + this.getSocket() + "/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}

}
