/**
 * 
 */
package org.powerapi.jjoules.domain;

import org.powerapi.jjoules.JJoulesException;

/**
 * @author sanoussy
 *
 */
public class NoSuchDomainException extends JJoulesException {
	private static final long serialVersionUID = 5374376192288288350L;

	public NoSuchDomainException(EnergyDomain domain) {
		super("No domain "+domain.getDomainName()+" available");
	}
}
