package jJoules.energyDevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jJoules.energyDevice.rapl.RaplDevice;
import jJoules.energyDomain.EnergyDomain;
import jJoules.exceptions.NoSuchEnergyDeviceException;

public class EnergyDeviceFactory {

	
	public ArrayList<EnergyDevice> genDevices(ArrayList<EnergyDomain> domains) {
		ArrayList<EnergyDevice> allDevices = new ArrayList<EnergyDevice>();
		Map<String, ArrayList<EnergyDomain>> allDomains;
		if (domains == null || domains.isEmpty())
			allDomains = this.genAllAvailbaleDomains();
		else
			allDomains = this.getAllDomainsWithTypes(domains);
		for (String deviceType: allDomains.keySet()) {
			try {
				Class<EnergyDevice> devices = (Class<EnergyDevice>) Class.forName(deviceType);
				EnergyDevice device;
				try {
					device = devices.newInstance();
					allDevices.add(device);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			if (deviceType == "RaplDomain")
				try {
					allDevices.add(new RaplDevice());
				} catch (NoSuchEnergyDeviceException e) {
					e.printStackTrace();
				}
		} return allDevices;
		
	}
	
	private Map<String, ArrayList<EnergyDomain>> genAllAvailbaleDomains(){
		ArrayList<EnergyDomain> availableDomains = new ArrayList<EnergyDomain>();
		ArrayList<EnergyDevice> availableDevices = new ArrayList<EnergyDevice>();
		try {
			availableDevices.add(new RaplDevice());
		} catch (NoSuchEnergyDeviceException e) {
			e.printStackTrace();
		}
		for(EnergyDevice device : availableDevices) {
			availableDomains.addAll(device.getAvailableDomains());
		}
		return getAllDomainsWithTypes(availableDomains);
	}
	
	private Map<String, ArrayList<EnergyDomain>> getAllDomainsWithTypes(ArrayList<EnergyDomain> domains){
		Map<String,ArrayList<EnergyDomain>> allDomains = new HashMap<String,ArrayList<EnergyDomain>>();
		for(EnergyDomain domain : domains) {
			String deviceName = domain.getDeviceType();
			if (allDomains.containsKey(deviceName))
				allDomains.get(deviceName).add(domain);
			else {
				ArrayList<EnergyDomain> newDomains = new ArrayList<EnergyDomain> ();
				newDomains.add(domain);
				allDomains.put(deviceName,newDomains);
			}
			
		}
		return allDomains;
	}
	public static void main(String[] args) {
		EnergyDeviceFactory devicesFactory = new EnergyDeviceFactory();
		ArrayList<EnergyDevice> devices = devicesFactory.genDevices(null);
		for(EnergyDevice d : devices) {
			System.out.println(d.getClass().getName());
		}
	}
}
