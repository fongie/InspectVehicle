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
	private Printer prnt;
	private Inspection currentInspection;
	
	/**
	 * Starts an instance of the Controller, which handles all calls from the View to the Model.
	 * 
	 * @param carRegistry An instance of the registry containing all registration numbers.
	 * @param cashReg The cashregister, containing the balance in cash
	 * @param prnt Interface to the external printer
	 */
	public Controller(CarRegistry carRegistry, CashRegister cashReg, Printer prnt) {
		this.carRegistry = carRegistry;
		this.cashReg = cashReg;
		this.grg = new Garage();
		this.prnt = prnt;
	}
	
	/**
	 * Instruct the Garage to display next customer's queue number and open the garage door.
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
		
		Inspection inspection = new Inspection(vehicleRegNr, prnt);
		return inspection.getCost();
	}
	
	public void payByCC(CreditCard card) {
		new CreditCardPayment(card, currentInspection.getCost());
	}
	
	public void payByCash(int amount) {}
	
	public String whatInspectNext() {}
	
	public void enterResultOfInspection(String result) {}
	
}

