package geomerty;

/**
 * The type Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double x1 = this.x;
        double x2 = other.getX();
        double y1 = this.y;
        double y2 = other.getY();
        return (Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2))));
    }

    /**
     * Equals boolean.
     *
     * @param other the other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        boolean answer = false;
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            answer = true;
        }
        return answer;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return (this.x);
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return (this.y);
    }

    /**
     * Sets x.
     *
     * @param x1 the x 1
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * Sets y.
     *
     * @param y1 the y 1
     */
    public void setY(double y1) {
        this.y = y1;
    }

}
