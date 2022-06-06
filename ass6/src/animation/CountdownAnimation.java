// ID 322766353
package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * @author Hail Zan Bar
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int currentCount;
    private boolean stop;
    private Sprite background;


    /**
     * A constructor of countdown.
     * @param numOfSeconds The number of the seconds that the countdown should displayed.
     * @param countFrom the number which the countdown begins from.
     * @param gameScreen the components of the game level that appear behind the count.
     * @param background the background of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentCount = countFrom;
        this.background = background;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLUE);

        // drawing the text In the desired location on the screen.
        d.drawText(5 * (d.getWidth() / 11), (d.getHeight() / 2 + d.getHeight() / 4),
                String.valueOf(this.currentCount), 150);

        // Wait before displaying the next digit, unless it is the first digit.
        if (this.currentCount != this.countFrom) {
            sleeper.sleepFor((long) ((numOfSeconds / countFrom) * 1000));
        }

        // If the countdown is over, we can end the animation display.
        if (currentCount == 0) {
            this.stop = true;
        }
        this.currentCount -= 1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}