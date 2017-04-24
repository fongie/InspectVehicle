package se.kth.iv1350.inspectvehicle.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import se.kth.iv1350.inspectvehicle.integration.CarRegistry;
import se.kth.iv1350.inspectvehicle.integration.Printer;
import se.kth.iv1350.inspectvehicle.model.CashRegister;

public class ControllerTest {
	
	private Controller cntr;

	@Before
	public void setUp() throws Exception {
		cntr = new Controller(new CarRegistry(), new CashRegister(), new Printer());
	}

	@After
	public void tearDown() throws Exception {
		cntr = null;
	}

	@Test
	public void testEnterRegNr() {
		int cost = cntr.enterRegNr("TEST123");
		int expectedCost = 1500; //if cost per task is 500
		assertEquals("enterRegNr returns wrong cost", cost, expectedCost);
	}

	@Ignore
	@Test
	public void testPayByCC() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testWhatInspectNext() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testEnterResultOfInspection() {
		fail("Not yet implemented");
	}
}