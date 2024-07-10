package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 315 - 6980
 * Project: Project 2
 * Date: July 9th, 2024
 * Description: GeneratePointsFile.java generates a .txt file with random points 
 * with single decimal values.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneratePointsFile {

	public static void generateRandomPointsFile(String fileName, int numberOfPoints) {
		FileWriter fileWriter = null;

		if (numberOfPoints <= 1) {
			System.err.println("Invalid number of points. File not created.");
			return;
		}

		try {

			fileWriter = new FileWriter(fileName);

			Random random = new Random();

			for (int i = 0; i < numberOfPoints; i++) {
				double x = Math.round(random.nextDouble() * 5000) / 10.0;
				double y = Math.round(random.nextDouble() * 5000) / 10.0;
				// Ensure x and y are within the 500x500 range
				x = Math.min(x, 499.9);
				y = Math.min(y, 499.9);
				fileWriter.write(x + " " + y + "\n");
			}
			System.out.println("Points data generated successfully in '" + fileName + "'");
		} catch (IOException e) {
			System.err.println("Failed to write data to file: " + fileName);
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				System.err.println("Failed to close the file writer.");
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// Get the directory of the root project directory
		String basePath = new File("").getAbsolutePath();

		// Define the path to the
		String targetDirPath = basePath + File.separator + "docs";

		// Ensure the directory exists
		File targetDir = new File(targetDirPath);
		if (!targetDir.exists()) {
			targetDir.mkdirs(); // Create the directory if it does not exist
		}

		// Define the path to the points.txt file within the
		// directory
		String filePath = targetDirPath + File.separator + "points.txt";

		// Generate points data and save it to the specified file
		generateRandomPointsFile(filePath, 11);
	}
}
