/**
 * 
 */
package org.powerapi.jjoules.energy.domain;

import org.powerapi.jjoules.energy.device.JJoulesException;

/**
 * @author sanoussy
 *
 */
public class NoSuchDomainException extends JJoulesException {

	/**
	 * 
	 */
	public NoSuchDomainException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public NoSuchDomainException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public NoSuchDomainException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NoSuchDomainException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
