// ID 322766353
package collision;
import geometry.Point;

/**
 * @author Hail Zan Bar
 * This class creates a collision information which hold information about the collision point between objects,
 * and the collision object.
 */
public class CollisionInfo {

    // The point of collision.
    private Point collPoint;

    // The colliding object.
    private Collidable collObject;

    /**
     * A constructor which creates a collision information.
     * @param collPoint the collision point.
     * @param collObject the collision object.
     */
    public CollisionInfo(Point collPoint, Collidable collObject) {
        this.collPoint = collPoint;
        this.collObject = collObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collObject;
    }
}
