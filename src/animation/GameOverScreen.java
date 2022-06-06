// ID 322766353
package animation;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Hail Zan Bar
 * The GameOverScreen will display the end screen of the game when the player did not win,
 * along with the score obtained during the game.
 */
public class GameOverScreen implements Animation {

    private int score;

    /**
     * A constructor of game over screen.
     * @param score The score with which the player finished the game.
     */
    public GameOverScreen(int score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // fill the surface.
        d.setColor(Color.black);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // We will write the text several times to create an interesting effect.
        d.setColor(Color.darkGray);
        d.drawText((d.getWidth() / 8) - 16, (d.getHeight() / 2) - 16, "Game Over", 120);

        d.setColor(Color.blue);
        d.drawText((d.getWidth() / 8) - 8, (d.getHeight() / 2) - 8, "Game Over", 120);

        d.setColor(new Color(144, 91, 180));
        d.drawText(d.getWidth() / 8, d.getHeight() / 2, "Game Over", 120);
        d.drawText(d.getWidth() / 4, d.getHeight() / 2 + d.getHeight() / 4,
                "Your score is " + this.score, 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
