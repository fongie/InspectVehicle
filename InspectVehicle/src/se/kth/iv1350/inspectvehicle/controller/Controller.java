package se.kth.iv1350.inspectvehicle.controller;

import se.kth.iv1350.garage.Garage;
import se.kth.iv1350.inspectvehicle.integration.CarRegistry;
import se.kth.iv1350.inspectvehicle.integration.Printer;
import se.kth.iv1350.inspectvehicle.model.*;
import se.kth.iv1350.payauth.CreditCard;

/**
 * The only controller class for the program, all calls from the user interface
 * pass through here.
 * @author Max Körlinge
 */
public class Controller {
	private CarRegistry carRegistry;
	private CashRegister cashReg;
	private Garage grg;
	private Printer printer;
	private Inspection currentInspection;
	
	/**
	 * Constant String used to end loops depending on String return values
	 */
	public static final String LOOPENDER = "END LOOP";
	
	/**
	 * Starts an instance of the <code>Controller</code>, which handles all calls from the View to the Model.
	 * 
	 * @param carRegistry An instance of the registry containing all registration numbers.
	 * @param cashReg The cashregister, containing the balance in cash
	 * @param prnt Interface to the external printer
	 */
	public Controller(CarRegistry carRegistry, CashRegister cashReg, Printer prnt) {
		this.carRegistry = carRegistry;
		this.cashReg = cashReg;
		this.grg = new Garage();
		this.printer = prnt;
	}
	
	/**
	 * Instruct the <code>Garage</code> to display next customer's queue number and open the garage door.
	 */
	public void beginInspection() {
		grg.nextCustomer();
	}


	/**
	 * Close the garage door.
	 */
	public void closeDoor() {
		grg.closeDoor();
	}
	
	/**
	 * User inputs the registration number of a car. The system returns the cost of the inspection of this car, if the registration number exists in the database.
	 * @param vehicleRegNr The car's registration number
	 * @return The cost of the upcoming inspection.
	 */
	public int enterRegNr(String vehicleRegNr) {
		while(true) {
			boolean numberExists = carRegistry.doesNrExist(vehicleRegNr);
			if (numberExists) {
				break;
			} else {
				//we do not code alternate flows, in this program, this always exist
			}
		}
		currentInspection = new Inspection(vehicleRegNr, printer);
		return currentInspection.getCost();
	}
	
	/**
	 * Instruct system that user is paying by credit card.
	 * @param card The credit card information to be paid with
	 */
	public void payByCC(CreditCard card) {
		new CreditCardPayment(card, currentInspection.getCost(), printer);
	}
	
	/**
	 * Instruct system that user is paying by cash.
	 * Not implemented (we do not code alternate flows)
	 * @param amount The amount paid by the customer in cash.
	 */
	public void payByCash(int amount) {}
	
	/**
	 * Get what item or task to inspect next on the vehicle.
	 * @return The item or task as a string.
	 */
	public String whatInspectNext() {
		return currentInspection.toInspectNext();
	}
	
	/**
	 * Check if there are more inspection tasks in the list of inspections to make.
	 * @return True if there are inspections left, false if not.
	 */
	public boolean hasMoreInspectionsToMake() {
		return currentInspection.hasMoreInspectionsToMake();
	}
	
	/**
	 * Enter the result of the current item being inspected.
	 * If this is the last of the tasks being inspected, also finish the inspection.
	 * @param result The result. Either "pass" or "fail".
	 */
	public void enterResultOfInspection(String result) {
			currentInspection.addResult(result);
			if (!currentInspection.hasMoreInspectionsToMake()) {
				currentInspection.finishInspection();
			}
	}
}

