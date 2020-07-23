package levels;

import game.LevelInformation;
import geomerty.Line;
import geomerty.Point;
import geomerty.Rectangle;
import sprites.Block;
import sprites.Sprite;
import tools.Circle;
import tools.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Second level.
 */
public class SecondLevel implements LevelInformation {

    private int numberOfBalls;
    // List of velocity for each ball.
    private List<Velocity> initialBallVelocities = new ArrayList<>();
    // Paddle properties.
    private int paddleSpeed;
    private int paddleWidth;
    // The name of the level.
    private String levelName;
    // Level gui.
    private Sprite background;
    // List of all blocks in the game.
    private List<Block> blocks = new ArrayList<>();
    // args
    private int numberOfBlocksToRemove;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BORDER_WIDTH = 30;
    private static final int WIDTH_BLOCK = 74;
    private static final int HEIGHT_BLOCK = 30;
    private static final int BALL_SPEED = 3;
    private static final int BLOCKS_PER_ROW = 10;
    private static final int BLOCK_START_HIT = 1;


    /**
     * Instantiates a new Second level.
     */
    public SecondLevel() {
        this.numberOfBalls = 10;
        //create the balls Velocity
        double opningAngleOfBalls = 90;
        double angle = (180 - opningAngleOfBalls) / 2;
        double addOnAngle = opningAngleOfBalls / (numberOfBalls - 1);
        for (int i = 0; i < numberOfBalls; i++) {
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(-angle, BALL_SPEED));
            angle += addOnAngle;
        }
        //paddle
        this.paddleSpeed = BALL_SPEED;
        this.paddleWidth = WIDTH_BLOCK * 1;
        this.levelName = "Second Level";
        this.background = levelBackground();
        //create the Blocks pattern
        int r = 220, g = 20, b = 60;
        int startY = (HEIGHT / 5) * 3;
        this.numberOfBlocksToRemove = BLOCKS_PER_ROW;
        for (int i = 0; i < BLOCKS_PER_ROW; i++) {
            Block object = new Block(new Rectangle(new Point((BORDER_WIDTH + (WIDTH_BLOCK * i)), startY),
                    WIDTH_BLOCK, HEIGHT_BLOCK), BLOCK_START_HIT, new java.awt.Color(r, g, b));
            this.blocks.add(object);
            if (i % 2 == 0) {
                g += 40;
            }
        }
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Level background sprite.
     *
     * @return the sprite
     */
    public Sprite levelBackground() {
        List<Sprite> objects = new ArrayList<>();
        Point center = new Point(WIDTH / 5, HEIGHT / 4);
        //create the lines
        int endY = (HEIGHT / 5) * 3;
        for (int i = 0; i < 100; i++) {
            objects.add(new Line(center,new Point(BORDER_WIDTH + i* 7.4,endY),Color.ORANGE));
        }
        //the Sun
        int r = 242, g = 187, b = 114;
        int radius = 40 + 20 * 2;
        for (int i = 0; i < 3; i++) {
            objects.add(new Circle(center.getX(), center.getY(), radius, new Color(r, g, b), true));
            g += 20;
            b -= 20;
            radius -= 10;
        }
        DrowBackground backgroundLevel = new DrowBackground(objects);
        return backgroundLevel;
    }
}
