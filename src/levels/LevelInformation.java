// ID 322766353
package levels;

import collision.Velocity;
import sprites.Block;
import sprites.Sprite;
import java.util.List;

/**
 * @author Hail Zan Bar
 * The LevelInformation interface specifies the information required to fully describe a level.
 * It includes information about the paddle, balls, blocks and more.
 */
public interface LevelInformation {

    /**
     * @return the number of balls in this level.
     */
    int numberOfBalls();

    /**
     * @return A list which contains the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the speed of the balls.
     */
    int ballsSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen (a string).
     */
    String levelName();

    /**
     * creates a sprite with the background of the level and returns it.
     * @param width the width of the surface which the background displayed on.
     * @param height the height of the surface which the background displayed on.
     * @return a sprite with the background of the level.
     */
    Sprite getBackground(int width, int height);

    /**
     * creates and returns a list of The Blocks that make up this level, each block contains
     * its size, color and location.
     * @param width the width of the surface which the blocks displayed on.
     * @param height the height of the surface which the blocks displayed on.
     * @param margins the margins of the surface which the blocks displayed on.
     * @return a list of The Blocks that make up this level.
     */
    List<Block> blocks(int width, int height, int margins);

    /**
     * @return The number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();

    /**
     * @return the initial number of blocks in this level.
     */
    int initialBlocksNum();
}
