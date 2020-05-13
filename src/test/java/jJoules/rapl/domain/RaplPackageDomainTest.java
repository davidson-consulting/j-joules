/**
 * 
 */
package jJoules.rapl.domain;

/**
 * @author sanoussy
 *
 */
public class RaplPackageDomainTest extends RaplDomainTest {
	
	public RaplDomain createDomain(int socket) {
		return new RaplPackageDomain(socket);
	}
}
