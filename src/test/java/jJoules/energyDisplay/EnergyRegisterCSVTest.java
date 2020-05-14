/**
 * 
 */
package jJoules.energyDisplay;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jJoules.energyDisplay.util.MockEnergyDevice;
import jJoules.exceptions.NoSuchEnergyDeviceException;

/**
 * @author sanoussy
 *
 */
class EnergyRegisterCSVTest {
	
	private MockEnergyDevice mockDevice;
	private Map<String, Double> energyConsumedByDevice;
	private EnergyRegisterCSV registerCsv;
	
	
	@BeforeEach
	public void init() {
		this.registerCsv = new EnergyRegisterCSV("out.csv");
		try {
			 this.mockDevice = new MockEnergyDevice();
			 this.energyConsumedByDevice = registerCsv.getEnergyConsumedByDevice(this.mockDevice);
		} catch (NoSuchEnergyDeviceException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void displayItPoduceFileWithCorrecteLines() {
		registerCsv.displayIt(energyConsumedByDevice);
		File file = new File(registerCsv.getFileName());
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			for(int i=0; i<energyConsumedByDevice.size();i++) {
				assertThat(br.readLine()).isIn("id;tag;energyConsumed","1;package-0;1000.0","2;core;100.0","3;dram;400.0","4;uncore;59.0");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
