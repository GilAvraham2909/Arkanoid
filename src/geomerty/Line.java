package geomerty;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * The type Line.
 */
public class Line implements Sprite {
    private Point start;
    private Point end;
    private Color color;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point point1 = new Point(x1, y1);
        Point point2 = new Point(x2, y2);
        this.start = point1;
        this.end = point2;
    }

    /**
     * Sets start.
     *
     * @param x the x
     * @param y the y
     */
    public void setStart(double x, double y) {
        this.start = new Point(x, y);
    }

    /**
     * Sets end.
     *
     * @param x the x
     * @param y the y
     */
    public void setEnd(double x, double y) {
        this.start = new Point(x, y);
    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Gets end.
     *
     * @return the end
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Sets end.
     *
     * @return the end
     */
    public Point setEnd() {
        return this.end;
    }

    /**
     * Length double.
     *
     * @return the length of the line
     */
    public Double length() {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        return (Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2))));
    }

    /**
     * Middle point.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(x, y);
        return (middle);
    }

    /**
     * Start point.
     *
     * @return the the start point of the line
     */
    public Point start() {
        return (this.start);
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return (this.end);
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        boolean isInRange = false;
        Point intersectionPoint = intersectionWith(other);
        if (intersectionPoint != null) {
            isInRange = true;
        }
        return (isInRange);
    }

    /**
     * Slope double.
     *
     * @return the double
     */
    public Double slope() {
        if (this.start.getX() == this.end.getX()) {
            return null;
        }
        return ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
    }

    /**
     * Intercept with y double.
     *
     * @return the double
     */
    public Double interceptWithY() {
        return (this.start.getY() - (this.start.getX() * slope()));
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersction;
        Double slope1 = slope();
        Double slope2 = other.slope();
        Double interceptX = null;
        Double interceptY = null;
        //cheek for vertical lines
        if ((slope1 == null) && (slope2 != null)) {
            interceptY = slope2 * this.end.getX() + other.interceptWithY();
            interceptX = this.end.getX();
        } else if ((slope1 != null) && (slope2 == null)) {
            interceptY = slope1 * other.end.getX() + interceptWithY();
            interceptX = other.end.getX();
        } else if ((slope1 == null) && (slope2 == null)) {
            return null;
        } else if ((slope1 != null) && (slope2 != null)) {
            interceptX = (this.interceptWithY() - other.interceptWithY()) / (slope2 - slope1);
            interceptY = slope2 * interceptX + other.interceptWithY();
        }
        //cheek if the point is in range.
        intersction = new Point(interceptX, interceptY);
        if (other.isInRange(intersction) && isInRange(intersction)) {
            return (intersction);
        }
        return null;
    }

    /**
     * cheek if the point is in the range of the line.
     *
     * @param intercept the intercept
     * @return the boolean
     */
    public boolean isInRange(Point intercept) {
        double x = (double) Math.round(intercept.getX() * 100) / 100;
        double y = (double) Math.round(intercept.getY() * 100) / 100;
        intercept = new Point(x, y);
        if (((Math.min(getStart().getX(), getEnd().getX())) <= intercept.getX())
                && ((Math.max(getStart().getX(), getEnd().getX())) >= intercept.getX())
                && ((Math.min(getStart().getY(), getEnd().getY())) <= intercept.getY())
                && ((Math.max(getStart().getY(), getEnd().getY())) >= intercept.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        boolean answer = false;
        if ((this.start.equals(other.start)) || (this.start.equals(other.end))) {
            if ((this.end.equals(other.start)) || (this.end.equals(other.end))) {
                answer = true;
            }
        }
        return (answer);
    }

    /**
     * Closest intersection to start of line point, otherwise If this
     * line does not intersect with the rectangle, return null.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        //find the closest Point
        Point closestPoint = intersections.get(0);
        for (Point intersection : intersections) {
            if (start.distance(intersection) < start.distance(closestPoint)) {
                closestPoint = intersection;
            }
        }
        return closestPoint;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start.getX(),(int) this.start.getY(),(int) this.end.getX(),(int) this.end.getY());
    }

    @Override
    public void timePassed() {
        return;
    }
}
