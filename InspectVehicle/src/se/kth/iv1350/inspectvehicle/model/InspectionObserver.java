package se.kth.iv1350.inspectvehicle.model;

/**
 * Listener interface for receiving notifications about an inspection
 * @author Max Körlinge
 */
public interface InspectionObserver {

	void newInspectionResult(String result);
		
}
