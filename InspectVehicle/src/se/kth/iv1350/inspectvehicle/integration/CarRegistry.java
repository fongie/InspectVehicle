package se.kth.iv1350.inspectvehicle.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The registry containing a list of all registration numbers present in the database.
 * @author Max Körlinge
 *
 */
public class CarRegistry {

	private ArrayList<String> carsInDatabase;
	private BufferedReader databaseReader;

	/**
	 * Creates the CarRegistry containing the registration numbers present in the database.
	 */
	public CarRegistry() {
		carsInDatabase = new ArrayList<String>();
		try {
			
		databaseReader = openDatabaseFileAsStream();
		putRegNumbersInList();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			databaseReader.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Check if a specific registration number exists in the car inspection database.
	 * @param specificRegNr The registration number we are checking for
	 * @return True if it exists, false if it does not
	 */
	public boolean doesNrExist(String specificRegNr) {
		for (int i = 0; i < carsInDatabase.size(); i++) {
			if(!carsInDatabase.get(i).equals(specificRegNr)) {
				continue;
			} else {
				return true;
			}
		}
		return false;
	}
	
	private void putRegNumbersInList() {
		String nextLine;
		try {
			while ((nextLine = databaseReader.readLine()) != null) {
				String regNr = nextLine.split(";")[0];
				carsInDatabase.add(regNr);
				}
			} catch (Exception e) {
			e.printStackTrace();
			}
	}

	private BufferedReader openDatabaseFileAsStream() {
			InputStream inpStream = CarRegistry.class.getResourceAsStream("/se/kth/iv1350/inspectvehicle/database/database.csv");
			return new BufferedReader(new InputStreamReader(inpStream));
	}
}
