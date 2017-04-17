package se.kth.iv1350.inspectvehicle.controller;

import se.kth.iv1350.garage.Garage;
import se.kth.iv1350.inspectvehicle.integration.CarRegistry;
import se.kth.iv1350.inspectvehicle.model.payment.CashRegister;

public class Controller {
	private CarRegistry carRegistry;
	private CashRegister cashReg;
	private Garage grg;
	
	/**
	 * Starts an instance of the Controller, which handles all calls from the View to the Model.
	 * 
	 * @param carRegistry An instance of the registry containing all registration numbers.
	 * @param cashReg The cashregister, containing the balance in cash
	 */
	public Controller(CarRegistry carRegistry, CashRegister cashReg) {
		this.carRegistry = carRegistry;
		this.cashReg = cashReg;
		this.grg = new Garage();
	}
}
