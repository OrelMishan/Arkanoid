/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * HitListener is object that listening to notifier
 */

public interface HitListener {
    /**
     * update the data that hitting happened.
     *
     * @param beingHit is the block that be hit
     * @param hitter   is the ball that hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}
