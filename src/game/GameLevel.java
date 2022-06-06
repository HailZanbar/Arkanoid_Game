// ID 322766353
package game;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.BallRemover;
import collision.BlockRemover;
import collision.Collidable;
import collision.HitListener;
import collision.ScoreTrackingListener;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hail Zan Bar
 * This class creates a game that includes a list of game objects.
 */
public class GameLevel implements Animation {

    // Dimensions of the game screen and its margins (to be covered with blocks).
    //private static final int HEIGHT = 600, WIDTH = 800, MARGINS = 25;

    // the height of the paddle.
    private static final int PADD_HEIGHT = 15;

    // The radius of balls in the game.
    private static final int RADIUS = 5;

    // The score which cleaning an entire level (destroying all blocks) is worth
    private static final int NEXT_LEVEL_SCORE = 100;

    private SpriteCollection sprites; // Collection of objects in the game
    private GameEnvironment environment;
    private Paddle paddle;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private int widthS;
    private int heightS;
    private int margins;

    /**
     * A constructor who initializes the game.
     * @param level the information of this level.
     * @param keyboard the keyboard of the game.
     * @param runner the runner which runs the game level.
     * @param scoreCounter the counter of the score of the whole game.
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter scoreCounter) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.scoreCounter = scoreCounter;
        this.runner = runner;
        this.keyboard = keyboard;
        this.level = level;
        this.running = true;

        // the size of the surface of the game.
        this.widthS = runner.getGui().getDrawSurface().getWidth();
        this.heightS = runner.getGui().getDrawSurface().getHeight();
        this.margins = widthS / 32;
    }

    /**
     * set the paddle of the game.
     * @param p the paddle of the game.
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * Adds the given object to the list of objects that can collide of the game environment.
     * @param c a collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds the given object to the list of the sprites of the game.
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Creates balls for the game.
     */
    private void createBalls() {
        ArrayList<Ball> balls = new ArrayList<Ball>();
        List<Velocity> velocities = this.level.initialBallVelocities();
        for (int i = 0; i < this.level.numberOfBalls(); i++) {

            double x = this.widthS / 2;
            double y = (this.heightS - (2 * PADD_HEIGHT)) - 10;

            Velocity v = velocities.get(i);
            Ball ball = new Ball(x, y, RADIUS, Color.WHITE);
            ball.setVelocity(v);
            balls.add(ball);
        }

        // initial the balls and add them to the game.
        for (Ball ball : balls) {
            ball.setEnvironment(this.environment);
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
    }

    /**
     * Represents the death region block, which causes the ball touching it to disappear from the game.
     * @param ballRemover An object that removes the balls that touch this block.
     */
    private void createDeathRegion(HitListener ballRemover) {
        Block deathBlock = new Block(new Rectangle(new Point(0, this.heightS + 2 * RADIUS),
                this.widthS, 0), Color.GRAY);

        // add the ball remover to the listeners list of this block.
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);
    }

    /**
     * Creates blocks for the game.
     * @param blockRemover Listener attached to each block, and causes its removal if an hit occurs.
     * @param scoreTracking Listener attached to each block, causing the score to increase when a block is hit.
     */
    private void createBlocks(HitListener blockRemover, HitListener scoreTracking) {

        /*
        We will create an array of blocks for the 4 margins of the game screen, and we will add blocks in the
        appropriate dimensions for the array.
        */
        ArrayList<Block> sideBlocks = new ArrayList<Block>(3);
        sideBlocks.add(new Block(new Rectangle(new Point(0, this.margins), this.widthS, this.margins), Color.GRAY));
        sideBlocks.add(new Block(new Rectangle(new Point(this.widthS - this.margins, 0),
                this.margins, this.heightS), Color.GRAY));
        sideBlocks.add(new Block(new Rectangle(new Point(0, 0), this.margins, this.heightS), Color.GRAY));

        // We will add the blocks of the margins to the game.
        for (Block b : sideBlocks) {
            b.addToGame(this);
        }

        for (Block b : this.level.blocks(this.widthS, this.heightS, this.margins)) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTracking);
            b.addToGame(this);
            this.blockCounter.increase(1);
        }
    }

    /**
     * A method which creates a paddle to the game.
     */
    private void createPaddle() {

        // the upper left point of the paddle
        Point upperLeft = new Point((this.widthS / 2 - this.level.paddleWidth() / 2),
                this.heightS - (2 * PADD_HEIGHT));

        // A rectangle that forms the shape of the paddle.
        Rectangle rec = new Rectangle(upperLeft, this.level.paddleWidth(), PADD_HEIGHT);
        this.setPaddle(new Paddle(this.keyboard, rec, Color.ORANGE, this.environment, this.margins,
                (this.widthS - this.margins)));
        this.paddle.setSpeed(this.level.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * create a sprite - the score indicator of the game.
     */
    private void createScoreIndicator() {
        ScoreIndicator score = new ScoreIndicator(this.scoreCounter, this.widthS, this.margins,
                this.level.levelName());
        this.addSprite(score);
    }


    /**
     * Initialize a new game: create the Blocks, the Ball and the  Paddle, and add them to the game.
     */
    public void initialize() {

        // We will create the blocks and their hit listeners for the game.
        HitListener ballRemover = new BallRemover(this, this.ballCounter);
        createDeathRegion(ballRemover);
        HitListener blockRemover = new BlockRemover(this, this.blockCounter);
        HitListener scoreTracking = new ScoreTrackingListener(this.scoreCounter);
        this.createBlocks(blockRemover, scoreTracking);

        // we will create the paddle of the game.
        this.createPaddle();

        // we will create the score indicator.
        this.createScoreIndicator();
    }


    /**
     * Run the game - start the animation loop.
     */
    public void run() {

        // We will create the balls for the game.
        this.createBalls();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites,
                this.level.getBackground(this.widthS, this.heightS)));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * remove the given collidable from the game.
     * @param c collidable object in the game
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove the given sprite from the game.
     * @param s sprite object in the game
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        this.level.getBackground(this.widthS, this.heightS).drawOn(d);

        // Draws all the objects belonging to the game to the screen.
        this.sprites.drawAllOn(d);

        // Activates the mode change for all objects in the game.
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            Animation pause = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pause));
        }

        // checks the conditions for the continuation of the game.
        if  (this.ballCounter.getValue() == 0) {
            this.running = false;
        }

        // Increases the score by 100 points if all blocks are destroyed.
        if (this.blockCounter.getValue() == this.level.initialBlocksNum() - this.level.numberOfBlocksToRemove()) {
            this.running = false;
            this.scoreCounter.increase(NEXT_LEVEL_SCORE);
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return The number of balls remaining in the game at this point.
     */
    public int getCurrentBallsNum() {
        return this.ballCounter.getValue();
    }
}
