/**
 * 
 */
package jJoules.energyDisplay;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import jJoules.energyDevice.EnergyDevice;
import jJoules.energyDomain.EnergyDomain;
import jJoules.exceptions.DeviceNotConfiguredException;
import jJoules.exceptions.NoSuchEnergyDeviceException;

/**
 * @author sanoussy
 *
 */
class EnergyDisplayHandlerTest {
	
	private Map<String, Double> energyConsumedByDevice;
	EnergyPrinter printer;
	@BeforeEach
	public void init() throws NoSuchEnergyDeviceException {
		EnergyDevice mokeDevice = new MockEnergyDevice();
		this.printer = new EnergyPrinter();
		this.energyConsumedByDevice = printer.getEnergyConsumedByDevice(mokeDevice);
	}
	
	@ParameterizedTest(name = "{1} enregy consumed is {0}")
	@CsvSource({"1000.0,package-0","100.0,core","59.0,uncore","400.0,dram"})
	public void displayItForEnergyPrinterTest(double expected, String name){
		assertThat(printer.getEnergyToPrint(energyConsumedByDevice, name)).isEqualTo(expected);
	}
	
	class MockEnergyDevice extends EnergyDevice{

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
}
