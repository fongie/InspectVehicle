package se.kth.iv1350.inspectvehicle.integration;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class CarFromDatabaseTest {

	private CarFromDatabase testCar;
	private ArrayList<String> correctInspectionsNeeded;
	private ArrayList<String> correctInspectionsLog;

	@Before
	public void setUp() throws Exception {
		testCar = new CarFromDatabase("TEST123");
		correctInspectionsNeeded = new ArrayList<String>();
		correctInspectionsLog = new ArrayList<String>();
		correctInspectionsNeeded.add("test1");
		correctInspectionsNeeded.add("test2");
		correctInspectionsNeeded.add("test3");
		correctInspectionsLog.add("<logend>");
	}

	@After
	public void tearDown() throws Exception {
		testCar = null;
		correctInspectionsNeeded = null;
		correctInspectionsLog = null;
	}

	@Test
	public void testCarInDatabase() {
		boolean result = testCar.getInspectionsNeeded().equals(correctInspectionsNeeded);
		assertTrue("inspectionsNeeded fetched from testcar in database is not equal to expected list", result);
		
		result = testCar.getLog().equals(correctInspectionsLog);
		assertTrue("inspectionsLog fetched from testcar in database is not equal to expected log", result);
	}
	
	@Test
	public void testCarInDatabaseInspectionsNeededNotfound() {
		boolean dataHasBeenFetched = !(testCar.getInspectionsNeeded() == null);
		assertTrue("inspectionsNeeded fetched from database is null on existing testcar", dataHasBeenFetched);
	}

}
