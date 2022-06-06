// ID 322766353
package collision;

import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * @author Hail Zan Bar.
 * This interface represents the objects that can be collided in a game, by defining features
 * such as returning the shape of the object and returning a new velocity to the colliding object.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object (rectangle).
     */
    Rectangle getCollisionRectangle();

    /**
     * A method which notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint The point of collision between the object and the object that can be collided.
     * @param currentVelocity The current velocity of the object that collided with that object
     * @param hitter the ball which hit the object.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}