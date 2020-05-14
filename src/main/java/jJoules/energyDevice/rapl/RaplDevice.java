/**
 * 
 */
package jJoules.energyDevice.rapl;

import java.io.File;
import java.util.ArrayList;

import jJoules.energyDevice.EnergyDevice;
import jJoules.energyDomain.EnergyDomain;
import jJoules.energyDomain.rapl.RaplCoreDomain;
import jJoules.energyDomain.rapl.RaplDomain;
import jJoules.energyDomain.rapl.RaplDramDomain;
import jJoules.energyDomain.rapl.RaplPackageDomain;
import jJoules.energyDomain.rapl.RaplUncoreDomain;
import jJoules.exceptions.DeviceNotConfiguredException;
import jJoules.exceptions.NoSuchEnergyDeviceException;

/**
 * @author sanoussy
 *
 */
public class RaplDevice extends EnergyDevice{

	/**
	 * @throws NoSuchEnergyDeviceException 
	 * 
	 */
	public RaplDevice() throws NoSuchEnergyDeviceException {
		super();
	}
	
	
	@Override
	public ArrayList<EnergyDomain> availableDomains() throws NoSuchEnergyDeviceException {
		if(! new File(RaplDomain.RAPL_PATH_NAME).exists()) 
			throw new NoSuchEnergyDeviceException();
		ArrayList<EnergyDomain> availDomains = new ArrayList<EnergyDomain>();
		
		availDomains.addAll(availablePKGDomains());
		availDomains.addAll(availableCoreDomains());
		availDomains.addAll(availableUncoreDomains());
		availDomains.addAll(availableDramDomains());
		return availDomains;
	}
	
	public static ArrayList<EnergyDomain> availablePKGDomains() {
		ArrayList<EnergyDomain> pkgDomains = new ArrayList<EnergyDomain>();
		int ids = getSocketIds();
		for(int id=0; id < ids ;id++ ) {
			String domainNameFilePath = RaplDomain.RAPL_PATH_NAME+ "/intel-rapl:" + id+"/name";
			if (new File(domainNameFilePath).exists()) {
				String domainName = RaplDomain.openAndReadFile(domainNameFilePath);
				if(domainName.equals("package-"+id))
					pkgDomains.add(new RaplPackageDomain(id));
			}
		}return pkgDomains;
	}
	
    private static int getSocketIds() {
		int socketId = 0;
		while (true) {
			String pathName = RaplDomain.RAPL_PATH_NAME+"/intel-rapl:" + socketId;
			if (new File(pathName).exists()) 
				socketId += 1;
			else return socketId;
		}
	}
    public static ArrayList<EnergyDomain> availableCoreDomains() {
    	ArrayList<EnergyDomain> coreDomains = new ArrayList<EnergyDomain>();
    	int ids = getSocketIds();
    	for(int id=0; id <ids; id++) {
    		String domainNameFilePath = RaplDomain.RAPL_PATH_NAME+ "/intel-rapl:" + id+"/name";
    		if (new File(domainNameFilePath).exists()) {
    			coreDomains.add(new RaplCoreDomain(id));
    		}
    	}return coreDomains;
	}

	public static ArrayList<EnergyDomain> availableUncoreDomains() {
		ArrayList<EnergyDomain> uncoreDomains = new ArrayList<EnergyDomain>();
		ArrayList<Integer> subDomainsList = getSubDomainsIds("uncore");
		for(Integer i : subDomainsList) {
			uncoreDomains.add(new RaplUncoreDomain(i));
		}return uncoreDomains;
	}
	
	private static ArrayList<EnergyDomain> availableDramDomains() {
		ArrayList<EnergyDomain> dramDomains = new ArrayList<EnergyDomain>();
		ArrayList<Integer> subDomainsList = getSubDomainsIds("dram");
		for(Integer i : subDomainsList) {
			dramDomains.add(new RaplDramDomain(i));
		}return dramDomains;
	}
	
	private static ArrayList<Integer> getSubDomainsIds(String domainName) {
		ArrayList<Integer> subDomainsList = new ArrayList<Integer>();
		int ids = getSocketIds();
		for(int id=0; id<ids;id++) {
			String domainFilePath = RaplDomain.RAPL_PATH_NAME+ "/intel-rapl:" + id;
			boolean isNewPackage = true;
			int subDomainsIds = 1;
			while(isNewPackage) {
				if(new File(domainFilePath+"/intel-rapl:"+id+":"+subDomainsIds).exists()) {
					String name = RaplDomain.openAndReadFile(domainFilePath+"/intel-rapl:"+id+":"+subDomainsIds+"/name");
					if(name == domainName)
						subDomainsIds += 1;
					else {
						isNewPackage = false;
						subDomainsList.add(subDomainsIds);
					}
				}else {
					isNewPackage = false;
					subDomainsList.add(subDomainsIds);
				}
			}
		}return subDomainsList;
	}
	
	@Override
	public ArrayList<Double> getEnergyConsumed() throws DeviceNotConfiguredException {
		ArrayList<Double> energyConsumed = new ArrayList<Double>();
		for(EnergyDomain domain : this.getConfiguredDomains()) {
			energyConsumed.add(domain.getEneregyConsumed());
		}
		return energyConsumed;
	}

}
