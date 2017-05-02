package se.kth.iv1350.inspectvehicle.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import se.kth.iv1350.inspectvehicle.model.InspectionObserver;

/**
 * Represents a display that shows the total amount of failed and passed inspections since
 * the program started.
 * @author Max Körlinge
 *
 */
class InspectionStatsView extends JFrame implements InspectionObserver {

	private int numberOfPassedInspections;
	private int numberOfFailedInspections;
	private JLabel passedLabel;
	private JLabel failedLabel;
	private String passedInspectionsText = "Total passed inspections: ";
	private String failedInspectionsText = "Total failed inspections: ";
	
	/**
	 * Creates and displays the display at program start. Does not remember data
	 * from last time it was run.
	 */
	InspectionStatsView() {
		numberOfPassedInspections = 0;
		numberOfFailedInspections = 0;
		
		setUpWindow();
	}

	@Override
	public void newInspectionResult(String result) {
		if (result.equals("pass")) {
			numberOfPassedInspections++;
			printCurrentState();
			updateLabels();
		} else {
			numberOfFailedInspections++;
			printCurrentState();
			updateLabels();
		}
	}
	
	private void printCurrentState() {
		System.out.println("Total passed inspection tasks since program started: " + numberOfPassedInspections);
		System.out.println("Total failed inspection tasks since program started: " + numberOfFailedInspections);
	}
	
	private void setUpWindow() {
		setTitle("InspectionStatsDisplay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.BLACK);
		this.getContentPane().setLayout(new FlowLayout());
		
		passedLabel = new JLabel("", SwingConstants.CENTER);
		passedLabel.setForeground(Color.WHITE);
		this.getContentPane().add(passedLabel);

		
		failedLabel = new JLabel("", SwingConstants.CENTER);
		failedLabel.setForeground(Color.WHITE);
		this.getContentPane().add(failedLabel);
		
		updateLabels();

		this.setBounds(600,0,200,80);
		this.setVisible(true);
	}
	
	private void updateLabels() {
		passedLabel.setText(passedInspectionsText + numberOfPassedInspections); 
		failedLabel.setText(failedInspectionsText + numberOfFailedInspections);
	}
}