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
	 * @param args No command line arguments are taken.
	 */
	public static void main(String[] args) {
	
		CashRegister cashRegister = new CashRegister();
		Printer printer = new Printer();
		Controller contr = new Controller(cashRegister, printer);
		new View(contr);
	}
}
