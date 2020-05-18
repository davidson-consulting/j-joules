/**
 * 
 */
package jJoules.energyDomain.nvidia;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author sanoussy
 *
 */
class NvidiaGPUDomainTest {
	
//	private NvidiaGPUDomain nvidiaGPUDomain;
//	
//	@BeforeEach
//	public void init() {
//		this.nvidiaGPUDomain  = new NvidiaGPUDomain(0);
//	}
	
	@ParameterizedTest(name = "{0} => nvidia_gpu_{0}")
	@ValueSource(ints = {0,1,2})
	public void nvidiaRepresentationCorrectTest(int arg) {
		NvidiaGPUDomain nvidiaGPUDomain = new NvidiaGPUDomain(arg);
		assertThat(nvidiaGPUDomain.toString()).isEqualTo("nvidia_gpu_"+arg);
	}
	
	@ParameterizedTest(name = "{0} => NvidiaGPUDevice")
	@ValueSource(ints = {0,1,2})
	public void getDeviceTypeReturnNvidiaGPUDevice(int arg) {
		NvidiaGPUDomain nvidiaGPUDomain = new NvidiaGPUDomain(arg);
		assertThat(nvidiaGPUDomain.getDeviceType()).isEqualTo("NvidiaGPUDevice");
	}
	@ParameterizedTest(name = "{0} => Is positive number")
	@ValueSource(ints = {0,1,2})
	public void getEnergyConsumedGivePositiveNomber(int arg) {
		NvidiaGPUDomain nvidiaGPUDomain = new NvidiaGPUDomain(arg);
		assertThat(nvidiaGPUDomain.getEneregyConsumed()).isGreaterThanOrEqualTo(0);
	}
	
}
