/**
 * 
 */
package jJoules.energyDevice.rapl;

import java.io.File;
import java.util.ArrayList;

import jJoules.energyDevice.EnergyDevice;
import jJoules.energyDomain.EnergyDomain;
import jJoules.energyDomain.rapl.RaplDomain;
import jJoules.energyDomain.rapl.RaplPackageDomain;
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
		return availDomains;
	}
	
	public static ArrayList<EnergyDomain> availablePKGDomains() {
		ArrayList<EnergyDomain> pkgDomain = new ArrayList<EnergyDomain>();
		for(int id=0; id < getSocketIds();id++ ) {
			String domainNameFilePath = RaplDomain.RAPL_PATH_NAME+ "/intel-rapl:" + id+"/name";
			if (new File(domainNameFilePath).exists()) {
				String domainName = RaplDomain.openAndReadFile(domainNameFilePath);
				if(domainName.equalsIgnoreCase("package-"+id))
					pkgDomain.add(new RaplPackageDomain(id));
			}
		}return pkgDomain;
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


//	private static ArrayList<EnergyDomain> availableCoreDomains() {
//		return null;
//	}
//	private static ArrayList<EnergyDomain> availableUncoreDomains() {
//		return null;
//	}
//	private static ArrayList<EnergyDomain> availableDramDomains() {
//		return null;
//	}


//	public int[] getDomainOnSocket(int domainSocket,String domainName){
//		int domainId = 0;
//		while (true) {
//			String domainPath = RaplDomain.RAPL_PATH_NAME + "/intel-rapl:"+domainId+"/name";
//			if (RaplDomain.openAndReadFile(domainPath).equals(domainName)) {
//				
//			}
//		}
//}
	
	@Override
	public ArrayList<Double> getEnergyConsumed() throws DeviceNotConfiguredException {
		ArrayList<Double> energyConsumed = new ArrayList<Double>();
		for(EnergyDomain domain : this.getConfiguredDomains()) {
			energyConsumed.add(domain.getEneregyConsumed());
		}
		return energyConsumed;
	}
	

}
