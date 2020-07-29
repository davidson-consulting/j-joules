package org.powerapi.jjoules.rapl.domain;

public class RaplCoreDomain extends RaplSubDomain {

	public static final String CORE = "core";

	/**
	 * @param socket
	 * @param subSocket
	 */
	public RaplCoreDomain(int socket, int subSocket) {
		super(socket, subSocket);
	}

	@Override
	public String getDomainName() {
		return CORE;
	}
}
