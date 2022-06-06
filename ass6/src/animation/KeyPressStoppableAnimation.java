// ID 322766353
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Hail Zan Bar
 * the KeyPressStoppableAnimation is a decorator-class that will wrap an existing animation and add
 * a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed; // Let us know if the key is pressed from the previous animation.

    /**
     * A constructor of KeyPressStoppableAnimation.
     * @param sensor the keyboard sensor of the game.
     * @param key the key (String) that the user should click to stop running the animation.
     * @param animation The animation that running.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(key)) {

            // if the key is not pressed from the previous animation, we will stop the animation.
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {

            // Update that the key is not pressed from the previous animation.
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
