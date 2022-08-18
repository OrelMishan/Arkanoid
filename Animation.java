/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;

/**
 * @author Orel Mishan
 * animation is classes that has information about the animations
 */
public interface Animation {
    /**
     * do on step of the animation.
     *
     * @param d is the drawSurface that draw on the screen
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return true if the animation need to stop and false if not
     */
    boolean shouldStop();

}
