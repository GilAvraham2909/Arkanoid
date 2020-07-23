package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geomerty.Point;
import geomerty.Rectangle;
import tools.Border;
import tools.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private int maxHit;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Instantiates a new Block.
     *
     * @param rect   the rect
     * @param maxHit the max hit
     * @param color  the color
     */
    public Block(Rectangle rect, int maxHit, Color color) {
        this.rectangle = rect;
        this.maxHit = maxHit;
        this.color = color;
    }

    /**
     * Instantiates a new Block.
     *
     * @param rect  the rect
     * @param color the color
     */
    public Block(Rectangle rect, Color color) {
        this.rectangle = rect;
        this.maxHit = 0;
        this.color = color;
    }

    /**
     * Add one hit to the object.
     */
    public void addOneHit() {
        if (this.maxHit > 0) {
            this.maxHit -= 1;
        }
    }

    /**
     * The Get rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the Point
     * @param currentVelocity the Velocity
     * @return he new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Border rectBorder = rectangleBorder();
        //find how to change the velocity and notify the hit counter
        if (rectBorder.getUp().isInRange(collisionPoint) || rectBorder.getDown().isInRange(collisionPoint)) {
            addOneHit();
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (rectBorder.getRight().isInRange(collisionPoint) || rectBorder.getLeft().isInRange(collisionPoint)) {
            addOneHit();
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        //never going to happend.
        return null;
    }

    /**
     * Rectangle border border.
     *
     * @return the border
     */
    public Border rectangleBorder() {
        Border rectBorder = new Border((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        return rectBorder;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int size = 20;
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        d.fillRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.white);
    }

    /**
     * Time passed.
     */
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Instantiates a new Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * notifyHit.
     *
     * @param hitter the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Gets max hit.
     *
     * @return the max hit
     */
    public int getMaxHit() {
        return maxHit;
    }
}
