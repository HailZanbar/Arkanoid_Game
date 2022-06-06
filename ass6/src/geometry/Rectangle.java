// ID 322766353
package geometry;
import java.util.ArrayList;

/**
 * @author Hail Zan Bar
 * This class creates a rectangle by upper left point, wudth and height.
 */
public class Rectangle {

    // Number of rectangle sides.
    private static final int LINES_NUM = 4;

    // The maximum amount of cutting points possible.
    private static final int INTER_POINTS_NUM = 2;


    private Point upperLeft;
    private double width;
    private double height;

    /**
     * A constructor which create a new rectangle with location and width/height.
     * @param upperLeft The upper left point of the rectangle, indicating its location.
     * @param width The Width of rectangle.
     * @param height The length of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Find the points of intersection between the given line and the rectangle.
     * @param line The line that examines the points of intersection between it and the rectangle.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        // the points of the rectangle.
        Point p1 = new Point((this.upperLeft.getX() + this.width), this.upperLeft.getY());
        Point p2 = new Point((this.upperLeft.getX() + this.width), (this.upperLeft.getY() + this.height));
        Point p3 = new Point(this.upperLeft.getX(), (this.upperLeft.getY() + this.height));

        // an array of the lines which build the rectangle.
        ArrayList<Line> linesList = new ArrayList<Line>(LINES_NUM);
        linesList.add(new Line(this.upperLeft, p1));
        linesList.add(new Line(this.upperLeft, p3));
        linesList.add(new Line(p1, p2));
        linesList.add(new Line(p3, p2));

        // an array of the intersection points between the line and the rectangle.
        ArrayList<Point> pointsList = new ArrayList<Point>(INTER_POINTS_NUM);
        for (Line l : linesList) {
            Point intersect = l.intersectionWith(line);
            if (intersect != null) {
                pointsList.add(intersect);
            }
        }
        return pointsList;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * set the upper left point of the rectangle.
     * @param p the new upper left point.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}
