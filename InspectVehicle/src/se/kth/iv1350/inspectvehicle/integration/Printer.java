package se.kth.iv1350.inspectvehicle.integration;

import se.kth.iv1350.inspectvehicle.model.PrintResults;
import se.kth.iv1350.inspectvehicle.model.Receipt;

/**
 * Handles calls to use the external printer.
 * 
 * @author Max Körlinge
 *
 */
public class Printer {
	
	/** Creates instance that will handle all print calls
	 * 
	 */
	public Printer() {
		
	}
	
	/**
	 * Prints a receipt.
	 * In current implementation, instead prints out the receipt to console,
	 * due to dummy implementation.
	 * @param receipt The receipt to be printed.
	 */
	public void print(Receipt receipt) {
		System.out.println(receipt.toString());
	}
	
	/**
	 * Prints a results sheet.
	 * In current implementation, instead prints out the results to console,
	 * due to dummy implementation.
	 * @param resultsToPrint The results to be printed.
	 */
	public void print(PrintResults resultsToPrint) {
		System.out.println(resultsToPrint.toString());
	}
}
