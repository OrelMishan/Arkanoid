/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * point repredented by start and end points on the axis
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor.
     *
     * @param start the first point of the line
     * @param end   the second point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor.
     *
     * @param x1 the x value of the first point of the line
     * @param y1 the y value of the first point of the line
     * @param x2 the x value of the second point of the line
     * @param y2 the y value of the second point of the line
     *           send the values to constructor of point and after send the points to line constructor
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculate the slope of the line.
     *
     * @return the slope point of the line
     */
    public double getSlope() {
        double slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        if (slope == Double.NEGATIVE_INFINITY) {
            slope = Double.POSITIVE_INFINITY;
        }
        return slope;
    }

    /**
     * calculate the y coordinate of the straight cut the y axis.
     *
     * @return the y coordinate of the cut point
     */
    public double yAxisCut() {
        return this.start.getY() - (this.getSlope() * this.start.getX());
    }

    /**
     * @return the length of the line
     */
    public double length() {
        //calculate the distance between the stars and the end points
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        //calculate the x,y value of the middle point of the line
        double xMiddle;
        double yMiddle;
        xMiddle = (this.start.getX() + this.end.getX()) / 2.0;
        yMiddle = (this.start.getY() + this.end.getY()) / 2.0;
        return new Point(xMiddle, yMiddle);
    }

    /**
     * check if the lines have cutting point and calculate her.
     *
     * @param other the line that check if cut this line
     * @return point if the lines have cutting point and null if not
     */
    public Point intersectionWith(Line other) {
        if (this.getSlope() == other.getSlope()) {
            //the lines on parallel straights
            if (this.yAxisCut() != other.yAxisCut()) {
                return null;
            } else {
                //the lines on the same straight
                if (Double.isInfinite(this.yAxisCut()) && this.start.getX() != other.start.getX()) {
                    return null;
                } else if (this.isConvergeLine(other)
                        || (this.start.getY() > other.start.getY() && this.start.getY() > other.end.getY())
                        || (this.start.getY() < other.start.getY() && this.start.getY() < other.end.getY())) {
                    return null;
                } else {
                    if (this.start.equals(other.start) || this.start.equals(other.end)) {
                        return this.start;
                    } else {
                        return this.end;
                    }
                }
            }
            //calculate the cutting points of the Lines
        } else {
            double x = (other.yAxisCut() - this.yAxisCut()) / (this.getSlope() - other.getSlope());
            double y = (this.getSlope() * x) + this.yAxisCut();
            if (Double.isInfinite(this.yAxisCut())) {
                x = this.start.getX();
                y = other.getSlope() * x + other.yAxisCut();
            }
            if (Double.isInfinite(other.yAxisCut())) {
                x = other.start.getX();
                y = this.getSlope() * x + this.yAxisCut();
            }
            Point cuttingPoint = new Point(x, y);
            if (this.isIn(cuttingPoint) && other.isIn(cuttingPoint)) {
                return cuttingPoint;
            }
            return null;
        }

    }

    /**
     * check if lines that on the same straight are converge.
     *
     * @param other line that check if converge this line
     * @return true if the lines converge an false if not
     */
    public boolean isConvergeLine(Line other) {
        //the lines parallel to the y axis
        if (Double.isInfinite(this.getSlope())) {
            if ((this.start.getY() > other.start.getY() && this.start.getY() < other.end.getY())
                    || (this.start.getY() < other.start.getY() && this.start.getY() > other.end.getY())
                    || (this.end.getY() > other.start.getY() && this.end.getY() < other.end.getY())
                    || (this.end.getY() < other.start.getY() && this.end.getY() > other.end.getY())) {
                return true;
            }
        } else {
            if ((this.start.getX() > other.start.getX() && this.start.getX() < other.end.getX())
                    || (this.end.getX() > other.start.getX() && this.end.getX() < other.end.getX())
                    || (this.start.getX() < other.start.getX() && this.start.getX() > other.end.getX())
                    || (this.end.getX() < other.start.getX() && this.end.getX() > other.end.getX())) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param other the line that check if cut this line
     * @return true if the lines have cutting point and false if not
     */
    public boolean isIntersecting(Line other) {
        //call the method that get the cutting point and if she null the lines don't cutting
        Point convergeSegment = this.intersectionWith(other);
        if (convergeSegment == null) {
            return false;
        }
        return true;
    }


    /**
     * @param other the line that check if equal this line
     * @return true if the lines are equals and false if not
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                && (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * check if the line intersect with the rectangles.
     *
     * @param rect is the rectangles
     * @return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //get the intersection points between the line and the rectangles
        java.util.List<Point> list = rect.intersectionPoints(this);
        //check if the line and the rectangles intersection
        if (list.isEmpty()) {
            return null;
        }
        int closestIndex = 0;
        //get the closest intersection point
        for (int i = 1; i < list.size(); i++) {
            if (this.start.distance(list.get(i)) < this.start.distance(list.get(closestIndex))) {
                closestIndex = i;
            }
        }
        return list.get(closestIndex);
    }

    /**
     * check if point in the line.
     *
     * @param p is the point
     * @return trou if the point in the line and false if not
     */
    public boolean isIn(Point p) {
        //the line parallel to the y axis
        if (Double.isInfinite(this.getSlope())) {
            if (this.start.getX() == p.getX()) {
                return ((this.start.getY() <= p.getY() && this.end.getY() >= p.getY())
                        || (this.start.getY() >= p.getY() && this.end.getY() <= p.getY()));
            } else {
                return false;
            }
        }
        if (this.getSlope() * p.getX() + this.yAxisCut() == p.getY()) {
            return ((this.start.getX() >= p.getX() && this.end.getX() <= p.getX())
                    || (this.start.getX() <= p.getX() && this.end.getX() >= p.getX()));
        }
        return false;
    }
}
