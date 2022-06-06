// ID 322766353
package collision;

import sprites.Ball;
import sprites.Block;

/**
 * @author Hail Zan Bar.
 * This interface represents Represents a listener who performs a particular action when the block
 * that holds the listener is hit.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block which hit.
     * @param hitter The Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
