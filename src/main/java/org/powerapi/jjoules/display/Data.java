/**
 * 
 */
package org.powerapi.jjoules.display;

import java.util.List;

/**
 * Class for saving the result of one class test
 * 
 * @author sanoussy
 *
 */
public class Data {

	private String className;
	private List<EnergySample> methods;

	/**
	 * 
	 */
	public Data(String className, List<EnergySample> methods) {
		this.className = className;
		this.methods = methods;
	}

	public List<EnergySample> getMethods() {
		return this.methods;
	}

	public String getClassName() {
		return this.className;
	}

}
