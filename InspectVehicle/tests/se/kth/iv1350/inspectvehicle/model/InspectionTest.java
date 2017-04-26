package se.kth.iv1350.inspectvehicle.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import se.kth.iv1350.inspectvehicle.integration.Printer;

public class InspectionTest {

	private Inspection testInspection;
	
	@Before
	public void setUp() throws Exception {
		testInspection = new Inspection("TEST123", new Printer());
	}

	@After
	public void tearDown() throws Exception {
		testInspection = null;
	}

	@Test
	public void testToInspectNextExistingItem() {
		try {
			boolean result = testInspection.toInspectNext().equals("test1");
			assertTrue("First item in list toInspectNext not equal to expected from testcar", result);
		} catch (Exception e) {}
	}
	
	@Test
	public void testhasMoreInspectionsToMake() {
		boolean result = testInspection.hasMoreInspectionsToMake();
		assertTrue("hasMoreInspectionsToMake return false on first item", result);
	}
	
	@Ignore("This exception was removed from the program")
	@Test
	public void testToInspextNoMoreInspections() {
		for(int i = 0; i < 4; i++) {
			try {
				testInspection.toInspectNext();
			} catch (Exception e) {
				//boolean result = e instanceof NoMoreInspectionsException;
				//assertTrue("Does not throw NoMoreInspectionsException when all inspections are done", result);
				return;
			}
		}
		fail("Does not throw NoMoreInspectionsException when all inspections are made.");
	}
}