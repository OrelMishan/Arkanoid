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
public class
LevelThree implements LevelInformation {
    private final int numBalls = 2;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private final int paddleSpeed = 5;
    private int paddleWidth = 100;
    private final String levelName = "Level: 3";
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numBlocks = 34;

    /**
     * constructor.
     */
    public LevelThree() {
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(45, 5));
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(-45, 5));
        this.background = new Block(new Rectangle(new Point(0, 0),
                GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT), Color.GREEN.darker());
        //create the blocks
        Color[] colors = new Color[5];
        colors[0] = Color.GRAY;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.BLUE;
        colors[4] = Color.WHITE;
        for (int i = 0; i < 5; i++) {
            int x = GameFlow.SCREEN_WIDTH - 500 - GameLevel.BORDERS_WIDTH + (50 * i);
            int y = 100 + (25 * i);
            for (int j = i; j < 10; j++) {
                Block block = new Block(new Rectangle(new Point(x, y), 50, 25), colors[i]);
                this.blocks.add(block);
                x += 50;
            }
        }
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
