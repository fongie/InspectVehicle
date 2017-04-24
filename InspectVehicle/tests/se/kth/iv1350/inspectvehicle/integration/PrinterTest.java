package se.kth.iv1350.inspectvehicle.integration;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.YearMonth;
import java.util.ArrayList;

import org.junit.*;

import se.kth.iv1350.inspectvehicle.model.CreditCardPayment;
import se.kth.iv1350.inspectvehicle.model.PrintResults;
import se.kth.iv1350.inspectvehicle.model.Receipt;
import se.kth.iv1350.payauth.CreditCard;

public class PrinterTest {

	private Receipt receipt;
	private PrintResults printres;
	private ArrayList<String> inspPerformed;
	private ArrayList<String> inspResults;
	private Printer printer;
	private PrintResults printres2;
	private Receipt receipt2;
	private CreditCard card;

	@Before
	public void setUp() throws Exception {
		printer = new Printer();
		card = new CreditCard(0, "1234-5678-1234", "Fake Fakesson", YearMonth.of(2020, 10), 500);
		receipt = new Receipt(new CreditCardPayment(card, 500, printer));
		inspPerformed = new ArrayList<String>();
		inspResults = new ArrayList<String>();
		inspPerformed.add("lights");
		inspPerformed.add("engine");
		inspResults.add("pass");
		inspResults.add("fail");
		printres = new PrintResults(inspPerformed, inspResults, printer);
	}

	@After
	public void tearDown() throws Exception {
		printer = null;
		receipt = null;
		receipt2 = null;
		printres = null;
		printres2 = null;
		inspPerformed = null;
		inspResults = null;
		System.setOut(System.out);
	}

	@Test
	public void testPrintReceiptDifferentCards() {
		
		CreditCard card2 = new CreditCard(0, "1234-5678-1234", "Fake Fakesson", YearMonth.of(2020, 10), 500);
		receipt2 = new Receipt(new CreditCardPayment(card2, 500, printer));
		
		ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut));
		printer.print(receipt);
		
		ByteArrayOutputStream sysOut2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut2));
		printer.print(receipt2);

		boolean expResult = true;
		boolean result = sysOut.toString().equals(sysOut2.toString());
		assertEquals("Different credit card details yield different receipt printed from printer, but it should not.", expResult, result);
	}
	
	@Test
	public void testPrintReceiptDifferentAmounts() {
		receipt2 = new Receipt(new CreditCardPayment(card, 1000, printer));
		
		ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut));
		printer.print(receipt);
		
		ByteArrayOutputStream sysOut2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut2));
		printer.print(receipt2);

		boolean expResult = false;
		boolean result = sysOut.toString().equals(sysOut2.toString());
		assertEquals("Different amounts paid yield the same Receipt output from printer, should be different", expResult, result);
	}

	@Test
	public void testPrintPrintResultsSameResults() {
		printres2 = new PrintResults(inspPerformed, inspResults, printer);
		
		ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut));
		printer.print(printres);
		
		ByteArrayOutputStream sysOut2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut2));
		printer.print(printres2);

		boolean expResult = true;
		boolean result = sysOut.toString().equals(sysOut2.toString());
		assertEquals("Different results yielded equal printed resultssheets.", expResult, result);
	}
	
	@Test
	public void testPrintPrintResultsDifferentResults() {
		inspPerformed.add("steering");
		inspResults.add("fail");
		printres2 = new PrintResults(inspPerformed, inspResults, printer);
		
		ByteArrayOutputStream sysOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut));
		printer.print(printres);
		
		ByteArrayOutputStream sysOut2 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sysOut2));
		printer.print(printres2);

		boolean expResult = false;
		boolean result = sysOut.toString().equals(sysOut2.toString());
		assertEquals("Different results yielded equal printed resultssheets.", expResult, result);
	}

}