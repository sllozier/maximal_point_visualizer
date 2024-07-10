package test;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 315 - 6980
 * Project: Project 2
 * Date: July 9th, 2024
 * Description: TestRunner.java is a utility class designed to systematically
 * execute and report
 * on a series of unit tests covering the functionality of the
 * Project2Test, MaximalPointsPaneTest and PointTest
 * classes. It assesses various aspects of each class,
 * providing a structured and
 * automated approach to validating the system's correctness and robustness.
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class TestRunner extends Application {
    // ANSI escape codes for text colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * Launches the JavaFX application to run the tests.
     * 
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * Initializes the JavaFX stage and runs all test suites, displaying their
     * results.
     * It overrides the JavaFX Application's start method.
     * 
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Suppress standard error
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(new ByteArrayOutputStream()));
        // Header for the test output
        System.out.println("-------------------------------------------------------");
        System.out.println(" T E S T S");
        System.out.println("-------------------------------------------------------");
        System.out.println("|");

        // Restore standard error
        System.setErr(originalErr);

        // Call test methods from different test classes
        runProject2Test();
        runMaximalPointsPaneTest();
        runPointTest();

        // Footer to indicate completion
        System.out.println("\nAll tests completed.");
        // Close the JavaFX application
        primaryStage.close();
        // Terminate the JavaFX application thread
        Platform.exit();
    }

    /**
     * Executes and reports on tests for the Project2Test class. It runs the
     * following tests: testReadPointsFromFile, testSceneCreation, and
     * testPaneInitialization. The results of each test are displayed with
     * execution times.
     */
    private static void runProject2Test() {
        System.out.println("|+-- Project2Test");

        // Execute and display results for Project2 specific tests
        boolean result1 = Project2Test.testReadPointsFromFile();
        boolean result2 = Project2Test.testSceneCreation();
        boolean result3 = Project2Test.testPaneInitialization();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayResult("TEST 1: testReadPointsFromFile", result1);
        displayResult("TEST 2: testSceneCreation", result2);
        displayResult("TEST 3: testPaneInitialization", result3);

        System.out.println("|");
    }

    /**
     * Executes and reports on tests for the MaximalPointsPane class. It runs the
     * following
     * tests: testConstructor, testAddPoint,
     * testRemovePoint,
     * and testMaximalPointsCalculation. The results of each test are displayed with
     * execution times.
     */
    private static void runMaximalPointsPaneTest() {
        System.out.println("|+-- MaximalPointsPaneTest");

        // Execute and display results for Graduate specific tests
        boolean result1 = MaximalPointsPaneTest.testConstructor();
        boolean result2 = MaximalPointsPaneTest.testAddPoint();
        boolean result3 = MaximalPointsPaneTest.testRemovePoint();
        boolean result4 = MaximalPointsPaneTest.testMaximalPointsCalculation();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayResult("TEST 1: testConstructor", result1);
        displayResult("TEST 2: testAddPoint", result2);
        displayResult("TEST 3: testRemovePoint", result3);
        displayResult("TEST 4: testMaximalPointsCalculation", result4);

        System.out.println("|");
    }

    /**
     * Executes and reports on tests for the Point class. It runs the
     * following tests: testConstructor, testGetX,
     * testGetY, testIsBelowAndLeftOf, and testCompareTo. The
     * results of each test are displayed with execution times.
     */
    private static void runPointTest() {
        System.out.println("|+-- PointTest");

        // Execute and display results for Graduate specific tests
        boolean result1 = PointTest.testConstructor();
        boolean result2 = PointTest.testGetX();
        boolean result3 = PointTest.testGetY();
        boolean result4 = PointTest.testIsBelowAndLeftOf();
        boolean result5 = PointTest.testCompareTo();

        // Report on each test's execution time and result
        System.out.println("|");

        // Calculate and display execution times
        displayResult("TEST 1: testConstructor", result1);
        displayResult("TEST 2: testGetX", result2);
        displayResult("TEST 3: testGetY", result3);
        displayResult("TEST 4: testIsBelowAndLeftOf", result4);
        displayResult("TEST 5: testCompareTo", result5);

        System.out.println("|");
    }

    /**
     * Displays the result of a test in a formatted manner, including the test name
     * and whether it passed or failed.
     * 
     * @param testName The name of the test.
     * @param result   The result of the test (true if passed, false if failed).
     */
    private static void displayResult(String testName, boolean result) {
        String resultString = (result ? ANSI_GREEN + "[OK]" : ANSI_RED + "[FAILED]") + ANSI_RESET;
        System.out.printf("| +-- %s: %s\n", testName, resultString);
    }

}
