// ID 322766353
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * @author Hail Zan Bar.
 * AnimationRunner runs an animation on the screen as long as it should not stop.
 */
public class AnimationRunner {

    private static final int FRAMES = 60;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private KeyboardSensor keyboard; // The keyboard of the gui of this animationRunner.

    /**
     * constructor of animationRunner.
     * @param title A string that is a title for the animation.
     * @param widthScreen The width of the screen on which the animation is displayed (int).
     * @param heightScreen The height of the screen on which the animation is displayed (int).
     */
    public AnimationRunner(String title, int widthScreen, int heightScreen) {
        this.gui = new GUI(title, widthScreen, heightScreen);
        this.framesPerSecond = FRAMES;
        this.sleeper = new biuoop.Sleeper();
        this.keyboard = this.gui.getKeyboardSensor();
    }

    /**
     * @return the keyboard sensor of this runner.
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * @return the gui of this runner.
     */
    public GUI getGui() {
        return this.gui;
    }


    /**
     * Runs the given animation on the screen as long as the animation should not stop.
     * @param animation The animation we want to display on the screen.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            // running the animation
            animation.doOneFrame(d);

            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
