/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Orel Mishan
 * indicator that count the score and the blocks in the game
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Counter blocks;

    /**
     * constructor.
     *
     * @param score is counter of score
     * @param blocks is counter of block
     */
    public ScoreIndicator(Counter score, Counter blocks) {
        this.score = score;
        this.blocks = blocks;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(GameFlow.SCREEN_WIDTH / 2 - 25, 20, "Score:" + score.getValue(), 20);
    }

    @Override
    public void timePassed() {
        return;
    }
}
