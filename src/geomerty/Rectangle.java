package geomerty;

import tools.Border;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Sets upper left.
     *
     * @param x the x
     * @param y the y
     */
    public void setUpperLeft(double x, double y) {
        this.getUpperLeft().setX(x);
        this.getUpperLeft().setY(y);
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return Return a (possibly empty) List of intersection points ith the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        Point intersection = null;
        //get intersection point with upper border
        Border borderLine = new Border((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) getWidth(), (int) getHeight());
        intersection = borderLine.getUp().intersectionWith(line);
        if (intersection != null) {
            intersections.add(intersection);
        }
        //get intersection point with lower border
        intersection = borderLine.getDown().intersectionWith(line);
        if (intersection != null) {
            intersections.add(intersection);
        }
        //get intersection point with left border
        intersection = borderLine.getLeft().intersectionWith(line);
        if (intersection != null) {
            intersections.add(intersection);
        }
        //get intersection point with right border
        intersection = borderLine.getRight().intersectionWith(line);
        if (intersection != null) {
            intersections.add(intersection);
        }
        return intersections;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}