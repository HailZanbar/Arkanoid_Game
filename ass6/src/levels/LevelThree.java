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
 * LevelThree Represents the infornation of level three.
 */
public class LevelThree extends AbstractLevel {

    private static final int BLOCKS_NUM = 40;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public int paddleSpeed() {
        return 8;
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground(int width, int height) {
        Background background = new Background();
        Rectangle rec = new Rectangle(new Point(0, 0), width, height);
        Color darkGreen = new Color(25, 100, 40);

        // create black background
        Block back = new Block(rec, darkGreen);
        background.addObject(back);
        return background;
    }

    @Override
    public List<Block> blocks(int width, int height, int margins) {
        List<Block> blocks = new LinkedList<>();

        int rowsNum = 5, firstRowBlocks = 10, blockHeight = 20, blockWidth = 50;

        // We will create an array of colors as the number of rows of blocks needed.
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.WHITE);

        // We will create graded rows and different colors of blocks, and add to the game.
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < firstRowBlocks - i; j++) {

                // The top left point of the first block in each row.
                Point p = new Point((width - margins - blockWidth * (j + 1)),
                        ((height / 4) + i * blockHeight));
                Rectangle rec = new Rectangle(p, (double) blockWidth, (double) blockHeight);
                Block b = new Block(rec, colors.get(i));
                blocks.add(b);
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
