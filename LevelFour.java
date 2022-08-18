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
public class LevelFour implements LevelInformation {
    private final int numBalls = 3;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private final int paddleSpeed = 7;
    private int paddleWidth = 100;
    private final String levelName = "Level: 4";
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numBlocks = 105;

    /**
     * constructor.
     */
    public LevelFour() {
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(45, 5));
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(0, 5));
        this.ballVelocities.add(Velocity.fromAngleAndSpeed(-45, 5));
        this.background = new Block(new Rectangle(new Point(0, 0),
                GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT), Color.BLUE);
        //create the blocks
        Color[] colors = new Color[7];
        colors[0] = Color.GRAY;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.WHITE;
        colors[5] = Color.PINK;
        colors[6] = Color.CYAN;
        for (int i = 0; i < colors.length; i++) {
            int x = 25;
            int y = 100 + (20 * i);
            for (int j = 0; j < 15; j++) {
                Block block = new Block(new Rectangle(new Point(x, y), 50, 20), colors[i]);
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
