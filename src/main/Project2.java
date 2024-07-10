package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 315 - 6980
 * Project: Project 2
 * Date: July 9th, 2024
 * Description: Project2.java defines the main class for the JavaFX application 
 * that reads a set of points from a file, displays them in a pane, and connects 
 * the maximal points.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class Project2 extends Application {

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
		ArrayList<Point> points = readPointsFromFile("points.txt");

		if (points == null) {
			System.err.println("Failed to read points from file.");
			return;
		}

		MaximalPointsPane pane = new MaximalPointsPane(points);
		Scene scene = new Scene(pane, 500, 500);

		primaryStage.setTitle("Maximal Points Visualizer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Reads points from the specified file.
	 *
	 * @param fileName the name of the file containing the points.
	 * @return an ArrayList of Point objects.
	 */
	public static ArrayList<Point> readPointsFromFile(String fileName) {
		ArrayList<Point> points = new ArrayList<>();
		String basePath = new File("").getAbsolutePath();
		String targetDirPath = basePath + File.separator + "docs";
		String filePath = targetDirPath + File.separator + "points.txt";

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
			return null;
		}

		return points;
	}

	public Scene createScene(ArrayList<Point> points) {
		try {
			MaximalPointsPane pane = new MaximalPointsPane(points);
			return new Scene(pane, 500, 500);
		} catch (Exception e) {
			System.out.println("Error creating scene: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
