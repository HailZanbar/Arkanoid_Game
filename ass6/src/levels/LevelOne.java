// ID 322766353
package levels;

import geometry.Point;
import geometry.Rectangle;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Hail Zan Bar
 * LevelOne Represents the infornation of level one.
 */
public class LevelOne extends AbstractLevel {

    private static final int BLOCK_SIZE = 35;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int ballsSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 75;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground(int width, int height) {
        Background background = new Background();
        Rectangle rec = new Rectangle(new Point(0, 0), width, height);

        // create black background
        Block back = new Block(rec, Color.black);
        background.addObject(back);
        return background;
    }

    @Override
    public List<Block> blocks(int width, int height, int margins) {
        List<Block> blocks = new LinkedList<>();
        Point upLeftBlock = new Point((double) (width / 2) - (BLOCK_SIZE / 2), (double) (height / 4));
        Rectangle recBlock = new Rectangle(upLeftBlock, BLOCK_SIZE, BLOCK_SIZE);

        // Creates the one block in this level.
        Block block = new Block(recBlock, Color.RED);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public int initialBlocksNum() {
        return 1;
    }
}
