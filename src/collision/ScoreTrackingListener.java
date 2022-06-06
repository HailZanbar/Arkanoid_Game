// ID 322766353
package collision;

import game.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * @author Hail Zan Bar
 * Responsible for tracking the score in the game and raising the score when a block hit.
 */
public class ScoreTrackingListener implements HitListener {

    // The score for hitting a block.
    private static final int BLOCK_SCORE = 5;

    // the counter of the score.
    private Counter currentScore;

    /**
     * A constructor of score tracking in the game.
     * @param scoreCounter the counter of the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(BLOCK_SCORE);
    }
}
