package se.kth.iv1350.inspectvehicle.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.inspectvehicle.integration.Printer;
import se.kth.iv1350.payauth.CreditCard;

public class ReceiptTest {

	private Receipt testReceipt;
	private Printer printer;
	private CreditCardPayment ccPayment;
	private int testCost;

	@Before
	public void setUp() throws Exception {
		printer = new Printer();
		testCost = 1000;
		ccPayment = new CreditCardPayment(new CreditCard(0, "1234-5678-1234", "Fake Fakesson", YearMonth.of(2020, 10), 500), testCost, printer);
	}

	@After
	public void tearDown() throws Exception {
		testReceipt = null;
		printer = null;
		testCost = 0;
		ccPayment = null;
	}
	

	@Test
	public void testtoString() {
		testReceipt = new Receipt(ccPayment);
		boolean expResult = true;
		boolean result = buildTestSheet().equals(testReceipt.toString());
		assertEquals("Receipt's toString not equal to the expected String.", expResult,result);
	}
	
	private String buildTestSheet() {
		String delimiter = "=======================\n";
		String firstPartOfReceipt = delimiter
				+ "RECEIPT FROM MYVEHICLEINSPECTIONCOMPANY\n"
				+ delimiter;
		String lastPartOfReceipt = delimiter;
		
		String middlePartOfReceipt;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime timePaid = LocalDateTime.now();
		middlePartOfReceipt = "Customer has paid " + testCost + " by Credit Card at " +
				dtf.format(timePaid) + " for an Inspection.\n";

		StringBuilder testString = new StringBuilder();
		testString.append(firstPartOfReceipt);		
		testString.append(middlePartOfReceipt);
		testString.append(lastPartOfReceipt);
		return testString.toString();
	}

}
