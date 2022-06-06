// ID 322766353
package collision;

import game.Counter;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * @author Hail Zan Bar
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;

    // count of the number of blocks that remain in the game.
    private Counter remainingBlocks;

    /**
     * Constructor of blocks remover.
     * @param game The game in which removal occurs.
     * @param removedBlocks Counter of blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * remove blocks that are hit from the game.
     * @param beingHit The block hit by a ball.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
