/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * BlockRemover is listener that update the bLOCK counter of the game when block being hit.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param gameLevel          to update his data
     * @param removedBlocks is counter of blocks that remain in the game
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}