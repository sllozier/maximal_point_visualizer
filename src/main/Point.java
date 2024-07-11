package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 315 - 6980
 * Project: Project 2
 * Date: July 9th, 2024
 * Description: Point.java represents a point in a 2D space with x and y
 * coordinates. This class is immutable and implements the Comparable interface.
 */

public class Point implements Comparable<Point> {
	private final double x;
	private final double y;

	/**
	 * Constructor that initializes the x and y coordinates of the point.
	 *
	 * @param x The x coordinate of the point.
	 * @param y The y coordinate of the point.
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x coordinate of the point.
	 *
	 * @return The x coordinate.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of the point.
	 *
	 * @return The y coordinate.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Determines if the specified point is below and to the left of this point.
	 *
	 * @param other The other point to compare with.
	 * @return true if the specified point is below and to the left of this point,
	 *         false otherwise.
	 */
	public boolean isBelowAndLeftOf(Point other) {
		boolean result = other.x < this.x && other.y > this.y;
		return result;
	}

	/**
	 * Compares this point with the specified point for order based on the x
	 * coordinate.
	 *
	 * @param other The other point to compare with.
	 * @return A negative integer, zero, or a positive integer as this point is less
	 *         than, equal to,
	 *         or greater than the specified point.
	 */
	@Override
	public int compareTo(Point other) {
		return Double.compare(this.x, other.x);
	}

	/**
	 * Checks if this point is equal to another point.
	 *
	 * @param obj The object to compare.
	 * @return True if the points are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Point point = (Point) obj;
		return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
	}

	/**
	 * Returns the hash code value for this point.
	 *
	 * @return The hash code value.
	 */
	@Override
	public int hashCode() {
		return java.util.Objects.hash(x, y);
	}

	/**
	 * Returns a string representation of the point.
	 *
	 * @return The string representation.
	 */
	@Override
	public String toString() {
		return "Point{" + "x=" + x + ", y=" + y + '}';
	}
}
