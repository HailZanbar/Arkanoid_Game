// ID 322766353
package collision;

import geometry.Point;

/**
 * @author Hail Zan Bar
 * This class creates a velocity of a ball, which specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    // The change in position of the center point of the ball, on the `x` axe.
    private double dx;

    // The change in position of the center point of the ball, on the `y` axe.
    private double dy;

    /**
     * a constructor which creates a velocity.
     * @param dx The change in position of the center point of the ball, on the `x` axe.
     * @param dy The change in position of the center point of the ball, on the `y` axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This function ake a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p a point (a center point of a ball).
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * @return dx - The change of the velocity, in the position of a center point a the ball, on the `x` axe.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return dy - The change of the velocity, in the position of a center point a the ball, on the `y` axe.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * A function that sets the "delta x" of the velocity.
     * @param deltaX The change of the velocity, in the position of a center point a the ball, on the `x` axe.
     */
    public void setDx(double deltaX) {
        this.dx = deltaX;
    }

    /**
     * A function that sets the "delta y" of the velocity.
     * @param deltaY The change of the velocity, in the position of a center point a the ball, on the `y` axe.
     */
    public void setDy(double deltaY) {
        this.dy = deltaY;
    }

    /**
     * this function converts angle and apeed to dx and dy of a velocity, and creates and returns a new velocity
     * with these dx and dy.
     * @param angle The angle of the motion of the ball (double).
     * @param speed The speed of the motion of the ball (double).
     * @return a velocity with dx, dy according the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // convert the given angle to radians.
        double radAngle = Math.toRadians(angle);

        // convert the speed and the angle to a change in x axe.
        double dx = speed * Math.sin(radAngle);

        // convert the speed and the angle to a change in y axe.
        double dy = speed * (-Math.cos(radAngle));
        return new Velocity(dx, dy);
    }

    /**
     * compute the speed of this velocity, and returns it.
     * @return the speed of this velocity.
     */
    public double computeSpeed() {

        // A formula for calculating velocity according to the rates of dx and dy.
        double speed = Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
        return speed;

    }
}
