/**
 * 
 */
package jJoules.rapl.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author sanoussy
 *
 */
public class RaplPackageDomain {

	/**
	 * 
	 */
	public static String PATH_NAME = "/sys/devices/virtual/powercap/intel-rapl/intel-rapl:0";
	public RaplPackageDomain() {
		
	}
	
	public String getDomainName() {
		return "package-0";
	}
	
	public String openAndRead(String pathName) {
		
		File file = new File(pathName);
		FileReader fr;
		String name = "";
		try {
			fr = new FileReader(file);
			name = new BufferedReader(fr).readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

}
