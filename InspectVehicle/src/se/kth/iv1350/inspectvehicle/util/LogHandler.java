package se.kth.iv1350.inspectvehicle.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles the log
 * @author Max Körlinge
 *
 */
public class LogHandler {
	
	private static final String FILE_NAME = "inspectionLog.txt";
	private PrintWriter log;

	/**
	 * Constructs the LogHandler
	 * @throws IOException When fails to create logfile
	 */
	public LogHandler() throws IOException {
		log = new PrintWriter(new FileWriter(FILE_NAME), true);
	}
	
	/**
	 * When an exception is thrown, log the message and the stacktrace to the log.
	 * @param ex The exception thrown
	 */
	public void logException (Exception ex) {
		StringBuilder message = new StringBuilder();
		message.append(printTime());
		message.append(": thrown Exception: " + ex.getMessage());
		log.println(message);
		ex.printStackTrace(log);
	}
	
	private String printTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime currentTime = LocalDateTime.now();
		return dtf.format(currentTime);
	}
}
