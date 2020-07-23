package game;

import geomerty.Line;
import geomerty.Point;
import sprites.Collidable;
import sprites.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> objectList;

    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.objectList = new ArrayList<>();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c the Collidable
     */
    public void addCollidable(Collidable c) {
        this.objectList.add(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisionList = new ArrayList<>();
        if (this.objectList.isEmpty()) {
            return null;
        }
        //give a list of object wich intersect with the line
        for (Collidable object : this.objectList) {
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(object.getCollisionRectangle());
            if (intersectionPoint != null) {
                collisionList.add(new CollisionInfo(intersectionPoint, object));
            }
        }
        if (collisionList.isEmpty()) {
            return null;
        }
        //cheek the closest object witch is intersecting with the line
        CollisionInfo minCollidable = collisionList.get(0);
        for (CollisionInfo temp : collisionList) {

            if (temp.collisionPointDistanc(trajectory) < minCollidable.collisionPointDistanc(trajectory)) {
                minCollidable = temp;
            }
        }
        return minCollidable;
    }

    /**
     * Gets collidable list.
     *
     * @return the collidable
     */
    public List<Collidable> getCollidable() {
        return this.objectList;
    }
}