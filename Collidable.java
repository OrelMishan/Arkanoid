/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * Collides is objects that can be hit by the ball.
 */
public interface Collidable {
    /**
     * @return the rectangle that is the shape of the callable
     */
    Rectangle getCollisionRectangle();


    /**
     * check the direction hit and update the ball's velocity.
     *
     * @param hitter is the ball that hit
     * @param collisionPoint  is the point that the ball hit the callable
     * @param currentVelocity the current velocity of the ball that hit the callable
     * @return the update velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
