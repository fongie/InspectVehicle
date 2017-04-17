package se.kth.iv1350.inspectvehicle.integration;

/**
 * The registry containing a list of all registration numbers present in the database.
 * @author Max Körlinge
 *
 */
public class CarRegistry {

	private String[] carsInReg;

	/**
	 * Creates the CarRegistry containing the cars in the database. Needs a method to actually fetch these from database.
	 */
	public CarRegistry() {
		//TODO make text file in database, read it from here and input reg nrs into casrInReg
		
		String[] cars= {"ABC123", "DEF456","GHI789"};
		carsInReg = cars;
	}
	
	/**
	 * Check if a specific registration number exists in the car inspection database.
	 * @param specificRegNr The registration number we are checking for
	 * @return True if it exists, false if it does not
	 */
	public boolean doesNrExist(String specificRegNr) {
		for (String carRegNr : carsInReg) {
			if (specificRegNr.equals(carRegNr)) {
				return true;
			}
		}
		return false;
	}
}
