package se.kth.iv1350.inspectvehicle.controller;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.inspectvehicle.integration.CarRegistry;
import se.kth.iv1350.inspectvehicle.integration.Printer;
import se.kth.iv1350.inspectvehicle.integration.RegistrationNumberNotFoundException;
import se.kth.iv1350.inspectvehicle.model.CashRegister;
import se.kth.iv1350.inspectvehicle.model.CreditCardPayment;
import se.kth.iv1350.inspectvehicle.model.Inspection;
import se.kth.iv1350.payauth.CreditCard;

public class ControllerTest {
	
	private Controller cntr;
	private Printer printer;
	private int testCost;
	
	@Before
	public void setUp() throws Exception {
		printer = new Printer();
		cntr = new Controller(new CarRegistry(), new CashRegister(), printer);
		testCost = 1500;
	}

	@After
	public void tearDown() throws Exception {
		printer = null;
		cntr = null;
		testCost = 0;
	}

	@Test
	public void testEnterRegNr() {
		try {
			int cost = cntr.enterRegNr("TEST123");
			int expectedCost = 1500; //if cost per task is 500
			assertEquals("enterRegNr returns wrong cost", cost, expectedCost);
		} catch (RegistrationNumberNotFoundException e) {
			fail("Cannot find regnr in database");
		}
	}

	@Test
	public void testPayByCC() {
		ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
		ByteArrayOutputStream sysOut2 = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(sysOut));
		new CreditCardPayment(new CreditCard(0, "1234-5678-1234", "Fake Fakesson", YearMonth.of(2020, 10), 500), testCost, printer);
		
		System.setOut(new PrintStream(sysOut2));
		System.out.println(buildTestSheet());
		
		System.setOut(System.out);
		
		boolean result = sysOut.toString().equals(sysOut2.toString());
		assertTrue("Printout after CCpayment by Controller not as expected", result);
	}

	@Test
	public void testWhatInspectNextFirstTask() {
		Controller cntr2 = new Controller(new CarRegistry(), new CashRegister(), printer);
		
		try {
			cntr2.enterRegNr("TEST123");
		} catch (RegistrationNumberNotFoundException e) {
			fail("Controller cannot find registration number for testcar in database");
		}
		
		try {
			Inspection insp = new Inspection("TEST123", printer);
			boolean result = cntr2.whatInspectNext().equals(insp.toInspectNext());
			assertTrue("Controller does not return first task to inspect equal to what Inspection class does", result);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Controller or Inspections inspectNext throw exception fetching first item on testcar");
		}
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