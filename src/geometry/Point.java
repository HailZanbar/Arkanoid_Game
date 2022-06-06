// ID 322766353
package geometry;
/**
 * @author Hail Zan Bar
 * This class creates a two-dimensional point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor of the point.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * function which return the distance of this point to the given other point.
     * @param other A point from which the distance from this point is measured.
     * @return double distance - the distance of this point to the other point.
     */
    public double distance(Point other) {

        // The coordinates of this point.
        double x1 = this.x, y1 = this.y;

        // The coordinates of the other point
        double x2 = other.x, y2 = other.y;

        // The root content in the distance formula.
        double sum = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        double root = Math.sqrt(sum);
        return root;
    }

    /**
     * function which check if this point and other point are equal.
     * @param other A point that is tested to be equal to this point.
     * @return boolean value - true if the given points are equal, false otherwise.
     */
    public boolean equals(Point other) {

        // Check if the coordinates of this point and the other are equal.
        double epsilon = Math.pow(10, -15);
        if ((Math.abs(this.x - other.getX()) < epsilon) && (Math.abs(this.y - other.getY()) < epsilon)) {
            return true;
        }
        return false;
    }

    /**
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
}
