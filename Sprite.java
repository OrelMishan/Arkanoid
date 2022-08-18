/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;

/**
 * Sprite is objects that show at the game.
 */
public interface Sprite {
    /**
     * draw the sprite on the screen.
     *
     * @param d if a surface to draw
     */
    void drawOn(DrawSurface d);

    /**
     *  notify the sprite that time has passed.
     */
    void timePassed();
}