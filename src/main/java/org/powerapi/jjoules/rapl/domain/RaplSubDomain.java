/**
 * 
 */
package org.powerapi.jjoules.rapl.domain;

/**
 * @author sanoussy
 *
 */
public abstract class RaplSubDomain extends RaplDomain {

	private int parentSocket;

	/**
	 * @param socket
	 */
	public RaplSubDomain(int subSocket, int parentSocket) {
		super(subSocket);
		this.parentSocket = parentSocket;
	}

	/**
	 * @return a sub socket (id of this domain)
	 */
	public int getParentSocket() {
		return this.parentSocket;
	}

	@Override
	public String domainPath() {
		return RAPL_PKG_PATH + this.getParentSocket() + "/intel-rapl:" + this.getParentSocket()
				+ ":" + this.getSocket();
	}

	@Override
	public double getDomainEnergy() {
		String pathName = this.domainPath() + "/energy_uj";
		String energyConsumed = RaplDomain.openAndReadFile(pathName);
		return Double.parseDouble(energyConsumed);
	}
}
