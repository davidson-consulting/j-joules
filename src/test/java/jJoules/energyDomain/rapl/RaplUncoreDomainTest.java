/**
 * 
 */
package jJoules.energyDomain.rapl;

/**
 * @author sanoussy
 *
 */
public class RaplUncoreDomainTest extends RaplDomainTest {
	public RaplDomain createDomain(int socket) {
		return new RaplUncoreDomain(socket);
	}
}
