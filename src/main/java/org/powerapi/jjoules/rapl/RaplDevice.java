/**
 * 
 */
package org.powerapi.jjoules.rapl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.powerapi.jjoules.DeviceNotConfiguredException;
import org.powerapi.jjoules.EnergyDevice;
import org.powerapi.jjoules.NoSuchEnergyDeviceException;
import org.powerapi.jjoules.domain.EnergyDomain;
import org.powerapi.jjoules.rapl.domain.RaplCoreDomain;
import org.powerapi.jjoules.rapl.domain.RaplDomain;
import org.powerapi.jjoules.rapl.domain.RaplDramDomain;
import org.powerapi.jjoules.rapl.domain.RaplPackageDomain;
import org.powerapi.jjoules.rapl.domain.RaplUncoreDomain;

/**
 * @author sanoussy
 *
 */
public class RaplDevice extends EnergyDevice {

	/**
	 * @throws NoSuchEnergyDeviceException
	 * 
	 */
	public RaplDevice() throws NoSuchEnergyDeviceException {
		super();
	}

	@Override
	public ArrayList<EnergyDomain> availableDomains() throws NoSuchEnergyDeviceException {
		RaplDomain.check();
		ArrayList<EnergyDomain> availDomains = new ArrayList<EnergyDomain>();

		availDomains.addAll(availablePKGDomains());
		availDomains.addAll(availableCoreDomains());
		availDomains.addAll(availableUncoreDomains());
		availDomains.addAll(availableDramDomains());
		return availDomains;
	}

	/**
	 * @return all available package on the device
	 */
	public static ArrayList<EnergyDomain> availablePKGDomains() {
		ArrayList<EnergyDomain> pkgDomains = new ArrayList<EnergyDomain>();
		int ids = getSocketIds();
		for (int id = 0; id < ids; id++) {
			String domainNameFilePath = RaplDomain.domainPath(id) + "/name";
			if (new File(domainNameFilePath).exists()) {
				String domainName = RaplDomain.openAndReadFile(domainNameFilePath);
				if (domainName.equals("package-" + id))
					pkgDomains.add(new RaplPackageDomain(id));
			}
		}
		return pkgDomains;
	}

	/**
	 * @return an integer which is assumed to be the number of package on the device
	 */
	private static int getSocketIds() {
		int socketId = 0;
		while (true) {
			String pathName = RaplDomain.domainPath(socketId);
			if (new File(pathName).exists())
				socketId += 1;
			else
				return socketId;
		}
	}

	/**
	 * @return list of all available core domains on the device
	 */
	public static ArrayList<EnergyDomain> availableCoreDomains() {
		ArrayList<EnergyDomain> coreDomains = new ArrayList<EnergyDomain>();
		Map<Integer, Integer> subDomains = getDomainsIds(RaplCoreDomain.CORE);
		for (Integer i : subDomains.keySet()) {
			coreDomains.add(new RaplCoreDomain(subDomains.get(i), i));
		}
		return coreDomains;
	}

	/**
	 * @return list of all available uncore domains on the device
	 */
	public static ArrayList<EnergyDomain> availableUncoreDomains() {
		ArrayList<EnergyDomain> uncoreDomains = new ArrayList<EnergyDomain>();
		Map<Integer, Integer> subDomains = getDomainsIds(RaplUncoreDomain.UNCORE);
		for (Integer i : subDomains.keySet()) {
			uncoreDomains.add(new RaplUncoreDomain(subDomains.get(i), i));
		}
		return uncoreDomains;
	}

	/**
	 * @return list of all available dram domains on the device
	 */
	private static ArrayList<EnergyDomain> availableDramDomains() {
		ArrayList<EnergyDomain> dramDomains = new ArrayList<EnergyDomain>();
		Map<Integer, Integer> subDomains = getDomainsIds(RaplDramDomain.DRAM);
		for (Integer i : subDomains.keySet()) {
			dramDomains.add(new RaplDramDomain(subDomains.get(i), i));
		}
		return dramDomains;
	}

	/**
	 * @param domainName  the domain name to check existence
	 * @param subDomainId the subDomain id {core, uncore or dram}
	 * @return list of integer those represent a socket of device
	 */
	private static Map<Integer, Integer> getDomainsIds(String domainName) {
		Map<Integer, Integer> domainsIds = new HashMap<Integer, Integer>();
		int ids = getSocketIds();
		for (int id = 0; id < ids; id++) {
			String domainFilePath = RaplDomain.domainPath(id);
			if (new File(domainFilePath).exists()) {
				boolean isSubDomain = false;
				int subId = 0;
				while (!isSubDomain) {
					String subDomainFilePath = domainFilePath + "/intel-rapl:" + id + ":" + subId + "/name";
					if (new File(subDomainFilePath).exists()) {
						String name = RaplDomain.openAndReadFile(subDomainFilePath);
						if (name.equals(domainName)) {
							domainsIds.put(id, subId);
							isSubDomain = true;
						} else {
							subId++;
						}

					} else {
						isSubDomain = true;
					}
				}
			}
		}
		return domainsIds;
	}

	@Override
	public Map<String, Double> getEnergyConsumed() throws DeviceNotConfiguredException {
		Map<String, Double> energyConsumed = new HashMap<String, Double>();
		for (EnergyDomain domain : this.getConfiguredDomains()) {
			energyConsumed.put(domain.getDomainName(), domain.getDomainEnergy());
		}
		return energyConsumed;
	}

}
