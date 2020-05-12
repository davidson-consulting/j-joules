/**
 * 
 */
package jJoules.rapl.domain;

/**
 * @author sanoussy
 *
 */
public class RaplCoreDomain extends RaplDomain {
	
	public static String RAPL_Core_PATH_NAME = "" ;//"/sys/devices/virtual/powercap/intel-rapl/intel-rapl:0";


	/**
	 * @param socket domain socket id
	 */
	public RaplCoreDomain(int socket) {
		super(socket);
	}
	
	public String getDomainName() {
		return "";
	}

}
