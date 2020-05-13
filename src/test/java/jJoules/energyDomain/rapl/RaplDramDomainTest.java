/**
 * 
 */
package jJoules.energyDomain.rapl;

/**
 * @author sanoussy
 *
 */
public class RaplDramDomainTest extends RaplDomainTest {
	public RaplDomain createDomain(int socket) {
		return new RaplDramDomain(socket);
	}
}
