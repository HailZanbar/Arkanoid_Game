// ID 322766353
package sprites;

import biuoop.DrawSurface;

/**
 * @author Hail Zan Bar
 * A background is an object composed of sprites and constitutes a background (on the screen)
 * for a certain level in the game.
 */
public class Background implements Sprite {

    // the objects that will appear in the background of the game.
    private SpriteCollection sprites;

    /**
     * A constructor of background.
     */
    public Background() {
        this.sprites = new SpriteCollection();
    }

    /**
     * add an sprite object to the background.
     * @param obj the sprite we want to add to the background.
     */
    public void addObject(Sprite obj) {
        this.sprites.addSprite(obj);
    }

    @Override
    public void drawOn(DrawSurface d) {
        sprites.drawAllOn(d);
    }

    @Override
    public void timePassed() {
        return;
    }
}
