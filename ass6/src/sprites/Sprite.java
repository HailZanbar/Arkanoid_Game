// ID 322766353
package sprites;
import biuoop.DrawSurface;

/**
 * @author Hail Zan Bar.
 * This interface represents the objects that belong to the game, and are characterized by features
 * such as changing the position and drawing the object on the game surface.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d the draw surface of the game.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed (To change his condition accordingly).
     */
    void timePassed();
}
