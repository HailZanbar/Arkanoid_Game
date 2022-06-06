// ID 322766353
package geometry;
import java.util.ArrayList;

/**
 * @author Hail Zan Bar
 * This class creates a line between two points.
 */
public class Line {

    // the start point of the line.
    private Point start;

    // the end point of the line.
    private Point end;

    // An auxiliary point that serves as a start point simulator, to make it easier to find intersection points.
    private Point helpStart;

    // An auxiliary point that serves as a end point simulator, to make it easier to find intersection points.
    private Point helpEnd;

    // the incline of the line.
    private double incline;

    // The point of intersection between the line on which this line is located, and the Y axis.
    private double intersectWithY;

    /**
     * A constructor which creates a line.
     * @param start the start point of the line.
     * @param end the end point of the line.
     */
    public Line(Point start, Point end) {

        this.start = start;
        this.end = end;

        /*
        A case where the start and end points form a vertical line and the start point is lower,
        or a case where the start point is to the left of the end point.
        */
        if ((start.getX() == end.getX() && start.getY() <= end.getY()) || start.getX() < end.getX()) {
            this.helpStart = start;
            this.helpEnd = end;
        } else {
            this.helpEnd = start;
            this.helpStart = end;
        }

        // if the line is not vertical, this is the incline of the line.
        if (this.start.getX() != this.end.getX()) {

            // Formula for calculating the slope of a line between two points
            this.incline = ((start.getY() - end.getY()) / (start.getX() - end.getX()));
        } else {

            // if the line is vertical, the incline of the line is infinity.
            this.incline = Double.POSITIVE_INFINITY;
        }

        // The point of intersection between the line on which this line is located, and the Y axis.
        this.intersectWithY = (start.getY() - (this.incline) * (start.getX()));
    }

    /**
     * A constructor which creates a line.
     * @param x1 the x value of the start point of the line.
     * @param y1 the y value of the start point of the line.
     * @param x2 the x value of the end point of the line.
     * @param y2 the y value of the end point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

        /*
        A case where the start and end points form a vertical line and the start point is lower,
        or a case where the start point is to the left of the end point.
        */
        if ((x1 == x2 && y1 <= y2) || x1 < x2) {
            this.helpStart = new Point(x1, y1);
            this.helpEnd = new Point(x2, y2);
        } else {
            this.helpEnd = new Point(x1, y1);
            this.helpStart = new Point(x2, y2);
        }

