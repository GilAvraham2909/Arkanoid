package sprites;

import geomerty.Line;
import geomerty.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point intersectionpoint;
    private Collidable object;

    /**
     * Instantiates a new Collision info.
     *
     * @param intersection the intersection
     * @param object       the object
     */
    public CollisionInfo(Point intersection, Collidable object) {
        this.intersectionpoint = intersection;
        this.object = object;
    }

    /**
     * Collision point point.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return intersectionpoint;
    }

    /**
     * Collision point distanc double.
     *
     * @param line the line
     * @return the point at which the collision occurs.
     */
    public Double collisionPointDistanc(Line line) {
        Point intersect = collisionPoint();
        return line.getStart().distance(intersect);
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return object;
    }

}