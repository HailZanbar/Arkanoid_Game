// ID 322766353
package collision;

import game.Counter;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * @author Hail Zan Bar
 * This class implements the "HitListener" interface, and is responsible for removing ball from the game when it hits
 * a block holding its object. its also keeping count of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor of ball remover.
     * @param game The game in which removal occurs.
     * @param removedBalls Counter of balls in the game.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Removes from the game the ball that hit the block that holds the ball remover.
     * @param beingHit The block hit by a ball.
     * @param hitter the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
