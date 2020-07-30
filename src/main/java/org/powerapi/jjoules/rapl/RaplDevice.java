/**
 * 
 */
package org.powerapi.jjoules.rapl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.powerapi.jjoules.EnergyDevice;
import org.powerapi.jjoules.EnergyDomain;
import org.powerapi.jjoules.NoSuchDomainException;

/**
 * Implementation of the Intel RAPL energy device with the associated domains.
 *
 */
public class RaplDevice extends EnergyDevice {
	protected static final String RAPL_DIR = "/intel-rapl";
	private static String BASE_RAPL_PATH = "/sys/devices/virtual/powercap" + RAPL_DIR;

	public static final RaplDevice RAPL = new RaplDevice();
	private final RaplDomain rootDomain;

	/**
	 * @throws NoSuchEnergyDeviceException
	 * 
	 */
	private RaplDevice() {
		this.rootDomain =  new RaplDomain(BASE_RAPL_PATH);
	}

	@Override
	public Collection<EnergyDomain> listAvailableDomains() {
		ArrayList<EnergyDomain> availableDomains = new ArrayList<EnergyDomain>();
		short socket = 0;
		while (true) {
			try {
				RaplDomain domain = RaplDomain.createDomain(domainPath(socket));
				availableDomains.add(domain);
				availableDomains.addAll(listAvailableSubDomain(socket, domain));
				socket++;
			} catch (NoSuchDomainException e) {
				break;
			}
		}
		return availableDomains;
	}

	private static final String domainPath(int socket) {
		return BASE_RAPL_PATH + RAPL_DIR + ":" + socket;
	}

	private Collection<EnergyDomain> listAvailableSubDomain(short socket, RaplDomain domain) {
		ArrayList<EnergyDomain> availableDomains = new ArrayList<EnergyDomain>();
		short subsocket = 0;
		while (true) {
			try {
				RaplDomain subDomain = domain.createSubDomain(subDomainPath(socket, subsocket));
				availableDomains.add(subDomain);
				subsocket++;
			} catch (NoSuchDomainException e) {
				break;
			}
		}
		return availableDomains;
	}

	private static final String subDomainPath(int socket, int subsocket) {
		return domainPath(socket) + RAPL_DIR + ":" + socket + ":" + subsocket;
	}

	protected Map<String, Long> getDomainCounters() {
		Map<String, Long> counters = new HashMap<String, Long>();
		for (EnergyDomain domain : this.listSelectedDomains())
			counters.put(domain.getDomainName(), domain.getDomainCounter());
		return counters;
	}

	protected Map<String, Long> getMaxDomainCounters() {
		Map<String, Long> maxCounters = new HashMap<String, Long>();
		for (EnergyDomain domain : this.listSelectedDomains())
			maxCounters.put(domain.getDomainName(), domain.getMaxDomainCounter());
		return maxCounters;
	}

	public boolean isDeviceAvailable() {
		return this.rootDomain.isDomainAvailable();
	}

	public boolean isDeviceEnabled() {
		return this.rootDomain.isDomainEnabled();
	}
}
