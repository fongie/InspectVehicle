package se.kth.iv1350.inspectvehicle.model;

import java.util.ArrayList;
import se.kth.iv1350.inspectvehicle.integration.Printer;

/**
 * Handles creating and printing the results sheet after an inspection is completed.
 * @author Max Körlinge
 *
 */
public class PrintResults {
	
	private final String delimiter = "---------------------------\n";
	private final String firstPartOfResultsSheet = delimiter
			+ "RESULTS SHEET FROM MYVEHICLEINSPECTIONCOMPANY\n"
			+ delimiter;
	private String changeablePartOfResultsSheet;
	private final String lastPartOfResultsSheet = delimiter;

	/** 
	 * Create an instance of the results of the inspection and print them.
	 * Note that the indexes of the inspection task made and its result must match in the lists.
	 * @param inspectionsPerformed An <code>ArrayList</code> containing the inspections that were performed.
	 * @param results An <code>ArrayList</code> containing the results of the inspections.
	 * @param printer The interface to the external printer.
	 */
	public PrintResults(ArrayList<String> inspectionsPerformed, ArrayList<String> results, Printer printer) {
		changeablePartOfResultsSheet = buildChangeablePart(inspectionsPerformed, results);
		printer.print(this);
	}
	
	/**
	 * Returns a string containing the full results sheet.
	 * @return The string to be printed or used in another way.
	 */
	public String toString() {
		StringBuilder fullResultsSheet = new StringBuilder();
		fullResultsSheet.append(firstPartOfResultsSheet);
		fullResultsSheet.append(changeablePartOfResultsSheet);
		fullResultsSheet.append(lastPartOfResultsSheet);
		return fullResultsSheet.toString();
	}
	
	private String buildChangeablePart(ArrayList<String> inspectionsPerformed, ArrayList<String> results) {
		StringBuilder toWrite = new StringBuilder();
		toWrite.append("TASK : RESULT\n\n");
		for (int i = 0; i < inspectionsPerformed.size(); i++) {
			toWrite.append(inspectionsPerformed.get(i));
			toWrite.append(" : ");
			toWrite.append(results.get(i));
			toWrite.append("\n");
		}
		return toWrite.toString();
	}
	
}