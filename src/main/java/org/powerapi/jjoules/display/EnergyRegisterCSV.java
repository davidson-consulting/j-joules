package org.powerapi.jjoules.display;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sanoussy
 *
 */
public class EnergyRegisterCSV extends EnergyDisplayHandler {

	public static EnergyRegisterCSV ENERGY_REGISTER_CSV = new EnergyRegisterCSV();
	public static List<Data> ALL_DATA = new ArrayList<Data>();
	public static String CURRENT_CLASS_NAME = "";
	private static String fileName = "target/jjoules-reports/report.csv";

	private EnergyRegisterCSV() {
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void displayIt() {
		int id = 1;
		File file = new File(this.fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("id;tag;classTest;energyConsumed;duration\n");
			for (Data data : ALL_DATA) {
				int subId = 0;
				for (EnergySample result : data.getMethods()) {
					bw.write(id + "" + subId + ";" + result.getTestName() + ";" + data.getClassName() + ";"
							+ result.getEnergyConsumed() + ";" + result.getDuration() + "\n");
					subId++;
				}
				id++;

			}
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
