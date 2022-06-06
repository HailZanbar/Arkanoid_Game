// ID 322766353
package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.GameOverScreen;
import animation.KeyPressStoppableAnimation;
import animation.WinScreen;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import java.util.List;

/**
 * @author Hail Zan Bar
 * This department is responsible for moving from the current level to the next level in the game,
 * and for displaying the end screen according to the game results.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private Counter score;
    private boolean win;

    /**
     * A constructor of GameFlow.
     * @param ar An animation runner which responsible for running the levels.
     * @param ks the keyboard sensor of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.win = true;
    }

    /**
     * Runs the game levels one after the other, until the player wins or loses.
     * @param levels A list of the levels of the game.
     */
    public void runLevels(List<LevelInformation> levels) {

        // We will go through all the levels of the game in the given list of levels.
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.runner, this.score);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            // If all the balls fall at this point, the player loses and we end the game.
            if (level.getCurrentBallsNum() == 0) {
                this.win = false;
                break;
            }
        }
        Animation endScreen;

        // We will initialize an end screen according to the results of the game and present it.
        if (!this.win) {
            endScreen = new GameOverScreen(this.score.getValue());
        } else {
            endScreen = new WinScreen(this.score.getValue());
        }
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, endScreen));
        this.runner.getGui().close();
    }
}
