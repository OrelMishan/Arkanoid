/*
 * Orel Mishan
 * 316551092
 */


import java.util.List;

/**
 * this interface hase methods of classes that represents levels in the game.
 */
public interface LevelInformation {
    /**
     * @return number of balls in the level
     */
    int numberOfBalls();

    /**
     * @return the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * @return the level's name
     */
    String levelName();

    /**
     * @return the background of the level
     */
    Sprite getBackground();

    /**
     * @return list of blocks in the level
     */
    List<Block> blocks();

    /**
     * @return number of blocks in the level
     */
    int numberOfBlocksToRemove();
}
