package se.kth.iv1350.inspectvehicle.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a receipt to be written by the printer.
 * 
 * @author Max Körlinge
 *
 */
public class Receipt {
	
	private final String delimiter = "=======================\n";
	private final String firstPartOfReceipt = delimiter
			+ "RECEIPT FROM MYVEHICLEINSPECTIONCOMPANY\n"
			+ delimiter;
	private String typeSpecificPartOfReceipt;
	private final String lastPartOfReceipt = delimiter;
	
	/**
	 * Not implemented (we do not code alternate flows)
	 * @param cashPayment The cash payment that needs a receipt.
	 */
	public Receipt(CashPayment cashPayment) {}
	
	/**
	 * Creates a receipt for a <code>CreditCardPayment</code>
	 * @param ccPayment The credit card payment that needs a receipt.
	 */
	public Receipt(CreditCardPayment ccPayment) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime timePaid = LocalDateTime.now();
		int paidAmount = ccPayment.getPaidAmount();
		typeSpecificPartOfReceipt = "Customer has paid " + paidAmount + " by Credit Card at " +
									dtf.format(timePaid) + " for an Inspection.\n";
	}

	/**
	 * Returns a string containing the full receipt.
	 * @return The string to be printed or used in some other way.
	 */
	public String toString() {
		StringBuilder fullReceipt = new StringBuilder();
		fullReceipt.append(firstPartOfReceipt);
		fullReceipt.append(typeSpecificPartOfReceipt);
		fullReceipt.append(lastPartOfReceipt);
		return fullReceipt.toString();
	}
}