/*
 * Orel Mishan
 * 316551092
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @author Orel Mishan
 * rectangle repredented by upperLeft point his width and his heights
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    public static final int NUM_BORDERS = 4;

    /**
     * constructor.
     *
     * @param upperLeft point of the rectangle
     * @param width     of the rectangle
     * @param height    of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points.
     *
     * @param line to check if intersect the rectangle
     * @return list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        Line[] lines = new Line[NUM_BORDERS];
        //create lines that represent the rectangle vertices
        lines[0] = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        lines[1] = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());
        lines[2] = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        lines[3] = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        List<Point> list = new ArrayList<>();
        //check if the line intersect the vertices
        for (int i = 0; i < NUM_BORDERS; i++) {
            Point p = line.intersectionWith(lines[i]);
            if (p != null) {
                list.add(p);
            }
        }
        return list;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * check if point in the walls of the rectangle.
     *
     * @param p the point that check
     * @return true if the point in the walls and false if not
     */
    public boolean isAtWalls(Point p) {
        Line wall1 = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line wall2 = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return wall1.isIn(p) || wall2.isIn(p);

    }

    /**
     * @param collisionPoint to check if a corner at the this
     * @return true if the point is corner and false if not
     */
    public boolean isAtCorner(Point collisionPoint) {
        return collisionPoint.equals(this.upperLeft)
                || collisionPoint.equals(new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()))
                || collisionPoint.equals(new Point(this.upperLeft.getX() + this.width,
                                                   this.upperLeft.getY() + this.height))
                || collisionPoint.equals(new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height));
    }
}
