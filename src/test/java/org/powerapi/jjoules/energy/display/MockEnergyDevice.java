package org.powerapi.jjoules.energy.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.powerapi.jjoules.DeviceNotConfiguredException;
import org.powerapi.jjoules.EnergyDevice;
import org.powerapi.jjoules.NoSuchEnergyDeviceException;
import org.powerapi.jjoules.domain.EnergyDomain;

public class MockEnergyDevice extends EnergyDevice{

	public MockEnergyDevice() throws NoSuchEnergyDeviceException {
		super();
	}

	@Override
	public ArrayList<EnergyDomain> availableDomains() throws NoSuchEnergyDeviceException {
		return null;
	}

	@Override
	public Map<String, Double> getEnergyConsumed() throws DeviceNotConfiguredException {
		Map<String, Double> energyConsumedByDevice = new HashMap<String,Double>();
		energyConsumedByDevice.put("package-0",1000.0);
		energyConsumedByDevice.put("core",100.0);
		energyConsumedByDevice.put("uncore",59.0);
		energyConsumedByDevice.put("dram",400.0);
		return energyConsumedByDevice;
		
	}
	
}