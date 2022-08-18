/*
 * Orel Mishan
 * 316551092
 */

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * @author Orel Mishan
 * spriteCollection is collection of all the sprites in the game.
 */
public class SpriteCollection {
    private List<Sprite> list = new ArrayList<>();

    /**
     * @param s is sprite that add to the collection
     */
    public void addSprite(Sprite s) {
        list.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(this.list);
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d is surface to draw with him
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : list) {
            sprite.drawOn(d);
        }
    }

    /**
     * @param s to remove from list
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }
}




