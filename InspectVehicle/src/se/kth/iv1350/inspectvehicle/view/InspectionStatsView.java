package se.kth.iv1350.inspectvehicle.view;

import se.kth.iv1350.inspectvehicle.model.InspectionObserver;

/**
 * Represents a display that shows the total amount of failed and passed inspections since
 * the program started.
 * @author Max Körlinge
 *
 */
class InspectionStatsView implements InspectionObserver {

	private int numberOfPassedInspections;
	private int numberOfFailedInspections;
	
	InspectionStatsView() {
		numberOfPassedInspections = 0;
		numberOfFailedInspections = 0;
	}

	@Override
	public void newInspectionResult(String result) {
		if (result.equals("pass")) {
			numberOfPassedInspections++;
			printCurrentState();
		} else {
			numberOfFailedInspections++;
			printCurrentState();
		}
	}
	
	private void printCurrentState() {
		System.out.println("Total passed inspection tasks since program started: " + numberOfPassedInspections);
		System.out.println("Total failed inspection tasks since program started: " + numberOfFailedInspections);
	}
}