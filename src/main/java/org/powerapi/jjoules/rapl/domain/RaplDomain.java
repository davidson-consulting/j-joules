/**
 * 
 */
package org.powerapi.jjoules.rapl.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.powerapi.jjoules.NoSuchEnergyDeviceException;
import org.powerapi.jjoules.domain.EnergyDomain;
import org.powerapi.jjoules.rapl.RaplDevice;

/**
 * @author sanoussy
 *
 */
public abstract class RaplDomain extends EnergyDomain {

	protected static final String RAPL_DIR = "/intel-rapl";

	private static String RAPL_PATH = "/sys/devices/virtual/powercap" + RAPL_DIR;

	public static final String RAPL_PKG_PATH = RAPL_PATH + RAPL_DIR + ":";

	private int socket;

	public static final String domainPath(int socketId) {
		return RAPL_PKG_PATH + socketId;
	}

	/**
	 * 
	 */
	public RaplDomain(int socket) {
		this.socket = socket;
	}

	@Override
	public String getDeviceType() {
		return RaplDevice.class.getName();
	}

	/**
	 * @return the domain socket
	 */
	public int getSocket() {
		return this.socket;
	}

	@Override
	public String domainPath() {
		return RAPL_PATH;
	}

	/**
	 * @param pathName path to open and read content
	 * @return content of the file
	 */
	public static String openAndReadFile(String pathName) {

		File file = new File(pathName);
		FileReader fr;
		String name = "";
		try {
			fr = new FileReader(file);
			name = new BufferedReader(fr).readLine();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return name;
		}

	}

	/**
	 * @param pathName path to check existence
	 * @return true if pathName exist and false otherwise
	 */
	public static boolean domainPathExist(String pathName) {
		return new File(pathName).exists();
	}

	/**
	 * @return a representation of each domain like `package-0`
	 */
	public String toString() {
		return this.getDomainName() + "_" + this.getSocket();
	}

	public static final void check() throws NoSuchEnergyDeviceException {
		if (!new File(RAPL_PATH).exists())
			throw new NoSuchEnergyDeviceException("RAPL domain \'" + RAPL_PATH + "\' does not exist.");
	}
}
