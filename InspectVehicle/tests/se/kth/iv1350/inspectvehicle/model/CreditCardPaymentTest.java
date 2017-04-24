package se.kth.iv1350.inspectvehicle.model;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.inspectvehicle.integration.Printer;
import se.kth.iv1350.payauth.CreditCard;

public class CreditCardPaymentTest {

	private Printer printer;
	private ByteArrayOutputStream sysOut;
	private ByteArrayOutputStream sysOut2;
	private int testCost;
	
	@Before
	public void setUp() throws Exception {
		printer = new Printer();
		sysOut = new ByteArrayOutputStream();
		sysOut2 = new ByteArrayOutputStream();
		testCost = 2000;
	}

	@After
	public void tearDown() throws Exception {
		printer = null;
		sysOut = null;
		sysOut2 = null;
		testCost = 0;
		System.setOut(System.out);
	}

	@Test
	public void testCreditCardPayment() {
		System.setOut(new PrintStream(sysOut));
		new CreditCardPayment(new CreditCard(0, "1234-5678-1234", "Fake Fakesson", YearMonth.of(2020, 10), 500), testCost, printer);
		
		System.setOut(new PrintStream(sysOut2));
		System.out.println(buildTestSheet());
		
		boolean expResult = true;
		boolean result = sysOut.toString().equals(sysOut2.toString());
		assertEquals("CreditCardPayment instantiation doesn't print string to sysout equal to the string expected", expResult, result);
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
