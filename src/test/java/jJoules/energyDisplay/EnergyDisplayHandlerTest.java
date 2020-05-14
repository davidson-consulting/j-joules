/**
 * 
 */
package jJoules.energyDisplay;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import jJoules.energyDevice.EnergyDevice;
import jJoules.energyDisplay.util.MockEnergyDevice;
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
		EnergyDevice mockDevice = new MockEnergyDevice();
		this.printer = new EnergyPrinter();
		this.energyConsumedByDevice = printer.getEnergyConsumedByDevice(mockDevice);
	}
	
	@ParameterizedTest(name = "{1} enregy consumed is {0}")
	@CsvSource({"1000.0,package-0","100.0,core","59.0,uncore","400.0,dram"})
	public void displayItForEnergyPrinterTest(double expected, String name){
		assertThat(printer.getEnergyToPrint(energyConsumedByDevice, name)).isEqualTo(expected);
	}
	
}
