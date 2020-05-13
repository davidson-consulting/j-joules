/**
 * 
 */
package jJoules.rapl.domain;

/**
 * @author sanoussy
 *
 */
public class RaplPackageDomain extends RaplDomain {
	
	public static String RAPL_PKG_PATH_NAME = RaplDomain.RAPL_PATH_NAME+"/intel-rapl:";

	/**
	 * 
	 */
	public RaplPackageDomain(int socket) {
		super(socket);
	}
	
	/**
	 * @return domain name
	 */
	public String getDomainName() {
		return "package-0";
	}

}
