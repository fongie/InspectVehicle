package se.kth.iv1350.inspectvehicle.integration;

import java.util.ArrayList;
import java.util.List;

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
	 * Gets the required information from the database.
	 * @return A list of two lists. Index 0 is the inspections needed, index 1 is the log of previous inspections.
	 */
	public ArrayList<ArrayList<String>> getInformationFromDatabase();
	
}
