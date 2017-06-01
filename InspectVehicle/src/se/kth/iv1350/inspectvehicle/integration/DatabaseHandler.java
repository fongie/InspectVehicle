package se.kth.iv1350.inspectvehicle.integration;

import java.util.ArrayList;

/**
 * Iterface for database handlers, used by classes that call different types of databases.
 * 
 * @author Max Körlinge
 *
 */
public interface DatabaseHandler {

	
	/**
	 * Updates the database
	 * and leaves failed inspections as "inspections needed" for later.
	 * Note that the indexes of the performed inspection and its result must match in the lists.
	 * @param inspectionsPerformed An <code>ArrayList</code> containing the performed inspections.
	 * @param resultsOfInspections An <code>ArrayList</code> containing the results.
	 */
	public void updateDatabase(ArrayList<String> inspectionsPerformed, ArrayList<String> resultsOfInspections);
	
	/**
	 * Gets the inspections needed for the car from the database.
	 * @return The list of inspections as an <code>ArrayList</code>
	 */
	public ArrayList<String> getInspectionsFromDatabase();
	
	/**
	 * Gets the log of previous inspections made for the car from the database.
	 * @return The log as an <code>ArrayList</code>
	 */
	public ArrayList<String> getLogFromDatabase();
	
}
