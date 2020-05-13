/**
 * 
 */
package jJoules.energyDomain.rapl;

import jJoules.energyDomain.rapl.RaplDomain;
import jJoules.energyDomain.rapl.RaplPackageDomain;

/**
 * @author sanoussy
 *
 */
public class RaplPackageDomainTest extends RaplDomainTest {
	
	public RaplDomain createDomain(int socket) {
		return new RaplPackageDomain(socket);
	}
}
