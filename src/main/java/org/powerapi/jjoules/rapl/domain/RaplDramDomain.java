/**
 * 
 */
package org.powerapi.jjoules.rapl.domain;

/**
 * @author sanoussy
 *
 */
public class RaplDramDomain extends RaplSubDomain {

	public static final String DRAM = "dram";

	/**
	 * @param socket
	 * @param subSocket
	 */
	public RaplDramDomain(int socket, int subSocket) {
		super(socket, subSocket);
	}

	@Override
	public String getDomainName() {
		return DRAM;
	}
}
