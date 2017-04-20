package se.kth.iv1350.inspectvehicle.start;

import se.kth.iv1350.inspectvehicle.controller.Controller;
import se.kth.iv1350.inspectvehicle.integration.CarRegistry;
import se.kth.iv1350.inspectvehicle.integration.Printer;
import se.kth.iv1350.inspectvehicle.model.CashRegister;
import se.kth.iv1350.inspectvehicle.view.View;

/**
 * Main class, starts program.
 * @author Max Körlinge
 *
 */
public class InspectVehicle {

	/**
	 * Starts the program, creates important class instances and references them to eachother.
	 *
	 * @param args No command line arguments are taken.
	 */
	public static void main(String[] args) {
	
		System.out.println("Starting program...");
		
		CarRegistry registry = new CarRegistry();
		CashRegister cashreg = new CashRegister();
		Printer prnt = new Printer();
		Controller contr = new Controller(registry,cashreg, prnt);
		new View(contr);
	}
}
