// ID 322766353
package animation;

import biuoop.DrawSurface;

/**
 * @author Hail Zan Bar.
 * This interface represents animation that can be displayed on the screen and stopped showing.
 */
public interface Animation {

    /**
     * Display the animation on the given draw surface.
     * @param d a draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * A method that announces whether an animated display should stop or not.
     * @return boolean value - true if this animation should stop, false otherwise.
     */
    boolean shouldStop();
}
