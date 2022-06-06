// ID 322766353
package sprites;
import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

/**
 * @author Hail Zan Bar
 * This class creates a ball (a circle) by a center point, a radius, a color and more.
 */
public class Ball implements Sprite {

    private static final int OUT = 5;

    private Point center;  // The center point of the ball.
    private int radius;  // The radius of the ball.
    private java.awt.Color color;  // The color of the ball.
    private Velocity velocity;  // The velocity of the movement of the ball.
    private int surfaceWidth;  // The width of the surface on which the ball is.
    private int surfaceHeight;  // The height of the surface on which the ball is.
    private Point surfaceStart; // The start point of the surface on which the ball is (the first of the hinges).
    private GameEnvironment env; // the game environment of the ball.

    /**
     * A constructor which creates a ball.
     * @param center the center point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * A constructor which creates a ball.
     * @param x the x value of the center point of the ball.
     * @param y the y value of the center point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * @return the x value (double) of the center point of this ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y value (double) of the center point of this ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius (int) of this ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color (java.awt.Color) of this ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface

    /**
     * a function that draws the ball ("this"), on the given DrawSurface.
     * @param surface A drawing surface on which the ball is drawn.
     */
    public void drawOn(DrawSurface surface) {

        // choose the color of this ball.
        surface.setColor(this.color);

        // fill the size of the ball with its color.
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.radius);

        surface.setColor(Color.BLACK);

        surface.drawCircle((int) this.getX(), (int) this.getY(), this.radius);
    }

    /**
     * A function that sets the velocity of the ball ("this") to the given velocity.
     * @param v a velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * A function that sets the velocity of the ball ("this") to the given velocity.
     * @param dx the change in position of the center point of the ball, on the `x` axe.
     * @param dy the change in position of the center point of the ball, on the `y` axe.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * A function that sets the surface size of the ball.
     * @param start the start point of the surface on which the ball is (the first of the hinges).
     * @param width The width (int) of the surface on which the ball is.
     * @param height The height (int) of the surface on which the ball is.
     */
    public void setSurfaceSize(Point start, int width, int height) {
        this.surfaceStart = start;
        this.surfaceWidth = width;
        this.surfaceHeight = height;
    }

    /**
     * a method which set the game environment of the ball.
     * @param environment - the game environment of the ball.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.env = environment;
    }

    /**
     * This method calculates the trajectory of the ball in one step according to its velocity.
     * @return a line - the trajectory of this ball.
     */
    public Line computeTrajectory() {

        // compute the end point of the trajectory.
        Point endPoint = this.velocity.applyToPoint(this.center);
        return new Line(this.center, endPoint);
    }

    /**
     * A function that moves the ball in one step, by the velocity of the ball.
     */
    public void moveOneStep() {
        if (this.velocity == null) {
            return;
        }

        // compute the trajectory of the ball.
        Line trajectory = this.computeTrajectory();

        // find a collisions of the ball in the game environment.
        CollisionInfo coll = this.env.getClosestCollision(trajectory);

        // if there is a collision, change the ball velocity when it occurs.
        if (coll != null) {
            Velocity v = new Velocity(-(this.velocity.getDx()), -(this.velocity.getDy()));
            this.center = v.applyToPoint(this.center);
            this.velocity = coll.collisionObject().hit(this, coll.collisionPoint(), this.velocity);
            return;
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * A method that checks whether this ball goes into the game's paddle, and if so, takes it out.
     */
    private void getOutFromPaddle() {
        Paddle paddle = this.env.getPaddle();
        if (paddle == null) {
            return;
        }

        // Get the shape of the paddle and its dimensions.
        Rectangle rec = paddle.getCollisionRectangle();
        double height = rec.getHeight();
        double width = rec.getWidth();

        // A case where this ball is inside the paddle.
        if (rec.getUpperLeft().getX() <= this.center.getX() && this.center.getX()
                <= rec.getUpperLeft().getX() + width && rec.getUpperLeft().getY() <= this.center.getY()
                && this.center.getY() <= rec.getUpperLeft().getY() + height) {

            // In case the ball is inside the paddle, we will move it slightly upwards to take it out.
            Velocity backwards = new Velocity(0, -OUT);
            this.center = backwards.applyToPoint(this.center);
        }
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
        this.getOutFromPaddle();
    }

    /**
     * Add this ball to the game.
     * @param game the game that this ball belongs to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * remove this ball from the game.
     * @param game the game that this ball belongs to.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
