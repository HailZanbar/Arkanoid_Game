// ID 322766353
import animation.AnimationRunner;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.LevelFour;
import levels.LevelInformation;
import levels.LevelOne;
import levels.LevelThree;
import levels.LevelTwo;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Hail Zan Bar
 * This class creates a game.
 */
public class Ass6Game {

    // Dimensions of the game screen.
    private static final int HEIGHT = 600, WIDTH = 800;

    /**
     * the main method of the program,that creates a game, initializes it and runs it.
     * @param args arguments from the command line
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new LinkedList<>();

        // we will add levels to the game according the user choose.
        for (String arg : args) {
            switch (arg) {
                case "1":
                    levels.add(new LevelOne());
                    break;
                case "2":
                    levels.add(new LevelTwo());
                    break;
                case "3":
                    levels.add(new LevelThree());
                    break;
                case "4":
                    levels.add(new LevelFour());
                    break;
                default:
                    break;
            }
        }

        // if the user didnt choose levels, we will run all the levels.
        if (levels.isEmpty()) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        }

        AnimationRunner runner = new AnimationRunner("Game", WIDTH, HEIGHT);
        KeyboardSensor keyboard = runner.getKeyboard();
        GameFlow gameFlow = new GameFlow(runner, keyboard);
        gameFlow.runLevels(levels);
    }
}
