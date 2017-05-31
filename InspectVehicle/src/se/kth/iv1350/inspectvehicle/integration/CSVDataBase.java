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

public class CSVDataBase implements DatabaseHandler {

	private ArrayList<String> inspectionsNeeded;
	private ArrayList<String> inspectionsLog;
	private String regNr;
	private String dataFilePath;

	public CSVDataBase(String regNr) {

		this.inspectionsNeeded = new ArrayList<String>();
		this.inspectionsLog = new ArrayList<String>();	
		this.regNr = regNr;

		try {
			URL dataFileUrl = CarFromDatabase.class.getResource("/se/kth/iv1350/inspectvehicle/database/database.csv");
			dataFilePath = dataFileUrl.toURI().toString().replaceFirst("^file:/", "");
			if (dataFilePath.startsWith("home")) { //for linux
				dataFilePath = "/" + dataFilePath;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Updates the database (currently a .csv file) with the inspections made, the results,
	 * and leaves failed inspections as "inspections needed" for later.
	 * Note that the indexes of the performed inspection and its result must match in the lists.
	 * @param inspectionsPerformed An <code>ArrayList</code> containing the performed inspections.
	 * @param resultsOfInspections An <code>ArrayList</code> containing the results.
	 */
	@Override
	public void updateDatabase(ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections) {
		List<String> lines = getLinesInFile();
		updateRelevantLine(lines, inspectionsPerformed, resultsOfInspections);
		writeToFile(lines);
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

	private void writeToFile(List<String> lines) {
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

		reAddFailedInspections(newLine, inspectionsPerformed, resultsOfInspections);
		newLine.append(delimiter);

		addResultsToLog(newLine, inspectionsPerformed, resultsOfInspections);
		newLine.append(delimiter);

		return newLine.toString();
	}

	private void addResultsToLog(StringBuilder newLine, ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections) {
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
	}

	private void reAddFailedInspections(StringBuilder newLine, ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections) {
		for (int i = 0; i < inspectionsPerformed.size(); i++) {
			String inspection = inspectionsPerformed.get(i);
			String result = resultsOfInspections.get(i);
			if (inspectionFailed(result)) {
				newLine.append(inspection);
				newLine.append(",");
			}
		}
	}

	private boolean inspectionFailed (String inspection) {
		if (inspection.equals("fail")) {
			return true;
		}
		return false;	
	}


	@Override
	public ArrayList<ArrayList<String>> getInformationFromDatabase() {

		InputStream inpStream = CSVDataBase.class.getResourceAsStream("/se/kth/iv1350/inspectvehicle/database/database.csv");
		BufferedReader databaseReader = new BufferedReader(new InputStreamReader(inpStream));

		String vehicleData = findVehicleInDatabase(databaseReader);

		writeToInspectionsNeeded(vehicleData);
		writeToInspectionsLog(vehicleData);

		ArrayList<ArrayList<String>> information = new ArrayList<ArrayList<String>>();
		information.add(inspectionsNeeded);
		information.add(inspectionsLog);

		return information;
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
