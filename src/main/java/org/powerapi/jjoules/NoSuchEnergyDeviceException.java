/**
 * 
 */
package org.powerapi.jjoules;

/**
 * @author sanoussy
 *
 */
public class NoSuchEnergyDeviceException extends JJoulesException {
	/**
	 * @param message
	 */
	public NoSuchEnergyDeviceException(String message) {
		super(message);
	}
	
	public NoSuchEnergyDeviceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NoSuchEnergyDeviceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
