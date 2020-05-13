/**
 * 
 */
package jJoules.energyDomain.rapl;

/**
 * @author sanoussy
 *
 */
public class RaplCoreDomainTest extends RaplDomainTest {

	public RaplDomain createDomain(int socket) {
		return new RaplCoreDomain(socket);
	}
}
