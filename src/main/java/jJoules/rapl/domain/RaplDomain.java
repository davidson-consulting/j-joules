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
public abstract class RaplDomain {
	
	public static String RAPL_PATH_NAME = "/sys/devices/virtual/powercap/intel-rapl";
	
	private int socket;

	/**
	 * 
	 */
	public RaplDomain(int socket) {
	}
	
	/**
	 * @return domain name
	 */
	public abstract String getDomainName();
	
	public int getSocket() {
		return 0;
	}
	
	public String openAndReadFile(String pathName) {
		
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
	
	
	public String toString() {
		return "";
	}
	
	public boolean equals(Object o) {
		return false;
	}
	
	
}
