package se.kth.iv1350.inspectvehicle.integration;

import java.util.ArrayList;

/**
 * Represents the information concerning a car that is in the database.
 * @author Max Körlinge
 *
 */
public class CarInDatabase {
	
	private String regNr;
	//private String[] inspectionsNeeded;
	private ArrayList<String> inspectionsNeeded;

	/**
	 * Creates an instance of the car in database, containing information
	 * about inspections needed. Also updates the information in the database
	 * when the inspection is finished.
	 * @param regNr The car's registration number.
	 */
	public CarInDatabase(String regNr) {
		this.regNr = regNr;
		getInformationFromDatabase();
	}
	
	/**
	 * Returns the inspections needed for this vehicle.
	 * @return The inspections needed.
	 */
	public ArrayList<String> getInspectionsNeeded() {
		return inspectionsNeeded;
	}
	
	public void updateDatabase(String[] inspectionsPerformed, String[] resultsOfInspections) {}
	
	private void getInformationFromDatabase() {
		//TODO fix database interaction
		String[] info = {"lights", "enginge", "brakes"};
		for (String item : info) {
			inspectionsNeeded.add(item);
		}
	}
}