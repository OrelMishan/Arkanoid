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
public class LevelTwo implements LevelInformation {

    private final int numBalls = 10;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private final int paddleSpeed = 5;
    private int paddleWidth = 650;
    private final String levelName = "Level: 2";
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numBlocks = 15;

    /**
     * constructor.
     */
    public LevelTwo() {
        for (int i = 1; i <= this.numBalls / 2; i++) {
            this.ballVelocities.add(Velocity.fromAngleAndSpeed(13 * i, 5));
            this.ballVelocities.add(Velocity.fromAngleAndSpeed(-13 * i, 5));
        }
        this.background = new Block(new Rectangle(new Point(0, 0),
                GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT), Color.white);
        Color[] colors = new Color[7];
        colors[0] = Color.RED;
        colors[1] = Color.ORANGE;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.BLUE;
        colors[5] = Color.pink;
        colors[6] = Color.CYAN;
        for (int i = 0; i < 3; i++) {
            int x = GameLevel.BORDERS_WIDTH + (100 * i);
            int y = 150;
            for (int j = 0; j < 2; j++) {
                this.blocks.add(new Block(new Rectangle(new Point(x, y), 50, 20), colors[i]));
                x += 50;
            }
        }
        for (int i = 0; i < 3; i++) {
            int x = (GameFlow.SCREEN_WIDTH / 2) - 75 + (50 * i);
            int y = 150;
            this.blocks.add(new Block(new Rectangle(new Point(x, y), 50, 20), colors[3]));

        }
        for (int i = 0; i < 3; i++) {
            int x = GameFlow.SCREEN_WIDTH - (GameLevel.BORDERS_WIDTH + 50 + (100 * i));
            int y = 150;
            for (int j = 0; j < 2; j++) {
                this.blocks.add(new Block(new Rectangle(new Point(x, y), 50, 20), colors[colors.length - i - 1]));
                x -= 50;
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
