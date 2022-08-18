/*
 * Orel Mishan
 * 316551092
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Orel Mishan
 * GameEnvironment is collection of all the callables in the game
 */
public class GameEnvironment {
    private List<Collidable> list = new ArrayList<>();

    /**
     * @return the list of the callables
     */
    public List<Collidable> getList() {
        return this.list;
    }

    /**
     * @param c is callable add to the environment
     */
    public void addCollidable(Collidable c) {
        this.list.add(c);

    }

    /**
     * @param c to remove from the list
     */
    public void removeCollidable(Collidable c) {
        this.list.remove(c);
    }

    /**
     * check if the ball hit callable un his trajectory and return information about the collision.
     *
     * @param trajectory of the ball
     * @return information about the collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collisionBlocks = new LinkedList<>();
        for (Collidable collidable : this.list) {
            if (!collidable.getCollisionRectangle().intersectionPoints(trajectory).isEmpty()) {
                collisionBlocks.add(collidable);
            }
        }
        if (collisionBlocks.isEmpty()) {
            return null;
        }
        Collidable closestCollidable = collisionBlocks.get(0);
        Point closest = trajectory.closestIntersectionToStartOfLine(collisionBlocks.get(0).getCollisionRectangle());
        for (Collidable collidable : collisionBlocks) {
            if (trajectory.start().distance(closest) < trajectory.start().distance(trajectory.
                    closestIntersectionToStartOfLine(collidable.getCollisionRectangle()))) {
                closestCollidable = collidable;
            }
        }
        return new CollisionInfo(closestCollidable, trajectory);

    }
}
