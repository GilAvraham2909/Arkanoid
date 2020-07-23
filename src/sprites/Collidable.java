package sprites;

import geomerty.Point;
import tools.Velocity;


/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the the "collision shape" of the object.
     */
    geomerty.Rectangle getCollisionRectangle();

    /**
     * return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the Point
     * @param currentVelocity the Velocity
     * @return he new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
