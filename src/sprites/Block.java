// ID 322766353
package sprites;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import collision.Velocity;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hail Zan Bar
 * This class creates a block by shape (rectangle) and color, for the game.
 * A block is a collidable and sprite object.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // the shape of the block.
    private Rectangle shape;

    // the color of the block.
    private java.awt.Color color;

    private List<HitListener> hitListeners;

    /**
     * A constructor which creates a block.
     * @param rec the rectangle which represents the shape of the block.
     * @param color the color of the block.
     */
    public Block(Rectangle rec, java.awt.Color color) {
        this.shape = rec;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point upperLeft = this.shape.getUpperLeft();

        // compute the upper right point of the block.
        Point upperRight = new Point((this.shape.getUpperLeft().getX() + this.shape.getWidth()),
                (this.shape.getUpperLeft().getY()));
        Point lowerLeft = new Point((this.shape.getUpperLeft().getX()),
                (this.shape.getUpperLeft().getY() + this.shape.getHeight()));
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        // Case where the collision point is on the top or bottom side of the block (not at the corners).
        if ((upperLeft.getX() < collisionPoint.getX()) && (collisionPoint.getX() < upperRight.getX())) {

            // In this case the vertical direction of the ball will change.
            newVelocity.setDy(-(newVelocity.getDy()));
        } else if (upperLeft.getY() < collisionPoint.getY() && collisionPoint.getY() < lowerLeft.getY()) {

            /*
            In case the point of collision on the vertical sides of the block,
            the horizontal direction of the ball will change.
            */
            newVelocity.setDx(-(newVelocity.getDx()));
        } else {

            /*
            In case the collision point is in one of the corners of the block,
            both the horizontal direction and the vertical direction of the ball will change.
            */
            newVelocity.setDy(-(newVelocity.getDy()));
            newVelocity.setDx(-(newVelocity.getDx()));
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {

        // choose the color of this block.
        d.setColor(this.color);

        // fill the size of the block with its color.
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());

        d.setColor(Color.BLACK);

        // draw the block on the surface.
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(), (int) this.shape.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * A method that adds this block to the given game.
     * @param game A game to which this block belongs.
     */
    public void addToGame(GameLevel game) {

        // add to the collidables list of the game.
        game.addCollidable(this);

        // add to the sprites list of the game.
        game.addSprite(this);
    }

    /**
     * remove this block from the game.
     * @param game the game that the block belongs to.
     */
    public void removeFromGame(GameLevel game) {

        // remove from the collidables list of the game.
        game.removeCollidable(this);

        // remove from the sprites list of the game.
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all listeners of this block about a hit event.
     * @param hitter the hitter ball
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
