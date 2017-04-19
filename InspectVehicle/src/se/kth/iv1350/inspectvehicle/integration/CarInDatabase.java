package se.kth.iv1350.inspectvehicle.integration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Represents the information concerning a car that is in the database.
 * @author Max Körlinge
 *
 */
public class CarInDatabase {
	
	private String regNr;
	private ArrayList<String> inspectionsNeeded;
	private ArrayList<String> inspectionsLog;

	/**
	 * Creates an instance of the car in database, containing information
	 * about inspections needed. Also updates the information in the database
	 * when the inspection is finished.
	 * @param regNr The car's registration number.
	 */
	public CarInDatabase(String regNr) {
		this.regNr = regNr;
		this.inspectionsNeeded = new ArrayList<String>();
		this.inspectionsLog = new ArrayList<String>();
		
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
	
	//HÄR ÄR JAG TODO
	public void updateDatabase(ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections) {
		
	}
	
	private void getInformationFromDatabase() {

			InputStream inpStream = CarInDatabase.class.getResourceAsStream("/se/kth/iv1350/inspectvehicle/database/database.csv");
			BufferedReader databaseReader = new BufferedReader(new InputStreamReader(inpStream));
			
			//if regnr is valid or not is checked earlier in the chain, dont need it here
			String vehicleData = findVehicleInDatabase(databaseReader);
			
			writeToInspectionsNeeded(vehicleData);
			writeToInspectionsLog(vehicleData);
	}
	
	private void writeToInspectionsLog(String vehicleData) {
		String inspectionsLogSplitoff = vehicleData.split(";")[1];
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