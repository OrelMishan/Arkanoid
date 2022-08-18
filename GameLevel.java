/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Orel Mishan
 * GameLevel is the object that save the logic and information of each level.
 */
public class GameLevel implements Animation {
    private LevelInformation levelInfo;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocks;
    private Counter balls;
    private Paddle paddle;
    private Counter score;


    public static final int BORDERS = 4;
    public static final int BORDERS_WIDTH = 25;
    public static final int BALL_SIZE = 5;
    public static final int BALL_SPEED = 7;

    /**
     * constructor.
     *
     * @param levelInfo       is information of the level
     * @param keyboardSensor  is the keyboard sensor
     * @param animationRunner run the animation
     * @param gui             is the screen
     * @param score           is the counter of score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, GUI gui, Counter score) {
        this.gui = gui;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.levelInfo = levelInfo;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.blocks = new Counter();
        this.balls = new Counter();
        this.score = score;
    }

    /**
     * @param c is callable add to the game
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param s is sprite add to the game
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //create the background
        this.addSprite(this.levelInfo.getBackground());
        //create the blocks
        for (Block block : this.levelInfo.blocks()) {
            block.addToGame(this);
            //add the listeners
            block.addHitListener(new BlockRemover(this, blocks));
            block.addHitListener(new ScoreTrackingListener(score));
        }
        blocks.increase(this.levelInfo.numberOfBlocksToRemove());
        //create the blocks that limit the screen
        Block[] frameLimit = new Block[BORDERS];
        frameLimit[0] = new Block(new Rectangle(new Point(0, 0), GameFlow.SCREEN_WIDTH, BORDERS_WIDTH), Color.GRAY);
        frameLimit[1] = new Block(new Rectangle(new Point(0, 0), BORDERS_WIDTH, GameFlow.SCREEN_HEIGHT), Color.GRAY);
        frameLimit[2] = new Block(new Rectangle(new Point(0, GameFlow.SCREEN_HEIGHT + 1),
                GameFlow.SCREEN_WIDTH, 1), Color.gray);
        frameLimit[2].addHitListener(new BallRemover(this, balls));
        frameLimit[3] = new Block(new Rectangle(new Point(GameFlow.SCREEN_WIDTH - BORDERS_WIDTH, 0),
                BORDERS_WIDTH, GameFlow.SCREEN_HEIGHT), Color.GRAY);
        for (Block block : frameLimit) {
            block.addToGame(this);
        }
        //create the score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, blocks);
        this.addSprite(scoreIndicator);
        //create the level's name
        this.addSprite(new NameLevel(this.levelInfo.levelName()));
        //create the paddle
        this.paddle = new Paddle(gui, this.levelInfo.paddleSpeed(), this.levelInfo.paddleWidth());
        this.paddle.addToGame(this);
        this.balls.increase(this.levelInfo.numberOfBalls());
    }

    /**
     * @param c to remove from the list of collidables
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * @param s remove from the list of sprites
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * @param ball to remove from the game
     */
    public void removeBall(Ball ball) {
        this.sprites.removeSprite(ball);
        this.paddle.removeBall(ball);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //check if remove all the blocks or the ball
        if (this.blocks.getValue() <= 0 || this.balls.getValue() <= 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }

    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        //add the balls
        this.createBallsOnTopOfPaddle();
        this.running = true;
        // use our runner to run the current animation
        this.runner.run(this);
    }

    /**
     * create balls of the level.
     */
    private void createBallsOnTopOfPaddle() {
        for (Velocity velocity : this.levelInfo.initialBallVelocities()) {
            Ball ball = new Ball((int) (this.paddle.getCollisionRectangle().getUpperLeft().getX()
                    + (int) (paddle.getCollisionRectangle().getWidth() / 2)),
                    (int) (this.paddle.getCollisionRectangle().getUpperLeft().getY() - (2 * BORDERS_WIDTH)),
                    BALL_SIZE, Color.lightGray);
            ball.setGameEnvironment(this.environment);
            ball.setVelocity(velocity);
            ball.addToGame(this);
            this.paddle.addBall(ball);
        }
    }

    /**
     * @return how blocks remaining in the level
     */
    public int remainingBlocks() {
        return this.blocks.getValue();
    }

    /**
     * @return how ball remaining in the level
     */
    public int remainingBalls() {
        return this.balls.getValue();
    }

    /**
     * @return the list of the sprites in the level
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }
}