/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Orel Mishan
 * this class draw the name of the level on the screen
 */
public class NameLevel implements Sprite {
    private String name;

    /**
     * constructor.
     *
     * @param name is the neme of the level
     */
    public NameLevel(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(550, 20, this.name, 20);
    }

    @Override
    public void timePassed() {
        return;
    }
}
