package se.kth.iv1350.inspectvehicle.integration;

import static org.junit.Assert.*;

import org.junit.*;

import se.kth.iv1350.inspectvehicle.model.CashPayment;
import se.kth.iv1350.inspectvehicle.model.PrintResults;
import se.kth.iv1350.inspectvehicle.model.Receipt;

public class PrinterTest {

	//TODO when receipt and printedresults classes are finished
	private Receipt receipt;
	private PrintResults printres;
	private String[] inspPerformed = {"lights", "engine"};
	//TODO change to static enum in inspection?
	private String[] inspResults = {"pass", "fail"};
	private Printer printer;

	@Before
	public void setUp() throws Exception {
		printer = new Printer();
		receipt = new Receipt(new CashPayment(50,25));
		printres = new PrintResults(inspPerformed, inspResults, printer);
	}

	@After
	public void tearDown() throws Exception {
		printer = null;
		receipt = null;
		printres = null;
		inspPerformed = null;
		inspResults = null;
	}

	@Ignore("Contains no logic in dummy implementation")
	@Test
	public void testPrintReceipt() {
	}

	@Ignore("Contains no logic in dummy implementation")
	@Test
	public void testPrintPrintResults() {
	}

}