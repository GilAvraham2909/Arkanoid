package tools;

import geomerty.Line;

/**
 * The type Border.
 */
public class Border {
    private Line up;
    private Line down;
    private Line left;
    private Line right;

    /**
     * Instantiates a new Border.
     *
     * @param startX the start x
     * @param startY the start y
     * @param width  the width
     * @param height the height
     */
    public Border(int startX, int startY, int width, int height) {
        this.up = new Line(startX, startY, startX + width, startY);
        this.down = new Line(startX, startY + height, startX + width, startY + height);
        this.left = new Line(startX, startY, startX, startY + height);
        this.right = new Line(startX + width, startY, startX + width, startY + height);
    }

    /**
     * Gets up.
     *
     * @return the up
     */
    public Line getUp() {
        return this.up;
    }

    /**
     * Gets down.
     *
     * @return the down
     */
    public Line getDown() {
        return this.down;
    }

    /**
     * Gets left.
     *
     * @return the left
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * Gets right.
     *
     * @return the right
     */
    public Line getRight() {
        return this.right;
    }
}