/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * point repredented by x, y values on the axis
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param other the point that the method calculate the distance from this point
     * @return the distance between the points
     */
    public double distance(Point other) {
        //result get the distance between the points
        double result;
        result = Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2);
        result = Math.sqrt(result);
        return result;
    }

    /**
     * @param other the point that the method check the equals with this point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

}