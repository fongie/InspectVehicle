package se.kth.iv1350.inspectvehicle.integration;

public class CarInDatabase {
	
	private String regNr;
	private String[] inspectionsNeeded;

	public CarInDatabase(String regNr) {
		this.regNr = regNr;
		
		getInformationFromDatabase();
	}
	
	public String[] getInspectionsNeeded() {
		return inspectionsNeeded;
	}
	
	public void updateDatabase(String[] inspectionsPerformed, String[] resultsOfInspections) {}
	
	private void getInformationFromDatabase() {
		
		//TODO fix database interaction
		String [] info = {"lights", "enginge", "brakes"};
		inspectionsNeeded = info;
	}
}
