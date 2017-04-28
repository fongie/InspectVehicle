package se.kth.iv1350.inspectvehicle.integration;

/**
 * Thrown when a registration number entered into the system to start an inspection cannot be found in the database.
 * 
 * @author Max Körlinge
 *
 */
public class RegistrationNumberNotFoundException extends Exception {
	
	/**
	 * Create new instance with a message concerning the failed operation.
	 * 
	 * @param regNr
	 */
	public RegistrationNumberNotFoundException(String regNr) {
		
		super("Cannot find registration number " + regNr + " in database.");
	}
}
