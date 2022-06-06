// ID 322766353
package animation;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Hail Zan Bar
 * The PauseScreen will display the pause screen of the game when the player presses the key p.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {

        // fill the surface
        d.setColor(new Color(0, 150, 200));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.black);
        d.drawText(d.getWidth() / 7 + 12, d.getHeight() / 2 - 8, "paused", 150);

        d.setColor(Color.white);
        d.drawText(d.getWidth() / 7 + 20, d.getHeight() / 2, "paused", 150);
        d.drawText(d.getWidth() / 5, d.getHeight() / 2 + d.getHeight() / 4,
                "Press space to continue", 40);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
