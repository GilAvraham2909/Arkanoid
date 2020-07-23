package tools;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.HitListener;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBall;

    /**
     * Instantiates a new Block remover.
     *
     * @param game         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBall = removedBalls;
    }

    /**
     * Hit event:  Blocks that are hit and reach 0 hit-points should be removed from the game.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBall.decrease(1);
        hitter.removeFromGame(this.game);
    }

    /**
     * Gets remaining ball.
     *
     * @return the remaining ball
     */
    public Counter getRemainingBall() {
        return remainingBall;
    }
}
