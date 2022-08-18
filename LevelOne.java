/*
 * Orel Mishan
 * 316551092
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Orel Mishan
 * this class has the information of the level four
 */

public class LevelOne implements LevelInformation {

    private final int numBalls = 1;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private final int paddleSpeed = 5;
    private int paddleWidth = 100;
    private final String levelName = "Level: 1";
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numBlocks = 1;

    /**
     * constructor.
     */
    public LevelOne() {
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(0, 5));
        this.background = new Block(new Rectangle(new Point(0, 0),
                GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT), Color.black);
        this.blocks.add(new Block(new Rectangle(new Point(GameFlow.SCREEN_WIDTH / 2 - 20, 240),
                40, 40), Color.RED));
    }

    @Override
    public int numberOfBalls() {
        return this.numBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numBlocks;
    }
}
