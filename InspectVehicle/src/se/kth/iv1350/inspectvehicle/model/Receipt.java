package se.kth.iv1350.inspectvehicle.model;

public class Receipt {
	
	public Receipt(Inspection inspection) {}
	
	public Receipt(CashPayment cashPayment) {}
	
	public Receipt(CreditCardPayment ccPayment) {}

	//noteself: skrivs ut i printer
	public String toString() {}
}
