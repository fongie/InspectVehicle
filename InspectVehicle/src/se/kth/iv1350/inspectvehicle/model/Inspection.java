package se.kth.iv1350.inspectvehicle.model;

import java.util.ArrayList;
import java.util.Iterator;

import se.kth.iv1350.inspectvehicle.integration.CarFromDatabase;
import se.kth.iv1350.inspectvehicle.integration.Printer;

/**
 * Represents the Inspection that takes place on demand by the customer. 
 * Knows which inspections to perform, how much they cost, and handles writing the results and the receipt of them.
 * @author Max Körlinge
 *
 */
public class Inspection {

	private String currentVehicleRegnr;
	private Printer printer;
	private CarFromDatabase currentCar;
	private ArrayList<String> inspectionsToPerform;
	private Iterator<String> inspectionsIterator;
	private ArrayList<String> resultsOfInspection;
	private int cost;
	private final int costPerPartOfInspection = 500;
	
	/**
	 * Creates instance of an Inspection, that is, the entire inspection that starts when a customer walks in the door.
	 * 
	 * @param regNr The registration number of the car that is to be inspected.
	 * @param printer The interface to the printer used to print the results.
	 */
	public Inspection(String regNr, Printer printer) {
		this.currentVehicleRegnr = regNr;
		this.printer = printer;
		currentCar = new CarFromDatabase(currentVehicleRegnr);
		inspectionsToPerform = currentCar.getInspectionsNeeded();
		inspectionsIterator = inspectionsToPerform.iterator();
		resultsOfInspection = new ArrayList<String>();
		cost = calculateCost();
	}
	
	/**
	 * Get the next task to inspect on the current vehicle. Iterates through the
	 * inspections needed. If this array has more items or not is not checked here but
	 * must be checked in the caller, using <code>hasMoreInspectionsToMake()</code>
	 * @return The next item or task to inspect, as a String
	 */
	public String toInspectNext() {
		return inspectionsIterator.next();
	}
	
	/**
	 * Check if there are more inspection tasks in the list of inspections to make.
	 * @return True if there are inspections left, false if not.
	 */
	public boolean hasMoreInspectionsToMake() {
		return inspectionsIterator.hasNext();
	}
	
	/**
	 * Add the result of an inspection, either "pass" or "fail".
	 * Note that the result at index i in this list will correspond to index i in the
	 * list of inspections to make.
	 * Wrong input must be checked at method call, it is not checked by the method itself.
	 * @param result The result to be written.
	 */
	public void addResult(String result) {
		resultsOfInspection.add(result);
	}
	
	/**
	 * Finish the inspection, that means update the database with the results and print a results sheet.
	 */
	public void finishInspection() {
		currentCar.updateDatabase(inspectionsToPerform, resultsOfInspection);
		new PrintResults(inspectionsToPerform, resultsOfInspection, printer);
	}
	
	/**
	 * Get the total cost of the inspection.
	 * @return The cost of the inspection to be paid by customer.
	 */
	public int getCost() {
		return cost;
	}
	
	private int calculateCost() {
		int totalCost = 0;
		for (int i = 0; i < inspectionsToPerform.size(); i++) {
			totalCost += costPerPartOfInspection;
		}
		return totalCost;
	}
}
