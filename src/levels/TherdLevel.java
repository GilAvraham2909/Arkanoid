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

public class TherdLevel implements LevelInformation {
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
    private static final int WIDTH_BLOCK = 60;
    private static final int HEIGHT_BLOCK = 30;
    private static final int BALL_SPEED = 2;

    public TherdLevel() {
        this.numberOfBalls = 2;
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
        this.paddleWidth = WIDTH_BLOCK * 2;
        this.levelName = "First Level";
        this.background = this.levelBackground();
        //create the Blocks pattern
        Random rand = new Random(); // create a random-number generator
        float r;
        float g;
        float b;
        int rowNum = 6;
        int bloksPerRow = 9;
        int startX = WIDTH - BORDER_WIDTH;
        int startY = HEIGHT / 5;
        this.numberOfBlocksToRemove = rowNum * (2 * bloksPerRow + 1 - rowNum) / 2;
        for (int i = 0; i < rowNum; i++) {
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
            for (int j = 0; j < bloksPerRow; j++) {
                Block object;
                if (i == 0) {
                    object = new Block(new Rectangle(new Point((WIDTH - (BORDER_WIDTH + (WIDTH_BLOCK * (j + 1)))),
                            (startY + (HEIGHT_BLOCK * i))), WIDTH_BLOCK, HEIGHT_BLOCK), 2, new Color(r, g, b));
                } else {
                    object = new Block(new Rectangle(new Point((WIDTH - (BORDER_WIDTH + (WIDTH_BLOCK * (j + 1)))),
                            (startY + (HEIGHT_BLOCK * i))), WIDTH_BLOCK, HEIGHT_BLOCK), 1, new Color(r, g, b));
                }
                this.blocks.add(object);
            }
            bloksPerRow--;
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
        //background
        objects.add(new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT),
                new Color(20, 145, 36)));
        //create the tower
        objects.add(new Block(new Rectangle(new Point(WIDTH / 8, WIDTH / 2), WIDTH / 4 ,
                HEIGHT + 10), Color.black));
        int perRow = 6;
        int numRow = 4;
        int startX = WIDTH / 8 + 15;
        int startY = WIDTH / 2 +10;
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < perRow; j++) {
                objects.add(new Block(new Rectangle(new Point((startX + (j * 20) + (j * 10)),
                        (startY + (i * 40) + (i * 10))), 20, 40), Color.white));
            }
        }
        //
        objects.add(new Block(new Rectangle(new Point(WIDTH / 8 + 50 , WIDTH / 2 - 30), 100 ,
                30), Color.darkGray));
        objects.add(new Block(new Rectangle(new Point(WIDTH / 8 + 95 , WIDTH / 2 - 160), 10 ,
                130), Color.gray));
        Point center = new Point(WIDTH / 8 + 100 , WIDTH / 2 - 170);
        objects.add(new Circle(center,12, new Color(255, 149, 27), true));
        objects.add(new Circle(center,15, new Color(255, 215, 116), true));
        objects.add(new Circle(center,10, new Color(255, 255, 19), true));
        DrowBackground backgroundLevel = new DrowBackground(objects);
        return backgroundLevel;
    }
}
