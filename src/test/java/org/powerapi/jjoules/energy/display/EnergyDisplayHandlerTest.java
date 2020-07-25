/**
 * 
 */
package org.powerapi.jjoules.energy.display;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.powerapi.jjoules.energy.device.EnergyDevice;
import org.powerapi.jjoules.energy.device.NoSuchEnergyDeviceException;
import org.powerapi.jjoules.energy.display.utils.MockEnergyDevice;
import org.powerapi.jjoules.energy.display.utils.Result;

/**
 * @author sanoussy
 *
 */
class EnergyDisplayHandlerTest {
	
	private Map<String, Result> energyConsumedByDevice;
	EnergyPrinter printer;
	@BeforeEach
	public void init() throws NoSuchEnergyDeviceException {
		this.energyConsumedByDevice = new HashMap<String,Result>();
		EnergyDevice mockDevice = new MockEnergyDevice();
		this.printer = EnergyPrinter.ENERGY_PRINTER;
		Map<String, Double> res = printer.getEnergyConsumedByDevice(mockDevice);
		for(String deviceName : res.keySet()){
			this.energyConsumedByDevice.put(deviceName, new Result(res.get(deviceName),0));
		}
	}
	
	@ParameterizedTest(name = "{1} enregy consumed is {0}")
	@CsvSource({"1000.0,package-0","100.0,core","59.0,uncore","400.0,dram"})
	public void displayItForEnergyPrinterTest(double expected, String name){
		assertThat(printer.getEnergyToPrint(energyConsumedByDevice, name)).isEqualTo(expected);
	}
	
}
