package se.kth.iv1350.inspectvehicle.view;

import se.kth.iv1350.inspectvehicle.controller.Controller;

/**
 * Represents the user interface, not coded here but instead the system calls are hard coded.
 * 
 * @author Max Körlinge
 *
 */
public class View {
	private Controller contr;

	/**
	 * Creates a new instance representing the user interface.
	 * 
	 * @param contr The controller instance that handles all calls from the View to the system itself
	 */
	public View(Controller contr) {
		this.contr = contr;
	}
}
