/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Orel Mishan
 * PauseScreen is animation to run when the player want pause the game
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k is the keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}

