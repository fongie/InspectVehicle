package se.kth.iv1350.inspectvehicle.model;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.inspectvehicle.integration.Printer;

public class PrintResultsTest {

	private ArrayList<String> testResults;
	private ArrayList<String> testItems;
	private PrintResults printResults;
	private Printer printer;
	private ByteArrayOutputStream sysOut;
	private ByteArrayOutputStream sysOut2;

	@Before
	public void setUp() throws Exception {

		testItems = new ArrayList<String>();
		testResults = new ArrayList<String>();
		testItems.add("tire");
		testResults.add("pass");
		testItems.add("brakes");
		testResults.add("fail");
		printer = new Printer();
		sysOut = new ByteArrayOutputStream();
		sysOut2 = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		testResults = null;
		
		testItems = null;
		printResults = null;
		printer = null;
		sysOut = null;
		sysOut2 = null;
		System.setOut(System.out);
	}
	
	@Test
	public void testPrintResults() {
	    System.setOut(new PrintStream(sysOut));
		new PrintResults(testItems, testResults, printer);
		
		System.setOut(new PrintStream(sysOut2));
		System.out.println(buildTestSheet());
		
		boolean expResult = true;
		boolean result = sysOut.toString().equals(sysOut2.toString());
		assertEquals("PrintResults instantiation doesn't print string equal to the string expected", expResult, result);
	}

	@Test
	public void testtoString() {
		printResults = new PrintResults(testItems, testResults, printer);
		boolean expResult = true;
		boolean result = buildTestSheet().equals(printResults.toString());
		assertEquals("PrintResults toString not equal to the expected String.", expResult,result);
	}
	
	private String buildTestSheet() {
		String delimiter = "---------------------------\n";
		String firstPartOfResultsSheet = delimiter
				+ "RESULTS SHEET FROM MYVEHICLEINSPECTIONCOMPANY\n"
				+ delimiter;
		String lastPartOfResultsSheet = delimiter;
		
		StringBuilder testString = new StringBuilder();
		testString.append(firstPartOfResultsSheet);		
		testString.append("TASK : RESULT\n\n");
		for (int i = 0; i < testItems.size(); i++) {
			testString.append(testItems.get(i));
			testString.append(" : ");
			testString.append(testResults.get(i));
			testString.append("\n");
		}
		testString.append(lastPartOfResultsSheet);
		return testString.toString();
	}
}
