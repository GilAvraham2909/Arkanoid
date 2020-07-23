package tools;

import geomerty.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        p.setX(this.dx + p.getX());
        p.setY(this.dy + p.getY());
        return p;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets dx.
     *
     * @param x the x
     */
    public void setDx(double x) {
        this.dx = x;
    }

    /**
     * Sets dy.
     *
     * @param y the y
     */
    public void setDy(double y) {
        this.dy = y;
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dy = speed * Math.sin(Math.toRadians(angle));
        double dx = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public Double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}