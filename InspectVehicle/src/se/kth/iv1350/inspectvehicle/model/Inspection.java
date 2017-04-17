package se.kth.iv1350.inspectvehicle.model;

import se.kth.iv1350.inspectvehicle.integration.CarInDatabase;
import se.kth.iv1350.inspectvehicle.integration.Printer;

public class Inspection {

	private String currentVehicleRegnr;
	private Printer printer;
	private CarInDatabase currentCar;
	private String[] inspectionsToPerform;
	private String[] resultsOfInspection;
	
	public Inspection(String regNr, Printer printer) {
		this.currentVehicleRegnr = regNr;
		this.printer = printer;
	}
	
	private int calculateCost() {}
	
	public String toInspectNext() {}
	
	public void addResult(String result) {
	//TODO MAKE ENUM FOR RESULT FALSE OR TRUE, see course litterature
	}
	
	public void finishInspection() {}
}
