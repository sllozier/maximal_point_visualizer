package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 315 - 6980
 * Project: Project 2
 * Date: July 9th, 2024
 * Description: MaximalPointsPaneTest.java is a test class that verifies the correctness
 * of the MaximalPointsPane class by running various test cases to ensure that the 
 * implementation is accurate.
 */

import main.MaximalPointsPane;
import main.Point;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MaximalPointsPaneTest extends Application {

	/**
	 * The main method launches the JavaFX application.
	 *
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Execute tests and report results
		boolean result1 = testConstructor();
		boolean result2 = testAddPoint();
		boolean result3 = testRemovePoint();
		boolean result4 = testMaximalPointsCalculation();

		// Output the results of each test
		System.out.println("MaximalPointsPaneTest results:");
		System.out.println("testConstructor: " + (result1 ? "Passed" : "Failed"));
		System.out.println("testAddPoint: " + (result2 ? "Passed" : "Failed"));
		System.out.println("testRemovePoint: " + (result3 ? "Passed" : "Failed"));
		System.out.println("testMaximalPointsCalculation: " + (result4 ? "Passed" : "Failed"));

		// Close the application after tests
		primaryStage.close();
	}

	/**
	 * Tests the constructor of the MaximalPointsPane class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testConstructor() {
		System.out.println("🧪 TEST 1 🧪");
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(100.0, 100.0));
		points.add(new Point(200.0, 200.0));
		MaximalPointsPane pane = new MaximalPointsPane(points);

		boolean expectedOutput = true;
		boolean actualOutput = (pane != null);

		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}

	/**
	 * Tests adding a point to the MaximalPointsPane class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testAddPoint() {
		System.out.println("🧪 TEST 2 🧪");
		ArrayList<Point> points = new ArrayList<>();
		MaximalPointsPane pane = new MaximalPointsPane(points);
		pane.addPoint(new Point(150.0, 150.0));

		boolean expectedOutput = true;
		boolean actualOutput = (pane.getPoints().size() == 1);

		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}

	/**
	 * Tests removing a point from the MaximalPointsPane class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testRemovePoint() {
		System.out.println("🧪 TEST 3 🧪");
		ArrayList<Point> points = new ArrayList<>();
		Point pointToRemove = new Point(200.0, 200.0);
		points.add(pointToRemove);
		MaximalPointsPane pane = new MaximalPointsPane(points);
		pane.removePoint(pointToRemove);

		boolean expectedOutput = true;
		boolean actualOutput = (pane.getPoints().size() == 0);

		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}

	/**
	 * Tests the maximal points calculation of the MaximalPointsPane class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testMaximalPointsCalculation() {
		System.out.println("🧪 TEST 4 🧪");
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(100.0, 100.0));
		points.add(new Point(200.0, 200.0));
		points.add(new Point(150.0, 150.0));
		MaximalPointsPane pane = new MaximalPointsPane(points);
		pane.calculateMaximalPoints();

		// Assuming that maximal points for the given set are (200.0, 200.0)
		ArrayList<Point> maximalPoints = pane.getMaximalPoints();

		// Adjusting the expected output to match the set points
		ArrayList<Point> expectedMaximalPoints = new ArrayList<>();
		expectedMaximalPoints.add(new Point(200.0, 200.0));

		boolean expectedOutput = true;
		boolean actualOutput = maximalPoints.equals(expectedMaximalPoints);

		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}
}
