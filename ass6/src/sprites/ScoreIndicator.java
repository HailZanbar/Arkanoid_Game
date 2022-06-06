// ID 322766353
package sprites;

import biuoop.DrawSurface;
import game.Counter;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * @author Hail Zan Bar
 * A class that represents a score indicator that shows the score status on the game screen at any given moment.
 */
public class ScoreIndicator implements Sprite {

    // A block that is the shape of the indicator.
    private Block shape;

    // The rectangle that makes up the block, and gives access to the size of the indicator.
    private Rectangle geoShape;

    // The current score in the game.
    private Counter score;

    // A string containing the current score.
    private String scoreString;

    private String levelName;

    /**
     * Constructor of the score indicator.
     * @param scoreCounter A counter which contains the current score in the game.
     * @param width the width of the indicator.
     * @param height the height of the indicator.
     * @param levelName the name of the current level in the game.
     */
    public ScoreIndicator(Counter scoreCounter, int width, int height, String levelName) {
        Rectangle rec = new Rectangle(new Point(0, 0), width, height);
        this.geoShape = rec;
        this.shape = new Block(rec, Color.WHITE);
        this.score = scoreCounter;
        this.scoreString = "Score:" + this.score.getValue();
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.shape.drawOn(d);
        int textPosition = (int) (this.geoShape.getWidth() / 2);
        int textHeight = (int) (this.geoShape.getHeight()) - 5;
        d.setColor(Color.black);

        // write the score
        d.drawText(textPosition, textHeight, this.scoreString, textHeight - 5);

        // write the level name at the right edge of the indicator
        d.drawText(textPosition + textPosition / 2, textHeight, "Level Name: " + this.levelName,
                textHeight - 5);
    }

    @Override
    public void timePassed() {
        this.scoreString = "Score:" + this.score.getValue();
    }
}
