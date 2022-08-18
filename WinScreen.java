/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Orel Mishan
 * WinScreen is animation to run when the playyer win the game
 */
public class WinScreen implements Animation {
    private KeyboardSensor ks;
    private Counter score;
    private boolean stop;
    private int textSize;

    /**
     * constructor.
     *
     * @param ks    is the keyboard sensor
     * @param score is the count of the player's score
     */
    public WinScreen(KeyboardSensor ks, Counter score) {
        this.ks = ks;
        this.score = score;
        this.stop = false;
        this.textSize = 1;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.white);
        d.drawText(200, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), this.textSize);
        if (this.textSize <= 35) {
            this.textSize++;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
