package se.kth.iv1350.inspectvehicle.model;

/**
 * Thrown by toInspectNext method in Inspection when you attempt to fetch a new inspection to make, but there are none.
 * @author Max Körlinge
 *
 */
public class NoMoreInspectionsException extends Exception {

	public NoMoreInspectionsException(String errormessage) {
		
		super(errormessage);
	}
}