package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import geomerty.Line;
import geomerty.Point;
import tools.Border;
import tools.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Border border;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Boolean fill = true;


    /**
     * Instantiates a new Ball.
     *
     * @param center          the center
     * @param r               the r
     * @param color           the color
     * @param gameEnvironment the game environment
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    public Ball(Point center, int r, java.awt.Color color, Boolean fill) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.fill = fill;
    }

    /**
     * Set border.
     *
     * @param guiWidth  the gui width
     * @param guiHeight the gui height
     */
    public void setBorder(int guiWidth, int guiHeight) {
        this.border = new Border(0, 0, guiWidth, guiHeight);
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
        if (this.fill) {
            surface.setColor(this.color);
            surface.fillCircle(this.getX(), this.getY(), this.radius);
        }
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        //cheekBoundries();
        double directionX = -this.getVelocity().getDx() / Math.abs(this.getVelocity().getDx());
        double directionY = -this.getVelocity().getDy() / Math.abs(this.getVelocity().getDy());
        double changeX = Math.abs(getVelocity().getDx()) > 1 ? this.radius * directionX : 0;
        double changeY = Math.abs(getVelocity().getDy()) > 1 ? this.radius * directionY : 0;
        //create the line witch is the move of the ball.
        Line newLine = new Line(getX(), getY(), this.getX() + this.getVelocity().getDx() - changeX,
                this.getY() + this.getVelocity().getDy() - changeY);
        //get the collision point of the line with the game
        CollisionInfo collision = gameEnvironment.getClosestCollision(newLine);
        if (collision != null) {
            setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), getVelocity()));
        } else {
            //if the ball dose not touch any of the objects
            this.center = this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * cheekBoundries of the ball in the screen.
     */
    public void cheekBoundries() {
        //when the ball meet the upper border
        if ((this.getY() - this.getSize() + this.getVelocity().getDy()) <= this.border.getUp().start().getY()) {
            this.getVelocity().setDy(-this.getVelocity().getDy());
            this.center.setY(this.border.getUp().start().getY() + this.radius);
            this.center = this.velocity.applyToPoint(this.center);
        }
        //when the ball meet the lower border
        if ((this.getY() + this.getSize() + this.getVelocity().getDy()) >= this.border.getDown().start().getY()) {
            this.getVelocity().setDy(-this.getVelocity().getDy());
            this.center.setY(this.border.getDown().start().getY() - this.radius);
            this.center = this.velocity.applyToPoint(this.center);
        }
        //when the ball meet the left border
        if ((this.getX() - this.getSize() + this.getVelocity().getDx()) <= this.border.getLeft().start().getX()) {
            this.getVelocity().setDx(-this.getVelocity().getDx());
            this.center.setX(this.border.getLeft().start().getX() + this.radius);
            this.center = this.velocity.applyToPoint(this.center);
        }
        //when the ball meet the right border
        if ((this.getX() + this.getSize() + this.getVelocity().getDx()) >= this.border.getRight().start().getX()) {
            this.getVelocity().setDx(-this.getVelocity().getDx());
            this.center.setX(this.border.getRight().start().getX() - this.radius);
            this.center = this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * Time passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}