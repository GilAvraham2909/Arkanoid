package tools;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.HitListener;

/**
 * The type Block remover:  a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Blocks that are hit and reach 0 hit-points should be removed from the game.
        if (beingHit.getMaxHit() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            remainingBlocks.decrease(1);
        }

    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}