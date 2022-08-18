/*
 * Orel Mishan
 * 316551092
 */

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Orel Mishan
 * GameFlow run on the screen list of levels.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private GUI gui;
    private boolean win;

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    /**
     * constructor.
     *
     * @param ar  is the runner that run animation.
     * @param ks  is the keyboard sensor
     * @param gui is the screen
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = new Counter();
        this.win = true;
    }

    /**
     * run the levels on the screen.
     *
     * @param levels is list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            //create the level and initialize him
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, this.gui, this.score);

            level.initialize();
            //run countdown
            this.animationRunner.run(new CountdownAnimation(2, 3, level.getSprites()));
            while (level.remainingBlocks() > 0 && level.remainingBalls() > 0) {
                level.run();
            }
            //check if the player loose
            if (level.remainingBalls() <= 0) {
                this.win = false;
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new GameOverScreen(this.keyboardSensor, this.score)));
                break;
            }
            //add score of win level
            this.score.increase(100);

        }
        //run win screen animation
        if (this.win) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new WinScreen(this.keyboardSensor, this.score)));
        }
    }
}

