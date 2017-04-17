package se.kth.iv1350.inspectvehicle.model;

import se.kth.iv1350.payauth.*;

public class CreditCardPayment {
	private int cost;
	private Receipt receipt;
	
	public CreditCardPayment(CreditCard card, int cost) {
		
		PaymentAuthorization payAuth = new PaymentAuthorization();
		boolean approved = payAuth.authorizePayment(card, cost);
		
		if (approved) {
			receipt = new Receipt(this);

			//TODO HÄR ÄR JAG, SKA JAG GÖRA PRINTERN STATIC?
			
		} else {
			//we dont code alternate flows
		}
		
	}
	
	public int getPaidAmount() {
		return cost;
	}
	
}
