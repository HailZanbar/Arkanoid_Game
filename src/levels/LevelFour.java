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
 * LevelFour Represents the infornation of level four.
 */
public class LevelFour extends AbstractLevel {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int ballsSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 75;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground(int width, int height) {
        Background background = new Background();
        Rectangle rec = new Rectangle(new Point(0, 0), width, height);
        Color lightBlue = new Color(0, 150, 255);

        // create black background
        Block back = new Block(rec, lightBlue);
        background.addObject(back);
        return background;
    }

    @Override
    public List<Block> blocks(int width, int height, int margins) {
        List<Block> blocks = new LinkedList<>();

        int rowsNum = 7, rowBlocks = 15, blockHeight = 20;
        double blockWidth = (double) ((width - 2 * margins) / rowBlocks);

        // We will create an array of colors as the number of rows of blocks needed.
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);
        colors.add(Color.pink);
        colors.add(Color.CYAN);

        // We will create graded rows and different colors of blocks, and add to the game.
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < rowBlocks; j++) {

                // The top left point of the first block in each row.
                Point p = new Point((margins + blockWidth * (j)),
                        ((height / 6) + i * blockHeight));
                Rectangle rec = new Rectangle(p, (double) blockWidth, (double) blockHeight);
                Block b = new Block(rec, colors.get(i));
                blocks.add(b);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    @Override
    public int initialBlocksNum() {
        return 105;
    }
}
