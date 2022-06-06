// ID 322766353
package sprites;
import java.util.LinkedList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * @author Hail Zan Bar
 * This class creates a sprite collection for the game, by a list of the sprited of the game.
 */
public class SpriteCollection {

    private List<Sprite> spriteList;

    /**
     * A constructor that creates the sprite collection by initializing a linked list of sprites.
     */
    public SpriteCollection() {
        this.spriteList = new LinkedList<Sprite>();
    }

    /**
     * Add a sprite to the sprite collection.
     * @param s The sprite added to the collection.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Remove a sprite from the sprite collection.
     * @param s The sprite removed from the collection.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * Enable mode change on all sprites in the list (call timePassed() on all sprites).
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new LinkedList<>(this.spriteList);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * Draw all the sprites listed on the given surface (call drawOn(d) on all sprites).
     * @param d the draw surface of the game.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCopy = new LinkedList<>(this.spriteList);
        for (Sprite s : spritesCopy) {
            s.drawOn(d);
        }
    }
}
