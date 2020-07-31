/**
 * 
 */
package org.powerapi.jjoules;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * class for sampling the energy of a code snippet
 * 
 */
public class EnergySample {
	public static final String DEVICE = "device";
	public static final String POWER = "power";
	public static final String DURATION = "duration";

	private final EnergyDevice device;
	private final Map<EnergyDomain, Long> maxCounters;
	private final Map<EnergyDomain, Long> initialCounters;
	private final long initialTimestamp = timestamp();

	public EnergySample(EnergyDevice device) {
		this.device = device;
		this.maxCounters = device.getMaxDomainCounters();
		this.initialCounters = device.getDomainCounters();
	}

	/**
	 * @return the current timestamp (in milliseconds)
	 */
	private final static long timestamp() {
		return System.nanoTime();
	}

	/**
	 * Builds the energy report from counters.
	 * 
	 * @param currentCounters  current value for all selected counters
	 * @param currentTimestamp current timestamp.
	 * @return the generated report.
	 */
	private final Map<String, Long> buildReport(Map<EnergyDomain, Long> currentCounters, long currentTimestamp) {
		Map<String, Long> report = new HashMap<String, Long>();

		long duration = currentTimestamp - initialTimestamp;
		report.put(DURATION, duration);

		long device = 0;
		for (Entry<EnergyDomain, Long> initial : initialCounters.entrySet()) {
			long value = currentCounters.get(initial.getKey());
			if (value >= initial.getValue())
				value = value - initial.getValue();
			else // Counter reached its max value before reset
				value = this.maxCounters.get(initial.getKey()) - initial.getValue() + value;
			report.put(initial.getKey().toString(), value);

			// Computes aggregated values per domain
			String domain = initial.getKey().getDomainKind();
			long aggregate = value;
			if (report.containsKey(domain)) {
				aggregate += report.get(domain);
			}
			report.put(domain, aggregate);

			// Computes the overall consumption of the device
			device += value;
		}

		if (device > 0) {
			report.put(DEVICE, device);
			report.put(POWER, device*1000000/duration);
		}

		return report;
	}

	private boolean stopped = false;

	private Map<String, Long> report;

	/**
	 * Stops the sampling processing and returns the final energy report.
	 * 
	 * @return the sampled energy
	 */
	public Map<String, Long> stop() {
		if (stopped)
			return report;
		this.report = buildReport(device.getDomainCounters(), timestamp());
		return report;
	}

	/**
	 * Reports on the energy consumed so far (but does not stop the sampling).
	 * 
	 * @return the energyConsumed
	 */
	public Map<String, Long> getEnergyReport() {
		return stopped ? report : buildReport(device.getDomainCounters(), timestamp());
	}
}
