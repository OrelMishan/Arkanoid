/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx the change of x axes
     * @param dy the change of y axes
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * calculate the change in position on the `x` and the `y` axes from angle and speed.
     *
     * @param angle the direction that the ball move
     * @param speed the speed of the ball
     * @return Velocity with dx and dy values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //calculate change in position on the `x` and the `y` axes
        angle -= 90;
        angle = Math.toRadians(angle);
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        //call constructor with the values
        return new Velocity(dx, dy);
    }

    /**
     * @return the change in position on the 'x' axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the change in position on the 'y' axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * @param newDx change dx value
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * @param newDy change dy value
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point that change her position
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}