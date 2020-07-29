/**
 * 
 */
package org.powerapi.jjoules.display;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powerapi.jjoules.NoSuchEnergyDeviceException;
import org.powerapi.jjoules.display.EnergyRegisterCSV;
import org.powerapi.jjoules.display.EnergySample;
import org.powerapi.jjoules.energy.display.MockEnergyDevice;


/**
 * @author sanoussy
 *
 */
class EnergyRegisterCSVTest {
	
	private MockEnergyDevice mockDevice;
	private Map<String, EnergySample> energyConsumedByDevice;
	private EnergyRegisterCSV registerCsv;
	
	
	@BeforeEach
	public void init() {
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.setFileName("out.csv");
		try {
			this.energyConsumedByDevice = new HashMap<String,EnergySample>();
			this.mockDevice = new MockEnergyDevice();
			Map<String, Double> res = EnergyRegisterCSV.ENERGY_REGISTER_CSV.getEnergyConsumedByDevice(this.mockDevice);
			for(String deviceName : res.keySet()){
				this.energyConsumedByDevice.put(deviceName, new EnergySample(res.get(deviceName),0));
			}
		} catch (NoSuchEnergyDeviceException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void displayItPoduceFileWithCorrecteLines() {
		EnergyRegisterCSV.CURRENT_CLASS_NAME = "test";
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.saveResultOfClass(energyConsumedByDevice, EnergyRegisterCSV.CURRENT_CLASS_NAME, EnergyRegisterCSV.ALL_DATA);
		//EnergyDisplayHandler.initDir();
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.setFileName("out.csv");
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.displayIt();
		
		File file = new File(EnergyRegisterCSV.ENERGY_REGISTER_CSV.getFileName());
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			for(int i=0; i<energyConsumedByDevice.size();i++) {
				assertThat(br.readLine()).isIn("id;tag;classTest;energyConsumed;duration","10;package-0;test;1000.0;0","11;core;test;100.0;0","12;dram;test;400.0;0","13;uncore;test;59.0;0");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			file.deleteOnExit();
		}
	}

}
