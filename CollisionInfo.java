/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * CollisionInfo is an information about a collision.
 */
public class CollisionInfo {
    private Collidable collidableBlock;
    private Line trajectory;

    /**
     * constructor.
     *
     * @param collidableBlock is the block that the ball hit
     * @param trajectory      is the trajectory of the ball
     */
    public CollisionInfo(Collidable collidableBlock, Line trajectory) {
        this.collidableBlock = collidableBlock;
        this.trajectory = trajectory;
    }

    /**
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.trajectory.closestIntersectionToStartOfLine(this.collidableBlock.getCollisionRectangle());
    }

    /**
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collidableBlock;
    }
}
