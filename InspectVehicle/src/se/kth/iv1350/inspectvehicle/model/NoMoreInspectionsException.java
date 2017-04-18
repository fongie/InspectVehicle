package se.kth.iv1350.inspectvehicle.model;

public class NoMoreInspectionsException extends Exception {

	public NoMoreInspectionsException(String errormessage) {
		
		super(errormessage);
	}
}