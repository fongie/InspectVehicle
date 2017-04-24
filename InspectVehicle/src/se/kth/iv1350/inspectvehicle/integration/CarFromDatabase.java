package se.kth.iv1350.inspectvehicle.integration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the information concerning a car that is in the database.
 * @author Max Körlinge
 *
 */
public class CarFromDatabase {
	
	private String regNr;
	private ArrayList<String> inspectionsNeeded;
	private ArrayList<String> inspectionsLog;
	private String dataFilePath;

	/**
	 * Creates an instance of the car in database, containing information
	 * about inspections needed. Also updates the information in the database
	 * when the inspection is finished.
	 * @param regNr The car's registration number.
	 */
	public CarFromDatabase(String regNr) {
		this.regNr = regNr;
		this.inspectionsNeeded = new ArrayList<String>();
		this.inspectionsLog = new ArrayList<String>();
		
		try {
			URL dataFileUrl = CarFromDatabase.class.getResource("/se/kth/iv1350/inspectvehicle/database/database.csv");
			dataFilePath = dataFileUrl.toURI().toString().replaceFirst("^file:/", "");
			if (dataFilePath.startsWith("home")) { //for linux
				dataFilePath = "/" + dataFilePath;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getInformationFromDatabase();
	}
	
	/**
	 * Returns the inspections needed for this vehicle.
	 * @return The inspections needed.
	 */
	public ArrayList<String> getInspectionsNeeded() {
		return inspectionsNeeded;
	}
	
	/**
	 * Returns the log of inspections previously made and their result.
	 * @return The log of inspections and results made.
	 */
	public ArrayList<String> getLog() {
		return inspectionsLog;
	}
	
	/**
	 * Updates the database (currently a .csv file) with the inspections made, the results,
	 * and leaves failed inspections as "inspections needed" for later.
	 * Note that the indexes of the performed inspection and its result must match in the lists.
	 * @param inspectionsPerformed An <code>ArrayList</code> containing the performed inspections.
	 * @param resultsOfInspections An <code>ArrayList</code> containing the results.
	 */
	public void updateDatabase(ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections) {
		List<String> lines = getLinesInFile();
		updateRelevantLine(lines, inspectionsPerformed, resultsOfInspections);
		writeFile(lines);
	}
	
	private List<String> getLinesInFile() {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(dataFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	private void updateRelevantLine(List<String> lines, ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections) {
			String line;
			for (int i = 0; i < lines.size(); i++) {
				line = lines.get(i);
				if (!line.startsWith(regNr)) {
					continue;
				}
				String replacedLine = buildNewLineInDatabase(inspectionsPerformed, resultsOfInspections);
				lines.set(i, replacedLine);
				}
	}
	
	private void writeFile(List<String> lines) {
		try {
			Files.write(Paths.get(dataFilePath), lines);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	//format in database is
	//regnr;inspectionsneeded1,2,3,;logDATE|inspection:result,inspection:result,|DATE|...
	private String buildNewLineInDatabase(ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections) {
		String delimiter = ";";
		StringBuilder newLine = new StringBuilder();
		newLine.append(regNr);
		newLine.append(delimiter);

		for (int i = 0; i < inspectionsPerformed.size(); i++) {
			String inspection = inspectionsPerformed.get(i);
			String result = resultsOfInspections.get(i);
			if (inspectionFailed(result)) {
				newLine.append(inspection);
				newLine.append(",");
			}
		}
		newLine.append(delimiter);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime timeInspectionFinished = LocalDateTime.now();
		newLine.append(dtf.format(timeInspectionFinished));
		newLine.append("|");
		for (int j = 0; j < inspectionsPerformed.size(); j++) {
			newLine.append(inspectionsPerformed.get(j));
			newLine.append(" : ");
			newLine.append(resultsOfInspections.get(j));
			newLine.append(" , ");
		}
		newLine.append("|");
		newLine.append(delimiter);
		
		return newLine.toString();
	}
	
	private boolean inspectionFailed (String inspection) {
		if (inspection.equals("fail")) {
			return true;
		}
		return false;	
	}
	
	private void getInformationFromDatabase() {

		InputStream inpStream = CarFromDatabase.class.getResourceAsStream("/se/kth/iv1350/inspectvehicle/database/database.csv");
		BufferedReader databaseReader = new BufferedReader(new InputStreamReader(inpStream));
		
		//if regnr is valid or not is checked earlier in the chain, dont need it here
		String vehicleData = findVehicleInDatabase(databaseReader);
		
		writeToInspectionsNeeded(vehicleData);
		writeToInspectionsLog(vehicleData);
	}
	
	private void writeToInspectionsLog(String vehicleData) {
		String inspectionsLogSplitoff = vehicleData.split(";")[2];
		String[] logItems = inspectionsLogSplitoff.split(",");
		for (String item : logItems) {
				inspectionsLog.add(item);
			}
	}
	
	private void writeToInspectionsNeeded(String vehicleData) {
		String inspectionsNeededSplitoff = vehicleData.split(";")[1];
		String[] inspectionPartsNeeded = inspectionsNeededSplitoff.split(",");
		for (String part : inspectionPartsNeeded) {
				inspectionsNeeded.add(part);
			}
	}
	
	private String findVehicleInDatabase(BufferedReader databaseReader) {
		String vehicleData = new String();
		try {
			String nextLineInDatabase;
				while ((nextLineInDatabase = databaseReader.readLine()) != null) {
					if (!nextLineInDatabase.startsWith(regNr)) {
						continue;
					} else {
						vehicleData = nextLineInDatabase;
					}
				} 
			} catch (Exception e) {
				System.out.println("Error fetching data from database: " + e.getMessage());
				}
		return vehicleData;
	}
}