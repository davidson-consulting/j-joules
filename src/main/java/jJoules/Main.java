package jJoules;

import jJoules.energyDevice.EnergyDevice;
import jJoules.energyDevice.rapl.RaplDevice;
import jJoules.energyDomain.EnergyDomain;

public class Main {

	public static void main(String[] args) {
		try {
			EnergyDevice device = new RaplDevice();
			device.configure(device.getAvailableDomains());
			System.out.println("All available domains => "+device.getAvailableDomains());
			System.out.println("All configured domains => "+device.getConfiguredDomains());

			System.out.println("\n---- Energy consumed in the device -------");
			for(EnergyDomain domain : device.getAvailableDomains()) {
				
				System.out.println("\n++ Energy consumed in "+domain+" ++");
				EnergyMesureIt mesureIt = new EnergyMesureIt(domain);
				System.out.println("Energy consumed before => "+mesureIt.getEnergyBefore());
				mesureIt.begin();
				
				for(int i=0;i<10000; i++) {}
				
				double diff = mesureIt.end();
				System.out.println("Energy consumed after => "+ mesureIt.getEnergyAfter());
				System.out.println("diff => "+diff+"\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
