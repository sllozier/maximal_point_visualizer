package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 315 - 6980
 * Project: Project 2
 * Date: July 9th, 2024
 * Description: MaximalPointsPane.java is a JavaFX Pane that manages a set of
 * points
 * and draws the lines connecting the maximal points.
 */

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

public class MaximalPointsPane extends Pane {
	private ArrayList<Point> points;
	private ArrayList<Point> maximalPoints;

	/**
	 * Constructor that initializes the pane with a given set of points.
	 *
	 * @param points The initial set of points.
	 */
	public MaximalPointsPane(ArrayList<Point> points) {
		this.points = points;
		this.maximalPoints = new ArrayList<>();
		setPrefSize(500, 500);
		calculateMaximalPoints();
		drawLines();
		setOnMouseClicked(new MouseClickHandler());
	}

	/**
	 * Returns the current set of points.
	 *
	 * @return The list of points.
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * Returns the current set of maximal points.
	 *
	 * @return The list of maximal points.
	 */
	public ArrayList<Point> getMaximalPoints() {
		return maximalPoints;
	}

	/**
	 * Adds a point to the pane and recalculates the maximal points.
	 *
	 * @param point The point to add.
	 */
	public void addPoint(Point point) {
		points.add(point);
		calculateMaximalPoints();
		drawLines();
	}

	/**
	 * Removes a point from the pane and recalculates the maximal points.
	 *
	 * @param point The point to remove.
	 */
	public void removePoint(Point point) {
		points.remove(point);
		calculateMaximalPoints();
		drawLines();
	}

	/**
	 * Calculates the maximal points and updates the list.
	 */
	public void calculateMaximalPoints() {
		maximalPoints.clear();

		for (Point p : points) {
			System.out.println("POINT P: " + p);
			boolean isMaximal = false;
			for (Point q : points) {
				System.out.println("POINT Q: " + q);
				if (q.isBelowAndLeftOf(p)) {
					isMaximal = true;
					break;
				}
			}
			if (isMaximal) {
				maximalPoints.add(p);
			}
		}
		Collections.sort(maximalPoints);
		System.out.println("Maximal points: " + maximalPoints);
	}

	/**
	 * Draws lines connecting the maximal points.
	 */
	private void drawLines() {
		getChildren().clear();
		// Draw points
		for (Point p : points) {
			Circle circle = new Circle(p.getX(), p.getY(), 3, Color.BLACK);
			getChildren().add(circle);
		}

		// Debugging: Print maximal points
		System.out.println("Maximal points:");
		for (Point p : maximalPoints) {
			System.out.println(p);
		}

		// Draw lines connecting maximal points
		for (int i = 0; i < maximalPoints.size() - 1; i++) {
			Point p1 = maximalPoints.get(i);
			Point p2 = maximalPoints.get(i + 1);
			Line line = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			getChildren().add(line);
		}
	}

	/**
	 * Handles mouse click events to add or remove points.
	 */
	private class MouseClickHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			if (event.getButton() == MouseButton.PRIMARY) {
				addPoint(new Point(event.getX(), event.getY()));
			} else if (event.getButton() == MouseButton.SECONDARY) {
				Point toRemove = null;
				for (Point p : points) {
					if (Math.abs(p.getX() - event.getX()) < 5 && Math.abs(p.getY() - event.getY()) < 5) {
						toRemove = p;
						break;
					}
				}
				if (toRemove != null) {
					removePoint(toRemove);
				}
			}
		}
	}
}
