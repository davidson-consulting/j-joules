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
	public static final String DURATION = "duration";

	private final EnergyDevice device;
	private final Map<String, Long> maxCounters;
	private final Map<String, Long> initialCounters;
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
		return System.currentTimeMillis();
	}

	/**
	 * Builds the energy report from counters.
	 * 
	 * @param currentCounters  current value for all selected counters
	 * @param currentTimestamp current timestamp.
	 * @return the generated report.
	 */
	private final Map<String, Long> buildReport(Map<String, Long> currentCounters, long currentTimestamp) {
		Map<String, Long> report = new HashMap<String, Long>();
		report.put(DURATION, currentTimestamp - initialTimestamp);
		for (Entry<String, Long> domains : initialCounters.entrySet()) {
			long value = currentCounters.get(domains.getKey());
			if (value >= domains.getValue())
				value = value - domains.getValue();
			else // Counter reached its max value before reset
				value = this.maxCounters.get(domains.getKey()) - domains.getValue() + value;
			report.put(domains.getKey(), value);
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
