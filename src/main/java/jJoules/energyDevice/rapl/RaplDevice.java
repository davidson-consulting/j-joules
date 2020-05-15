/**
 * 
 */
package jJoules.energyDevice.rapl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	/**
	 * @return all available package on the device 
	 */
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
	
    /**
     * @return an integer which is assumed to be the number of package on the device
     */
    private static int getSocketIds() {
		int socketId = 0;
		while (true) {
			String pathName = RaplDomain.RAPL_PATH_NAME+"/intel-rapl:" + socketId;
			if (new File(pathName).exists()) 
				socketId += 1;
			else return socketId;
		}
	}
    
    /**
     * @return list of all available core domains on the device
     */
    public static ArrayList<EnergyDomain> availableCoreDomains() {
    	ArrayList<EnergyDomain> coreDomains = new ArrayList<EnergyDomain>();
    	int ids = getSocketIds();
    	for(int id=0; id <ids; id++) {
    		String domainNameFilePath = RaplDomain.RAPL_PATH_NAME+ "/intel-rapl:" + id+"/name";
    		if (new File(domainNameFilePath).exists()) {
    			coreDomains.add(new RaplCoreDomain(id,0));
    		}
    	}return coreDomains;
	}

	/**
	 * @return list of all available uncore domains on the device
	 */
	public static ArrayList<EnergyDomain> availableUncoreDomains() {
		ArrayList<EnergyDomain> uncoreDomains = new ArrayList<EnergyDomain>();
		ArrayList<Integer> subDomainsList = getPkgIds("uncore",1);
		for(Integer i : subDomainsList) {
			uncoreDomains.add(new RaplUncoreDomain(i,1));
		}return uncoreDomains;
	}
	
	/**
	 * @return list of all available dram domains on the device 
	 */
	private static ArrayList<EnergyDomain> availableDramDomains() {
		ArrayList<EnergyDomain> dramDomains = new ArrayList<EnergyDomain>();
		ArrayList<Integer> subDomainsList = getPkgIds("dram",2);
		for(Integer i : subDomainsList) {
			dramDomains.add(new RaplDramDomain(i,2));
		}return dramDomains;
	}
	
	/**
	 * @param domainName the domain name to check existence
	 * @param subDomainId the subDomain id {core, uncore or dram}
	 * @return list of integer those represent a socket of device
	 */
	private static ArrayList<Integer> getPkgIds(String domainName,int subDomainId) {
		ArrayList<Integer> pkgIdList = new ArrayList<Integer>();
		int ids = getSocketIds();
		for(int id=0; id<ids;id++) {
			String domainFilePath = RaplDomain.RAPL_PATH_NAME+ "/intel-rapl:" + id;
			if(new File(domainFilePath+"/intel-rapl:"+id+":"+subDomainId).exists()) {
				pkgIdList.add(id);
			}	
		}return pkgIdList;
	}
	
	@Override
	public Map<String,Double> getEnergyConsumed() throws DeviceNotConfiguredException {
		Map<String,Double> energyConsumed = new HashMap<String,Double>();
		for(EnergyDomain domain : this.getConfiguredDomains()) {
			energyConsumed.put(domain.getDomainName(),domain.getEneregyConsumed());
		}
		return energyConsumed;
	}

}
