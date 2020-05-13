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
		this.socket = socket;
	}
	
	/**
	 * @return domain name
	 */
	public abstract String getDomainName();
	
	public int getSocket() {
		return this.socket;
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
	
	public boolean domainPathExist(String pathName) {
		return new File(pathName).exists();
	}
	
	public String toString() {
		return this.getDomainName()+"_"+this.getSocket();
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o instanceof RaplDomain) {
			RaplDomain other = (RaplDomain) o;
			return this.toString().equals(other.toString());
		} return false;
	}
	
	
}