        // if the line is not vertical, this is the incline of the line.
        if (x1 != x2) {

            // Formula for calculating the slope of a line between two points
            this.incline = ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
        } else {

            // if the line is vertical, the incline of the line is infinity.
            this.incline = Double.POSITIVE_INFINITY;
        }
        // The point of intersection between the line on which this line is located, and the Y axis.
        this.intersectWithY = (this.start.getY() - (this.incline) * (this.start.getX()));
    }

    /**
     * function which return the length of this line.
     * @return double length - the length of the line.
     */
    public double length() {
        double len = (this.start).distance(this.end);
        return len;
    }

    /**
     * function which calculate the middle point of this line and return it.
     * @return Point middle - the middle point of the line.
     */
    public Point middle() {

        // Calculate the midpoint by a midline segment formula.
        double xMiddle = ((this.start.getX() + this.end.getX()) / 2);
        double yMiddle = ((this.start.getY() + this.end.getY()) / 2);
        Point middlePoint = new Point(xMiddle, yMiddle);
        return middlePoint;
    }

    /**
     * @return Point start - the start point of this line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return Point end - the end point of this line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * A function that finds an intersection point between two lines with different non-infinite inclines.
     * @param l1 a line
     * @param l2 a line
     * @return a Point - the intersection point between the given lines if there is, or null if not.
     */
    private static Point findInterWithIncline(Line l1, Line l2) {

        // Find the x value of the intersection point by solving the equation between the two equations of the lines.
        double intersectX = ((l2.intersectWithY - l1.intersectWithY) / (l1.incline - l2.incline));

        // Find the y value of the intersection point by placing the X in the line equation of the first line.
        double intersectY = (l1.incline) * intersectX + l1.intersectWithY;

        // If the point obtained is within range of each of the lines - we will return it.
        if ((l1.helpStart.getX() <= intersectX && intersectX <= l1.helpEnd.getX())
                && (l2.helpStart.getX() <= intersectX && intersectX <= l2.helpEnd.getX())) {
            return new Point(intersectX, intersectY);
        }
        return null;
    }

    /**
     * A function that finds an intersection point between two lines with equal non-infinite inclines.
     * @param l1 a line
     * @param l2 a line
     * @return a Point - the intersection point between the given lines if there is, or null if not.
     */
    private static Point findInterWithEqualIncline(Line l1, Line l2) {

        // A case where the start point of the first line is the same as the end point of the other
        if ((l1.helpStart).equals(l2.helpEnd)) {
            return (l1.helpStart);
        } else if ((l1.helpEnd).equals(l2.helpStart)) {

            // A case where the end point of the first line is the same as the start point of the other
            return (l2.helpStart);
        }
        return null;
    }

    /**
     * A function that finds an intersection point between a vertical line and non-vertical line.
     * @param vertLine a vertical line
     * @param line a non-vertical line
     * @return a Point - the intersection point between the given lines if there is, or null if not.
     */
    private static Point findInterWithVerticalLine(Line vertLine, Line line) {
        double intersectX = vertLine.helpStart.getX();

        // Find the y value of the intersection point by placing the X in the line equation of the regular line.
        double intersectY = (line.incline) * (vertLine.helpStart.getX()) + line.intersectWithY;

        // If the point obtained is within range of each of the lines - we will return it.
        if ((line.helpStart.getX() <= intersectX && intersectX <= line.helpEnd.getX())
                && (vertLine.helpStart.getY() <= intersectY && intersectY <= vertLine.helpEnd.getY())) {
            return new Point(intersectX, intersectY);
        }
        return null;
    }

    /**
     * A function that finds an intersection point between two vertical lines with infinite inclines.
     * @param l1 a vertical line
     * @param l2 a vertical line
     * @return a Point - the intersection point between the given lines if there is, or null if not.
     */
    private static Point findInterWithTwoVerticals(Line l1, Line l2) {

        // A case where the first line is a point or a case where the second line is a point
        if ((l1.helpStart).equals(l1.helpEnd)) {
            if (l2.helpStart.getY() <= l1.helpStart.getY() && l1.helpStart.getY() <= l2.helpEnd.getY()) {
                return l1.helpStart;
            }
        } else if ((l2.helpStart).equals(l2.helpEnd)) {
            if (l1.helpStart.getY() <= l2.helpStart.getY() && l2.helpStart.getY() <= l1.helpEnd.getY()) {
                return l2.helpStart;
            }
        } else {

            // Otherwise, we will check using the function that finds a intersection between lines with the same slope.
            return findInterWithEqualIncline(l1, l2);
        }
        return null;
    }

    /**
     * a function which Returns true if the lines (this, other) intersect, false otherwise.
     * @param other a line
     * @return boolean value - true if the given lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }

        // find the intersection point between the lines by auxiliary function.
        Point intersect = this.intersectionWith(other);

        // if there is no intersection between the lines (the intersection is null), return false
        if (intersect == null) {
            return false;
        }
        return true;
    }

    /**
     * a function that finds the intersection point between this line and other line, and returns it if it exist,
     * or returns null if the point does not exist.
     * @param other a line
     * @return a Point - the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        double epsilon = Math.pow(10, -15);

        // A case that the given lines are with different non-infinite inclines.
        if (this.incline != other.incline && this.incline != Double.POSITIVE_INFINITY
                && other.incline != Double.POSITIVE_INFINITY) {
            return findInterWithIncline(this, other);
        }

        // A case that the given lines are with equal non-infinite inclines.
        if (Math.abs(this.incline - other.incline) < epsilon && this.incline != Double.POSITIVE_INFINITY
                && other.incline != Double.POSITIVE_INFINITY) {
            return findInterWithEqualIncline(this, other);
        }

        // A case that this line is vertical, and the other line is not.
        if (this.incline != other.incline && this.incline == Double.POSITIVE_INFINITY) {
            return findInterWithVerticalLine(this, other);
        }

        // A case that the other line is vertical, and the this line is not.
        if (this.incline != other.incline && other.incline == Double.POSITIVE_INFINITY) {
            return findInterWithVerticalLine(other, this);
        }

        // A case that both the given lines are vertical.
        if (Math.abs(this.incline - other.incline) < epsilon && this.incline == Double.POSITIVE_INFINITY
                && other.incline == Double.POSITIVE_INFINITY) {
            return findInterWithTwoVerticals(this, other);
        }
        return null;
    }

    /**
     * a function that checks if this line and an other line are equal, and returns a boolean value accordingly.
     * @param other a line
     * @return a boolean value - true if the given lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }

        // If the start and end points of the two lines are equal (not necessarily respectively), we will return true.
        if (((this.helpStart).equals(other.helpStart) && (this.helpEnd).equals(other.helpEnd))
                || ((this.helpStart).equals(other.helpEnd) && (this.helpEnd).equals(other.helpStart))) {
            return true;
        }
        return false;
    }

    /**
     * A method which return a point: If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect a Rectangle with which to find points of intersection.
     * @return null - If this line does not intersect with the rectangle. Otherwise, return the closest
     * intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        // An array of intersection points between the line and the rectangle.
        ArrayList<Point> pointsList = (ArrayList<Point>) rect.intersectionPoints(this);
        if (pointsList.isEmpty()) {
            return null;
        }

        // we choose the first point in the array to be the closest point to the start of the line
        Point closestP = pointsList.get(0);
        for (Point p : pointsList) {

            // If the current intersection point is closer to the start of the line, we will set it to be the closest.
            if (p.distance(this.start) < closestP.distance(this.start)) {
                closestP = p;
            }
        }
        return closestP;
    }
}
