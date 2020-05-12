/**
 * 
 */
package jJoules.rapl.domain;

/**
 * @author sanoussy
 *
 */
public class RaplDomain {
	
	public static String RAPL_PATH_NAME = "/sys/devices/virtual/powercap/intel-rapl";
	
	private int socket;

	/**
	 * 
	 */
	public RaplDomain(int socket) {
	}
	
	/**
	 * @return domain name
	 */
	public String getDomainName() {
		return "";
	}
	
	public String toString() {
		return "";
	}
	
	public boolean equals(Object o) {
		return false;
	}
	
	
}
