package se.kth.iv1350.inspectvehicle.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CarRegistryTest {

	private CarRegistry carReg;
	@Before
	public void setUp() throws Exception {
		carReg = new CarRegistry();
	}

	@After
	public void tearDown() throws Exception {
		carReg = null;
	}

	@Test
	public void testDoesNrExist() {
		boolean result = carReg.doesNrExist("TEST123");
		assertTrue("doesNrExist does not find test car", result);
	}
}
