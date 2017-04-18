package se.kth.iv1350.inspectvehicle.model;

import se.kth.iv1350.inspectvehicle.integration.CarInDatabase;
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
	private CarInDatabase currentCar;
	private String[] inspectionsToPerform;
	private String[] resultsOfInspection;
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
		
		currentCar = new CarInDatabase(currentVehicleRegnr);
		inspectionsToPerform = currentCar.getInspectionsNeeded();
		cost = calculateCost();
	}
		
	/**
	 * Get the total cost of the inspection.
	 * @return The cost of the inspection to be paid by customer.
	 */
	public int getCost() {
		return cost;
	}
	
	public String toInspectNext() {}
	
	public void addResult(String result) {
	//TODO MAKE ENUM FOR RESULT FALSE OR TRUE, see course litterature
	}
	
	public void finishInspection() {}
	
	//each part of the inspection costs 500, 
	//could implement enum to handle different costs for different parts
	private int calculateCost() {
		int totalCost = 0;
		for (int i = 0; i < inspectionsToPerform.length; i++) {
			totalCost += costPerPartOfInspection;
		}
		return totalCost;
	}

}
