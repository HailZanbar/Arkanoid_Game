// ID 322766353
package levels;

import geometry.Point;
import geometry.Rectangle;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Hail Zan Bar
 * LevelTwo Represents the infornation of level two.
 */
public class LevelTwo extends AbstractLevel {

    private static final int BLOCKS_NUM = 15;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int ballsSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 350;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground(int width, int height) {
        Background background = new Background();
        Rectangle rec = new Rectangle(new Point(0, 0), width, height);

        // create black background
        Block back = new Block(rec, Color.WHITE);
        background.addObject(back);
        return background;
    }

    @Override
    public List<Block> blocks(int width, int height, int margins) {

        List<Block> blocks = new LinkedList<>();

        // We will create an array of colors as the number of rows of blocks needed.
        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.pink);
        colors.add(Color.CYAN);

        int blockWidth = (width - (2 * margins)) / BLOCKS_NUM;
        int blockHeight = 20;
        double position = 0;

        // run over the colors of the blocks
        for (int j = 0; j < colors.size(); j++) {
            int inSameColor = 2;

            // if the current color is green, we need 3 blocks in this color.
            if (j == colors.indexOf(Color.GREEN)) {
                inSameColor = 3;
            }

            // creates some blocks in the same color.
            for (int i = 0; i < inSameColor; i++) {

                // The top left point of the first block in each row.
                Point p = new Point(margins + position, height / 3);
                Rectangle rec = new Rectangle(p, blockWidth, blockHeight);
                Block b = new Block(rec, colors.get(j));
                blocks.add(b);
                position += blockWidth;
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_NUM;
    }

    @Override
    public int initialBlocksNum() {
        return BLOCKS_NUM;
    }
}
