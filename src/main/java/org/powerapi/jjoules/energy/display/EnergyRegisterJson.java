/**
 * 
 */
package org.powerapi.jjoules.energy.display;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.powerapi.jjoules.energy.display.utils.Data;

import com.google.gson.Gson;


/**
 * @author sanoussy
 *
 */
public class EnergyRegisterJson extends EnergyDisplayHandler {

	public static EnergyRegisterJson ENERGY_REGISTER_Json = new EnergyRegisterJson();
	public static String CURRENT_CLASS_NAME = "";
	
	public static List<Data> ALL_DATA = new ArrayList<Data>();
	private static String fileName = "target/jjoules-reports";
	
	
	private EnergyRegisterJson() {
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
		saveIt(this.fileName);
		
	}

	
	public static void saveIt(String fileName) {
		Gson gson = new Gson();
		File file = new File(fileName+"/report.json");
		if (! file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter  fw = new FileWriter(file.getAbsoluteFile());
			gson.toJson(gson.toJsonTree(ALL_DATA), fw);
			fw.flush();
			fw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
