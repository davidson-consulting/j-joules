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
		this.energyConsumedByDevice = registerCsv.getEnergyConsumedByDevice(this.mockDevice);
		try {
			 this.mockDevice = new MockEnergyDevice();
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
			assertThat("id;tag;energyConsumed").isEqualTo(br.readLine());
			assertThat("1;package-0;1000.0").isEqualTo(br.readLine());
			assertThat("1;core;100.0").isEqualTo(br.readLine());
			assertThat("1;uncore;59.0").isEqualTo(br.readLine());
			assertThat("1;dram;400.0").isEqualTo(br.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
