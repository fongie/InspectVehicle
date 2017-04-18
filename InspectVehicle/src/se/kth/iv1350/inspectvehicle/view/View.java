package se.kth.iv1350.inspectvehicle.view;


import java.time.YearMonth;

import se.kth.iv1350.inspectvehicle.controller.Controller;
import se.kth.iv1350.payauth.CreditCard;

/**
 * Represents the user interface, not coded here but instead the system calls are hard coded.
 * 
 * @author Max Körlinge
 */
public class View {
	private Controller contr;

	/**
	 * Creates a new instance representing the user interface. It also starts the chain of commands that are given by the View,
	 *  that is, the Inspector using the program, but here they are hardcoded.
	 * 
	 * @param contr The controller instance that handles all calls from the View to the system itself
	 */
	public View(Controller contr) {
		this.contr = contr;
		
		startHardCodedCommands();
	}
	
	private void startHardCodedCommands() {
		
		contr.beginInspection();
		System.out.println("Begin new inspection: Display next number and open garage door");

		contr.closeDoor();
		System.out.println("Closing garage door.");

		String sampleRegNr = "ABC123";
		System.out.println("Inputting customer car with registration number " + sampleRegNr + " into the system.");
		int cost = contr.enterRegNr(sampleRegNr);
		System.out.println("The cost of the inspection is: " + cost + ".");
		
		System.out.println("User wishes to pay by Credit Card. Inputting credit card details..");
		
		System.out.println("Receipt:\n");
		CreditCard customerCC = new CreditCard(0, "1234-5678-1234", "Fake Fakesson", YearMonth.of(2020, 10), 500);
		contr.payByCC(customerCC);
		

	}
}
