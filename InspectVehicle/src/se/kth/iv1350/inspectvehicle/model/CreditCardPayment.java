package se.kth.iv1350.inspectvehicle.model;

import se.kth.iv1350.inspectvehicle.integration.Printer;
import se.kth.iv1350.payauth.*;

/**
 * Represents a payment by credit card by the customer.
 * @author Max Körlinge
 *
 */
public class CreditCardPayment {
	private int cost;
	private Receipt receipt;
	
	/**
	 * Creates a credit card payment instance, which handles authorizing
	 * the payment and printing the receipt.
	 * 
	 * @param card The customer's credit card
	 * @param cost The cost of the inspection to be paid
	 * @param printer The class that handles the external printer
	 */
	public CreditCardPayment(CreditCard card, int cost, Printer printer) {
		this.cost = cost;
		
		PaymentAuthorization payAuth = new PaymentAuthorization();
		boolean approved = payAuth.authorizePayment(card, cost);
		
		if (approved) {
			receipt = new Receipt(this);
			printer.print(receipt);
		} else {
			//we dont code alternate flows
		}
	}
	
	/**
	 * Get the cost of this payment (used by Receipt class)
	 * @return The amount to be paid.
	 */
	public int getPaidAmount() {
		return cost;
	}
}
