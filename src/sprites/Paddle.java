package sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geomerty.Line;
import geomerty.Point;
import geomerty.Rectangle;
import tools.Border;
import tools.Velocity;

import java.awt.Color;


/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private GUI gui;
    private Velocity velocity;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int WIDTH_BLOCK = 60;
    private static final int HEIGHT_BLOCK = 30;
    private static final int BORDER_WIDTH = 30;


    /**
     * Instantiates a new Paddle.
     *
     * @param paddle   the paddle
     * @param color    the color
     * @param gui      the gui
     * @param velocity the velocity
     */
    public Paddle(Rectangle paddle, Color color, GUI gui, Velocity velocity) {
        this.paddle = paddle;
        this.color = color;
        this.gui = gui;
        this.velocity = velocity;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (paddle.getUpperLeft().getX() - this.velocity.getDx() > BORDER_WIDTH) {
            paddle.getUpperLeft().setX(paddle.getUpperLeft().getX() - this.velocity.getDx());
        } else {
            paddle.getUpperLeft().setX(BORDER_WIDTH);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (paddle.getUpperLeft().getX() + this.velocity.getDx() + paddle.getWidth() < WIDTH - BORDER_WIDTH) {
            paddle.getUpperLeft().setX(paddle.getUpperLeft().getX() + this.velocity.getDx());
        } else {
            paddle.getUpperLeft().setX(WIDTH - paddle.getWidth() - BORDER_WIDTH);
        }
    }

    /**
     * Notify the sprite that time has passed.
     */
    public void timePassed() {
        this.keyboard = gui.getKeyboardSensor();
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }

    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int x = (int) this.paddle.getUpperLeft().getX();
        int y = (int) this.paddle.getUpperLeft().getY();
        d.fillRectangle(x, y, (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * Gets collision rectangle.
     *
     * @return the the "collision shape" of the object.
     */
    public geomerty.Rectangle getCollisionRectangle() {
        return new Rectangle(this.paddle.getUpperLeft(), this.paddle.getWidth(), 0);
    }


    /**
     * return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the Point
     * @param currentVelocity the Velocity
     * @return he new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Border paddleBorder = new Border((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        //if the Ball touch the left/Right border
        if (paddleBorder.getRight().isInRange(collisionPoint) || paddleBorder.getLeft().isInRange(collisionPoint)) {
            //System.out.println("FUCK");
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        //if the point hit the part1 with upper border
        Point newStart = paddle.getUpperLeft();
        Point newEnd = new Point(newStart.getX() + (paddle.getWidth() / 5), newStart.getY());
        Line newLine = new Line(newStart, newEnd);
        if (newLine.isInRange(collisionPoint)) {
            //System.out.println("1");
            return Velocity.fromAngleAndSpeed(-150, currentVelocity.getSpeed());
        }
        //if the point hit the part2 with upper border
        newStart = newEnd;
        newEnd = new Point(newStart.getX() + (paddle.getWidth() / 5), newStart.getY());
        newLine = new Line(newStart, newEnd);
        if (newLine.isInRange(collisionPoint)) {
            //System.out.println("2");
            return Velocity.fromAngleAndSpeed(-120, currentVelocity.getSpeed());
        }
        //if the point hit the part3 with upper border
        newStart = newEnd;
        newEnd = new Point(newStart.getX() + (paddle.getWidth() / 5), newStart.getY());
        newLine = new Line(newStart, newEnd);
        if (newLine.isInRange(collisionPoint)) {
            //System.out.println("3");
            return Velocity.fromAngleAndSpeed(-90, currentVelocity.getSpeed());
        }
        //if the point hit the part4 with upper border
        newStart = newEnd;
        newEnd = new Point(newStart.getX() + (paddle.getWidth() / 5), newStart.getY());
        newLine = new Line(newStart, newEnd);
        if (newLine.isInRange(collisionPoint)) {
            //System.out.println("4");
            return Velocity.fromAngleAndSpeed(-60, currentVelocity.getSpeed());
        }
        //if the point hit the part5 with upper border
        newStart = newEnd;
        newEnd = new Point(newStart.getX() + (paddle.getWidth() / 5), newStart.getY());
        newLine = new Line(newStart, newEnd);
        if (newLine.isInRange(collisionPoint)) {
            //System.out.println("5");
            return Velocity.fromAngleAndSpeed(-30, currentVelocity.getSpeed());
        }

        //if the ball touch the lower border, never going to happend
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
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
     * Sets paddle in the center of the GUI.
     */
    public void setPaddleCenter() {
        this.getCollisionRectangle().setUpperLeft((WIDTH / 2) - WIDTH_BLOCK, HEIGHT - HEIGHT_BLOCK);
    }
}