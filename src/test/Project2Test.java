package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 315 - 6980
 * Project: Project 2
 * Date: July 9th, 2024
 * Description: Project2Test.java contains tests to verify the functionality 
 * of the Project2 class including reading points from a file, scene creation, 
 * and pane initialization.
 */

import main.MaximalPointsPane;
import main.Point;
import main.Project2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project2Test extends Application {

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
		boolean result1 = testReadPointsFromFile();
		boolean result2 = testSceneCreation();
		boolean result3 = testPaneInitialization();

		// Output the results of each test
		System.out.println("Project2Test results:");
		System.out.println("testReadPointsFromFile: " + (result1 ? "Passed" : "Failed"));
		System.out.println("testSceneCreation: " + (result2 ? "Passed" : "Failed"));
		System.out.println("testPaneInitialization: " + (result3 ? "Passed" : "Failed"));

		// Close the application after tests
		primaryStage.close();
	}

	/**
	 * Tests the reading of points from the file.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testReadPointsFromFile() {
		System.out.println("🧪 TEST 1 🧪");
		// Define the path to the points.txt file
		String basePath = new File("").getAbsolutePath();
		String targetDirPath = basePath + File.separator + "docs";
		String filePath = targetDirPath + File.separator + "points.txt";

		ArrayList<Point> points = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] coordinates = line.split(" ");
				double x = Double.parseDouble(coordinates[0]);
				double y = Double.parseDouble(coordinates[1]);
				points.add(new Point(x, y));
			}
		} catch (IOException e) {
			System.err.println("File not found: " + filePath);
			e.printStackTrace();
			return false;
		}

		// Validate the points read from the file
		if (points.size() == 11) {
			System.out.println("Expected output: true, Actual output: true");
			return true;
		} else {
			System.out.println("Expected output: true, Actual output: false");
			return false;
		}
	}

	/**
	 * Tests the creation of the JavaFX scene.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testSceneCreation() {
		System.out.println("🧪 TEST 2 🧪");

		// Ensure the file path is correct
		String basePath = new File("").getAbsolutePath();
		String targetDirPath = basePath + File.separator + "docs";
		String filePath = targetDirPath + File.separator + "points.txt";

		ArrayList<Point> points = Project2.readPointsFromFile(filePath);
		if (points == null || points.isEmpty()) {
			System.out.println("Expected output: true, Actual output: false");
			return false;
		}

		boolean expectedOutput = true;
		boolean actualOutput = true;

		try {
			Project2 project2 = new Project2();
			Scene scene = project2.createScene(points);
			if (scene == null || !(scene instanceof Scene)) {
				System.out.println("Scene creation failed or returned null.");
				actualOutput = false;
			} else {
				System.out.println("Scene created successfully.");
			}
		} catch (Exception e) {
			System.out.println("Exception during scene creation: " + e.getMessage());
			e.printStackTrace();
			actualOutput = false;
		}

		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}

	/**
	 * Tests the initialization of the MaximalPointsPane.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testPaneInitialization() {
		System.out.println("🧪 TEST 3 🧪");
		boolean expectedOutput = true;
		boolean actualOutput = true;

		try {
			ArrayList<Point> points = new ArrayList<>();
			points.add(new Point(100.0, 100.0));
			points.add(new Point(200.0, 200.0));
			points.add(new Point(150.0, 150.0));
			MaximalPointsPane pane = new MaximalPointsPane(points);
			if (pane == null || !(pane instanceof MaximalPointsPane)) {
				actualOutput = false;
			}
		} catch (Exception e) {
			actualOutput = false;
		}

		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}
}
