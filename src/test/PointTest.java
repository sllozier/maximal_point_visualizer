package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 315 - 6980
 * Project: Project 2
 * Date: July 9th, 2024
 * Description: PointTest.java is a test class that verifies the correctness
 * of the Point class by running various test cases to ensure that the 
 * implementation of the Point class is accurate.
 */

import main.Point;

public class PointTest {

	/**
	 * The main method executes all test methods and reports their results.
	 *
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
		// Execute tests and report results
		boolean result1 = testConstructor();
		boolean result2 = testGetX();
		boolean result3 = testGetY();
		boolean result4 = testIsBelowAndLeftOf();
		boolean result5 = testCompareTo();

		// Output the results of each test
		System.out.println("PointTest results:");
		System.out.println("testConstructor: " + (result1 ? "Passed" : "Failed"));
		System.out.println("testGetX: " + (result2 ? "Passed" : "Failed"));
		System.out.println("testGetY: " + (result3 ? "Passed" : "Failed"));
		System.out.println("testIsBelowAndLeftOf: " + (result4 ? "Passed" : "Failed"));
		System.out.println("testCompareTo: " + (result5 ? "Passed" : "Failed"));
	}

	/**
	 * Tests the constructor of the Point class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testConstructor() {
		System.out.println("🧪 TEST 1 🧪");
		Point p = new Point(100.0, 200.0);
		boolean expectedOutput = true;
		boolean actualOutput = p.getX() == 100.0 && p.getY() == 200.0;
		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}

	/**
	 * Tests the getX method of the Point class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testGetX() {
		System.out.println("🧪 TEST 2 🧪");
		Point p = new Point(100.0, 200.0);
		double expectedOutput = 100.0;
		double actualOutput = p.getX();
		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}

	/**
	 * Tests the getY method of the Point class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testGetY() {
		System.out.println("🧪 TEST 3 🧪");
		Point p = new Point(100.0, 200.0);
		double expectedOutput = 200.0;
		double actualOutput = p.getY();
		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}

	/**
	 * Tests the isBelowAndLeftOf method of the Point class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testIsBelowAndLeftOf() {
		System.out.println("🧪 TEST 4 🧪");
		Point p1 = new Point(100.0, 200.0);
		Point p2 = new Point(150.0, 150.0);
		boolean expectedOutput = false;
		boolean actualOutput = p1.isBelowAndLeftOf(p2);
		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}

	/**
	 * Tests the compareTo method of the Point class.
	 *
	 * @return true if the test passes, false otherwise
	 */
	public static boolean testCompareTo() {
		System.out.println("🧪 TEST 5 🧪");
		Point p1 = new Point(100.0, 200.0);
		Point p2 = new Point(150.0, 150.0);
		int expectedOutput = -1;
		int actualOutput = p1.compareTo(p2);
		System.out.println("Expected output: " + expectedOutput + ", Actual output: " + actualOutput);
		return actualOutput == expectedOutput;
	}
}
