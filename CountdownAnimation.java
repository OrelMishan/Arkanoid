/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Orel Mishan
 * this class print countdown to start the level.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int draw;

    /**
     * constructor.
     *
     * @param numOfSeconds how second for the animation.
     * @param countFrom    from which number count
     * @param gameScreen   is the screen to draw the animation on him
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.draw = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        if (this.draw != countFrom) {
            sleeper.sleepFor((long) this.numOfSeconds * 1000 / countFrom);
        }
        gameScreen.drawAllOn(d);
        d.setColor(Color.MAGENTA);
        d.drawText((d.getWidth() / 2) - 20, (d.getHeight() / 2) - 20, "" + this.draw, 60);
        if (draw == 0) {
            this.stop = true;
        }
        draw--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
