// ID 322766353
package game;
import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import sprites.Paddle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Hail Zan Bar
 * This class creates a game envinroment that includes a A list of objects that can be collided,
 * and also a paddle, separately.
 */
public class GameEnvironment {

    // list of objects that can be collided in the game.
    private List<Collidable> collidableList;

    // the paddle in the game.
    private Paddle paddle;

    /**
     * A constructor which initials a game environment.
     */
    public GameEnvironment() {
        collidableList = new LinkedList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     * @param c a collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * @return the collidable list of this game environment.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * This method checks if there is a collision between objects in the environment of the game and the
     * given trajectory, and locates the accessible object closest to the start of the trajectory.
     * @param trajectory The trajectory of the ball with which we seeks a collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable findCollisionObject(Line trajectory) {

        List<Collidable> collidablesCopy = this.collidableList;
        // an array of the objects that have a collisions with the line
        List<Collidable> collisions = new ArrayList<Collidable>();
        for (Collidable object : collidablesCopy) {

            // if there are intersection points between the line and the collidable
            if (!(object.getCollisionRectangle().intersectionPoints(trajectory).isEmpty())) {
                collisions.add(object);
            }
        }

        // if there is no collisions
        if (collisions.isEmpty()) {
            return null;
        }

        // fond the closest object to the start of the line. start with the first object in the array.
        Collidable closestObj = collisions.get(0);
        for (Collidable object : collisions) {

            // if the distance between the current collidable is small than the minimal distance, this is the closest.
            if (object.getCollisionRectangle().getUpperLeft().distance(trajectory.start())
                    < closestObj.getCollisionRectangle().getUpperLeft().distance(trajectory.start())) {
                closestObj = object;
            }
        }
        return closestObj;
    }

    /**
     * This method finds the nearest collision point in the colliding object, and returns it.
     * @param trajectory The colliding ball trajectory.
     * @param collObject The object with which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point findCollisionPoint(Line trajectory, Collidable collObject) {

        // A case where there is no collision with any object.
        if (collObject == null) {
            return null;
        }

        // Find the nearest collision point.
        Point collisPoint = trajectory.closestIntersectionToStartOfLine(collObject.getCollisionRectangle());
        return collisPoint;
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object will not collide with any of
     * the collidables in this collection, return null. Else, return the information about the closest collision
     * that is going to occur.
     * @param trajectory The trajectory of the object moving in the direction of the collision (a Line).
     * @return if there is no collision - null. otherwise - the information about the closest collision
     * that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        Collidable collObject = findCollisionObject(trajectory);

        // if there is no collision, return null
        if (collObject == null) {
            return null;
        }
        Point collPoint = findCollisionPoint(trajectory, collObject);
        // Issue information about the collision (if any).
        CollisionInfo collInfo = new CollisionInfo(collPoint, collObject);
        return collInfo;
    }

    /**
     * set the paddle of the game.
     * @param p a paddle.
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * @return the paddle of the game.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * Removing a collidable from the collidables List of the game environment.
     * @param coll the collidable object we want to remove.
     */
    public void removeCollidable(Collidable coll) {
        this.collidableList.remove(coll);
    }
}
