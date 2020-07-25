/**
 * 
 */
package org.powerapi.jjoules.energy.display.utils;

import java.util.List;

/**
 * Class for saving the result of one class test
 * @author sanoussy
 *
 */
public class Data {
	
	private String className;
	private List<Result> methods;
	/**
	 * 
	 */
	public Data(String className, List<Result> methods) {
		this.className = className;
		this.methods = methods;
	}
	
	public List<Result> getMethods(){
		return this.methods;
	}
	
	public String getClassName() {
		return this.className;
	}
	
	
}
