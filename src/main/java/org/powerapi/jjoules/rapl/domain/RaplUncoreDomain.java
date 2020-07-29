package org.powerapi.jjoules.rapl.domain;

public class RaplUncoreDomain extends RaplSubDomain {

	public static final String UNCORE = "uncore";

	/**
	 * @param socket
	 * @param subSocket
	 */
	public RaplUncoreDomain(int socket, int subSocket) {
		super(socket, subSocket);
	}

	@Override
	public String getDomainName() {
		return UNCORE;
	}
}
