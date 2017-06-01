package se.kth.iv1350.inspectvehicle.integration;

import java.util.ArrayList;

/**
 * Represents the information concerning a car that is in the database.
 * @author Max Körlinge
 *
 */
public class CarFromDatabase {
	
	private String regNr;
	private ArrayList<String> inspectionsNeeded;
	private ArrayList<String> inspectionsLog;
	private DatabaseHandler dbHandler;

	/**
	 * Creates an instance of a Car, containing information
	 * about inspections needed and past inspections made.
	 * @param regNr The car's registration number.
	 */
	public CarFromDatabase(String regNr) {
		this.regNr = regNr;
		this.inspectionsNeeded = new ArrayList<String>();
		this.inspectionsLog = new ArrayList<String>();
		this.dbHandler = new CSVDataBase(regNr);
			
		inspectionsNeeded = dbHandler.getInspectionsFromDatabase();
		inspectionsLog = dbHandler.getLogFromDatabase();
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
	 * Updates the database
	 * and leaves failed inspections as "inspections needed" for later.
	 * Note that the indexes of the performed inspection and its result must match in the lists.
	 * @param inspectionsPerformed An <code>ArrayList</code> containing the performed inspections.
	 * @param resultsOfInspections An <code>ArrayList</code> containing the results.
	 */
	public void updateDatabase(ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections) {
		dbHandler.updateDatabase(inspectionsPerformed, resultsOfInspections);
	}

}