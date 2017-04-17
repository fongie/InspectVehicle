package se.kth.iv1350.inspectvehicle.model;

/**
 * Represents a payment made in cash.
 * Not implemented due to instructed not to implement alternate flows
 * 
 * @author Max Körlinge
 *
 */
public class CashPayment {

	private int costToBePaid;
	private int amountPaid;
	private Receipt receipt;

	/**
	 * Creates instance. Dummy implementation, do not use
	 * @param amountPaid The amount paid by the customer
	 * @param costToBePaid The cost of the inspection
	 */
	public CashPayment(int amountPaid, int costToBePaid) {}
	
}
