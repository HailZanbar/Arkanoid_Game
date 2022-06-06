// ID 322766353
package sprites;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * @author Hail Zan Bar
 * This class creates a paddle for the game by a shape (rectangle), a color,A play environment,
 * and the boundaries between them it can move.
 */
public class Paddle implements Sprite, Collidable {

    // The number of areas on the top flank of the paddle that change the speed an object that collides differently.
    private static final int AREA_NUM = 5;

    // A fixed angle that is the difference between the angles returned from different areas in the paddle.
    private static final int ANGLE_MOVE = 30;

    // The velocity angle returned from a collision in the leftmost region of the paddle.
    private static final int LEFT_ANGLE = 300;

    // The distance the paddle moves to the right or left.
    private static final int MOVE = 10;

    private biuoop.KeyboardSensor keyboard;
    private Rectangle shape;
    private java.awt.Color color;
    private GameEnvironment env;
    private double leftBound;
    private double rightBound;
    private int speed;

    // instructor

    /**
     * A constructor which creates a paddle.
     * @param keyboard An object that allows reading of user presses on the keyboard.
     * @param rec A rectangle that forms the shape of the paddle.
     * @param color The color of the paddle.
     * @param environment The game environment to which the paddle belongs.
     * @param leftBound The left border to which the paddle can move.
     * @param rightBound The right border to which the paddle can move.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rec, java.awt.Color color, GameEnvironment environment,
                  double leftBound, double rightBound) {
        this.keyboard = keyboard;
        this.shape = rec;
        this.color = color;
        this.env = environment;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    /**
     * set the speed of this paddle.
     * @param newSpeed the speed of this paddle.
     */
    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * Move the paddle to the left, at a fixed distance (without crossing the left border).
     */
    public void moveLeft() {
        double x = this.shape.getUpperLeft().getX();
        double y = this.shape.getUpperLeft().getY();

         // If after moving the paddle crosses the left border, move smaller step.
        if (x - speed < this.leftBound) {
            double move = x - leftBound;
            this.shape.setUpperLeft(new Point(x - move, y));
            return;
        }

        // Move the ball to the left.
        this.shape.setUpperLeft(new Point(x - speed, y));

    }

    /**
     * Move the paddle to the right, at a fixed distance (without crossing the right border).
     */
    public void moveRight() {
        double x = this.shape.getUpperLeft().getX();
        double y = this.shape.getUpperLeft().getY();

        // If after moving the paddle crosses the right border, move smaller step.
        if ((x + this.shape.getWidth() + speed) > rightBound) {
            double move = rightBound - (x + this.shape.getWidth());
            this.shape.setUpperLeft(new Point(x + move, y));
            return;
        }

        // Move the ball to the right.
        this.shape.setUpperLeft(new Point(x + speed, y));
    }

    @Override
    public void timePassed() {

        // If the user presses the left arrow, move the paddle to the left.
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {

            // If the user presses the right arrow, move the paddle to the right.
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        // choose the color of the paddle.
        d.setColor(this.color);

        // fill the size of the block with its color.
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());

        // choose the color of the paddle borders.
        d.setColor(Color.BLACK);

        // fill the size of the block with its color.
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        // Size of area on top of the paddle. Each area will move the colliding ball to a different angle.
        double area = this.shape.getWidth() / AREA_NUM;
        Velocity newVelocity = currentVelocity;
        Point upperLeft = this.shape.getUpperLeft();
        Point lowerLeft = new Point((this.shape.getUpperLeft().getX()),
                (this.shape.getUpperLeft().getY() + this.shape.getHeight()));
        Point upperRight = new Point((this.shape.getUpperLeft().getX() + this.shape.getWidth()),
                (this.shape.getUpperLeft().getY()));

        // If the ball hits one of the side ribs of the paddle, we will change its horizontal direction.
        if (upperLeft.getY() < collisionPoint.getY() && collisionPoint.getY() < lowerLeft.getY()) {
            newVelocity.setDx(-(newVelocity.getDx()));
        } else if ((upperLeft.getX() <= collisionPoint.getX()) && (collisionPoint.getX() <= upperRight.getX())) {

            // compute the speed of the current velocity.
            double newSpeed = currentVelocity.computeSpeed();

            /*
            If the collision occurs on the top flank of the paddle, we will change the angle of movement of
            the ball depending on the area on the flank where it collides.
            */
            for (int i = 0; i < AREA_NUM; i++) {
                if ((upperLeft.getX() + area * i) <= collisionPoint.getX()
                        && collisionPoint.getX() <= (upperLeft.getX() + area * (i + 1))) {

                    /*
                    Calculate the new velocity by adding a constant angle to the initial angle according to
                    the number of the area where the collision occurred.
                    */
                    newVelocity = Velocity.fromAngleAndSpeed((LEFT_ANGLE + i * ANGLE_MOVE), newSpeed);
                }
            }
        }
        return newVelocity;
    }

    /**
     * Add this paddle to the game.
     * @param g The game to which the paddle is added
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);

        // We will add the paddle to the paddle's game environment.
        this.env.setPaddle(this);
    }
}