package levels;

import game.LevelInformation;
import geomerty.Line;
import geomerty.Point;
import geomerty.Rectangle;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;
import tools.Circle;
import tools.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirstLevel implements LevelInformation {
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
    private static final int WIDTH_BLOCK = 30;
    private static final int HEIGHT_BLOCK = 30;
    private static final int BALL_SPEED = 3;

    public FirstLevel() {
        this.numberOfBalls = 1;
        //create the balls Velocity
        this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(-90, BALL_SPEED));
        //paddle
        this.paddleSpeed = BALL_SPEED;
        this.paddleWidth = WIDTH_BLOCK * 3;
        this.levelName = "First Level";
        //drow the objects to the level.
        this.background = this.levelBackground();
        //create the Blocks pattern
        Random rand = new Random(); // create a random-number generator
        this.numberOfBlocksToRemove = 1;
        Block object = new Block(new Rectangle(new Point(WIDTH / 2 - WIDTH_BLOCK / 2, HEIGHT / 3)
                , WIDTH_BLOCK, HEIGHT_BLOCK), 1, Color.cyan);
        this.blocks.add(object);
        this.background = levelBackground();
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

    public Sprite levelBackground() {
        List<Sprite> objects = new ArrayList<>();
        //color background
        Block backgroundBlock = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT), Color.black);
        objects.add(backgroundBlock);
        //create the circles.
        int radiuse = WIDTH_BLOCK + 20;
        for (int i = 0; i < 3; i++) {
            Circle newCircle = new Circle(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2, radiuse, Color.green,
                    false);
            objects.add(newCircle);
            radiuse += 30;
        }
        //create the lines
        Line up = new Line(new Point(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2),
                new Point(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2), Color.green);
        Line down = new Line(new Point(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2),
                new Point(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2), Color.green);
        Line right = new Line(new Point(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2),
                new Point(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2), Color.green);
        Line left = new Line(new Point(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2),
                new Point(WIDTH / 2, HEIGHT / 3 + HEIGHT_BLOCK / 2), Color.green);
        //up
        up.getStart().setY(up.getStart().getY() - WIDTH_BLOCK);
        up.getEnd().setY(up.getStart().getY() - radiuse + WIDTH_BLOCK);
        objects.add(up);
        //down
        down.getStart().setY(up.getStart().getY() + WIDTH_BLOCK * 2);
        down.getEnd().setY(up.getStart().getY() + radiuse + WIDTH_BLOCK);
        objects.add(down);
        //right
        right.getStart().setX(up.getStart().getX() + WIDTH_BLOCK);
        right.getEnd().setX(up.getStart().getX() + radiuse);
        objects.add(right);
        //left
        left.getStart().setX(up.getStart().getX() - WIDTH_BLOCK);
        left.getEnd().setX(up.getStart().getX() - radiuse);
        objects.add(left);
        DrowBackground backgroundLevel = new DrowBackground(objects);
        return backgroundLevel;
    }
}
