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
import javafx.scene.text.Text;
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
			boolean isMaximal = false;
			for (Point q : points) {
				if (q.isBelowAndLeftOf(p)) {
					isMaximal = true;
					break;
				}
			}
			if (!isMaximal) {
				maximalPoints.add(p);
			}
		}
		Collections.sort(maximalPoints);
	}

	/**
	 * Draws lines connecting the maximal points and displays all points with
	 * coordinates.
	 */
	private void drawLines() {
		getChildren().clear();

		// Draw points with hover functionality
		for (Point p : points) {
			Circle circle = new Circle(p.getX(), p.getY(), 5, maximalPoints.contains(p) ? Color.GREEN : Color.BLACK);
			Text text = new Text(p.getX() + ", " + p.getY());
			text.setVisible(false);
			// Adjust text position based on the coordinates
			if (p.getX() > 440 && p.getY() < 60) {
				text.setX(p.getX() - text.getLayoutBounds().getWidth() - 7);
				text.setY(p.getY() + 17);
			} else if (p.getX() < 60 && p.getY() < 60) {
				text.setX(p.getX() + 7);
				text.setY(p.getY() + 17);
			} else if (p.getX() < 60 && p.getY() > 440) {
				text.setX(p.getX() + 7);
				text.setY(p.getY() - 7);
			} else if (p.getY() < 60) {
				text.setX(p.getX() + 7);
				text.setY(p.getY() + 17);
			} else if (p.getX() > 440) {
				text.setX(p.getX() - text.getLayoutBounds().getWidth() - 7);
				text.setY(p.getY() - 7);
			} else {
				text.setX(p.getX() + 7);
				text.setY(p.getY() - 7);
			}

			circle.setOnMouseEntered(e -> {
				circle.setFill(Color.RED);
				text.setVisible(true);
			});
			circle.setOnMouseExited(e -> {
				circle.setFill(maximalPoints.contains(p) ? Color.GREEN : Color.BLACK);
				text.setVisible(false);
			});

			getChildren().add(circle);
			getChildren().add(text);
		}

		if (maximalPoints.size() > 0) {
			Point firstPoint = maximalPoints.get(0);

			// Draw line from (0, firstPoint.y) to firstPoint
			Line line1 = new Line(0, firstPoint.getY(), firstPoint.getX(), firstPoint.getY());
			line1.setStroke(Color.BLUE);
			line1.setStrokeWidth(2.0);
			getChildren().add(line1);

			for (int i = 0; i < maximalPoints.size() - 1; i++) {
				Point p1 = maximalPoints.get(i);
				Point p2 = maximalPoints.get(i + 1);

				// Draw vertical line from p1 to (p1.x, p2.y)
				Line verticalLine = new Line(p1.getX(), p1.getY(), p1.getX(), p2.getY());
				verticalLine.setStroke(Color.BLUE);
				verticalLine.setStrokeWidth(2.0);
				getChildren().add(verticalLine);

				// Draw horizontal line from (p1.x, p2.y) to p2
				Line horizontalLine = new Line(p1.getX(), p2.getY(), p2.getX(), p2.getY());
				horizontalLine.setStroke(Color.BLUE);
				horizontalLine.setStrokeWidth(2.0);
				getChildren().add(horizontalLine);
			}

			Point lastPoint = maximalPoints.get(maximalPoints.size() - 1);

			// Draw vertical line from lastPoint to (lastPoint.x, getPrefHeight())
			Line line2 = new Line(lastPoint.getX(), lastPoint.getY(), lastPoint.getX(), getPrefHeight());
			line2.setStroke(Color.BLUE);
			line2.setStrokeWidth(2.0);
			getChildren().add(line2);
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
