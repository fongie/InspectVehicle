package se.kth.iv1350.inspectvehicle.integration;

import static org.junit.Assert.*;

import org.junit.*;

public class CarInDatabaseTest {

	private CarInDatabase testCar;

	@Before
	public void setUp() throws Exception {
		testCar = new CarInDatabase("ABC123");
	}

	@After
	public void tearDown() throws Exception {
		testCar = null;
	}

	@Test
	public void testCarInDatabase() {
		boolean dataHasBeenFetched = !(testCar.getInspectionsNeeded() == null);
		assertTrue("inspectionsNeeded fetched from database is null", dataHasBeenFetched);
	}

	@Ignore("Not yet implemented in the code")
	@Test
	public void testGetInspectionsNeeded() {
	}

	@Ignore("Not yet implemented")
	@Test
	public void testUpdateDatabase() {
	}

}
