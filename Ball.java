/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Orel Mishan
 * ball has a point that present his center position, radius that present his size and color.
 * ball has velocity that present his speed and angle and screenSize that present were he can go
 */
public class Ball implements Sprite {
    private Point point;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity = null;
    private GameEnvironment gameEnvironment;


    /**
     * Constructor.
     *
     * @param center is the center of the ball
     * @param radius is the size of the ball
     * @param color  is the color of the ball
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.point = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * Constructor.
     *
     * @param x      the x coordinate of the center point
     * @param y      the y coordinate of the center point
     * @param radius is the size of the ball
     * @param color  is the color of the ball
     */
    public Ball(int x, int y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color);
    }


    /**
     * @return the center point of the ball
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * @return the x coordinate of the center point
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * @return the y coordinate of the center point
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * @return the size of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface is DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * change the position of the ball by his velocity.
     */
    public void moveOneStep() {
        //check the ball has velocity and gameEnvironment
        if (this.velocity == null || this.gameEnvironment == null) {
            return;
        }
        //check if the ball hit a collidable at the trajectory
        Line trajectory = new Line(this.getPoint(), new Point(this.getX()
                + (this.velocity.getDx() * 2), this.getY() + (this.velocity.getDy() * 2)));
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            //the ball hit callable
            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);
            trajectory = new Line(this.point, this.velocity.applyToPoint(this.point));
            collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
            if (collisionInfo != null) {
                this.velocity = collisionInfo.collisionObject().hit(
                        this, collisionInfo.collisionPoint(), this.velocity);
            }
        }
        this.point = this.velocity.applyToPoint(this.point);

    }


    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * get velocity to the ball.
     *
     * @param v is velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * get velocity to the ball.
     *
     * @param dx is change on x axis
     * @param dy is change on y axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * add to the ball the collection of the callables in the game.
     *
     * @param game is the collection of all the callables in the game
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.gameEnvironment = game;
    }

    /**
     * @param gameLevel has the sprites collection to add her the ball
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * @param gameLevel has the sprites collection and paddle to to remove from them the ball
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeBall(this);
    }
}



