/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Orel Mishan
 * GameOverScreem is animation of loose game
 */
public class GameOverScreen implements Animation {
    private KeyboardSensor ks;
    private Counter score;
    private boolean stop;
    private int textSize;

    /**
     * constructor.
     *
     * @param ks    is the keyboard sensor
     * @param score is the counter of score
     */
    public GameOverScreen(KeyboardSensor ks, Counter score) {
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
        d.drawText(200, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), this.textSize);
        if (this.textSize <= 35) {
            this.textSize++;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
