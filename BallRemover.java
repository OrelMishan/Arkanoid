/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * BallRemover is listener that update the ball counter of the game when ball out from the game.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param gameLevel           to update his data
     * @param remainingBalls is counter of ball that remain in the game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }

}
