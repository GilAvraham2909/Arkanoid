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
import java.util.Random;

public class ForthLevel implements LevelInformation {

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
    private static final int WIDTH_BLOCK = 62;
    private static final int HEIGHT_BLOCK = 30;
    private static final int BALL_SPEED = 2;

    public ForthLevel() {
        this.numberOfBalls = 3;
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
        this.paddleWidth = WIDTH_BLOCK + 20;
        this.levelName = "First Level";
        this.background = this.levelBackground();
        //create the Blocks pattern
        Random rand = new Random(); // create a random-number generator
        float r;
        float g;
        float b;
        int rowNum = 5;
        int bloksPerRow = 12;
        int startX = WIDTH - BORDER_WIDTH;
        int startY = HEIGHT / 5;
        this.numberOfBlocksToRemove = rowNum * (2 * bloksPerRow + 1 - rowNum) / 2;
        Color[] rainbow = new Color[]{Color.BLUE, Color.cyan, Color.MAGENTA, Color.PINK, Color.yellow};
        int startHitValue = 5;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < bloksPerRow; j++) {
                Block object = new Block(new Rectangle(new Point((WIDTH - (BORDER_WIDTH + (WIDTH_BLOCK * (j + 1)))),
                        (startY + (HEIGHT_BLOCK * i))), WIDTH_BLOCK, HEIGHT_BLOCK), startHitValue, rainbow[i]);
                this.blocks.add(object);
            }
            startHitValue -= 1;
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

    public Sprite levelBackground() {
        List<Sprite> objects = new ArrayList<>();
        objects.add(new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT),
                0, new Color(29, 190, 255)));
        //rain&clouds 1
        Point center = new Point(WIDTH / 4, HEIGHT / 1.5);
        //rain
        for (int i = 0; i < 5; i++) {
            objects.add(new Line(new Point(center.getX() - (10 * i), center.getY()),
                    new Point(center.getX() - (30 * i), HEIGHT), Color.WHITE));
        }
        //create the one cloud
        objects.add(new Circle(center.getX() - 30, center.getY(), 30, new Color(140, 140, 140),
                true));
        objects.add(new Circle(center.getX() - 50, center.getY(), 30, new Color(110, 110, 110),
                true));
        objects.add(new Circle(center.getX() - 30, center.getY() + 15, 30, new Color(140, 140, 140)
                , true));
        objects.add(new Circle(center.getX() + 15, center.getY() + 20, 30, new Color(156, 156, 156)
                , true));
        objects.add(new Circle(center, 30, new Color(156, 156, 156), true));
        //rain&clouds 2
        center = new Point(WIDTH/1.2, HEIGHT / 1.3);
        //rain
        for (int i = 0; i < 5; i++) {
            objects.add(new Line(new Point(center.getX() - (10 * i), center.getY()),
                    new Point(center.getX() - (20 * i), HEIGHT), Color.WHITE));
        }
        //create the one cloud
        objects.add(new Circle(center.getX() - 20, center.getY(), 30, new Color(140, 140, 140),
                true));
        objects.add(new Circle(center.getX() - 30, center.getY(), 30, new Color(110, 110, 110),
                true));
        objects.add(new Circle(center.getX() - 40, center.getY() + 15, 30, new Color(140, 140, 140)
                , true));
        objects.add(new Circle(center.getX() + 20, center.getY() + 20, 30, new Color(156, 156, 156)
                , true));
        objects.add(new Circle(center.getX(), center.getY() + 5, 30, new Color(156, 156, 156)
                , true));

        DrowBackground backgroundLevel = new DrowBackground(objects);
        return backgroundLevel;
    }
}
