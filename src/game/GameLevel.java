package game;

import animations.*;
import biuoop.KeyboardSensor;
import geomerty.Point;
import geomerty.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprites.*;
import tools.BallRemover;
import tools.BlockRemover;
import tools.Counter;
import tools.ScoreTrackingListener;
import tools.Velocity;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private LevelInformation level;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private int score;
    private ScoreIndicator showScore;
    private LivesIndicator lifeLeft;
    private LevelIndicator levleName;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private static final int FRAMESPERSECOND = 60;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BORDER_WIDTH = 30;
    private static final int WIDTH_BLOCK = 60;
    private static final int HEIGHT_BLOCK = 30;
    private static final int BALLRADIUS = 5;
    private static final int BALLSNUM = 50;

    public GameLevel(LevelInformation level, GUI gui, int score, int lifeLeft,
                     KeyboardSensor keyboard) {
        this.level = level;
        this.gui = gui;
        this.score = score;
        this.lifeLeft = new LivesIndicator(new Block(new Rectangle(new Point(0, 0), WIDTH / 3,
                BORDER_WIDTH), Color.WHITE), new Counter(lifeLeft + 1));
        this.showScore = new ScoreIndicator(new Block(new Rectangle(new Point(WIDTH / 3, 0), WIDTH / 3
                , BORDER_WIDTH), Color.WHITE),score);
        this.keyboard = keyboard;
    }

    public int getScore() {
        return this.showScore.getScore().getValue();
    }

    public int getLifeLeft() {
        return lifeLeft.getLifeLeft().getValue();
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidable().remove(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        //set Background
        this.addSprite(this.level.getBackground());
        //truck the score
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.showScore.getScore());
        //create the Blocks pattern
        this.blockRemover = new BlockRemover(this, new Counter(this.level.numberOfBlocksToRemove()));
        for (Block object : this.level.blocks()) {
            object.addHitListener(scoreListener);
            object.addHitListener(this.blockRemover);
            object.addToGame(this);
        }
        //create the Walls.
        this.levleName = new LevelIndicator(new Block(new Rectangle(new Point((WIDTH / 3) * 2, 0), WIDTH / 3,
                BORDER_WIDTH), Color.WHITE), this.level.levelName());
        Block borderUp = new Block(new Rectangle(new Point(0, 0 + BORDER_WIDTH), WIDTH, BORDER_WIDTH), Color.gray);
        Block borderRight = new Block(new Rectangle(new Point(WIDTH - BORDER_WIDTH, BORDER_WIDTH),
                BORDER_WIDTH, HEIGHT - BORDER_WIDTH), Color.gray);
        Block borderLeft = new Block(new Rectangle(new Point(0, BORDER_WIDTH),
                BORDER_WIDTH, HEIGHT - BORDER_WIDTH), Color.gray);
        Block borderDown = new Block(new Rectangle(new Point(0, HEIGHT + 1), WIDTH, BORDER_WIDTH), Color.gray);
        this.ballRemover = new BallRemover(this, new Counter(BALLSNUM));
        borderDown.addHitListener(this.ballRemover);
        this.lifeLeft.addToGame(this);
        this.levleName.addToGame(this);
        this.showScore.addToGame(this);
        borderUp.addToGame(this);
        borderDown.addToGame(this);
        borderRight.addToGame(this);
        borderLeft.addToGame(this);
        this.running = !shouldStop();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        //create the Paddle
        this.createBallsOnTopOfPaddle();
        Paddle paddle = new Paddle(new Rectangle(new Point((WIDTH / 2) - this.level.paddleWidth() / 2,
                HEIGHT - HEIGHT_BLOCK), this.level.paddleWidth(), 20),
                new Color(167, 126, 255), gui, new Velocity(this.level.paddleSpeed(), 0));
        paddle.addToGame(this);
        this.runner = new AnimationRunner(this.gui, FRAMESPERSECOND);
        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites));
        this.runner.run(this);
        paddle.removeFromGame(this);
    }

    /**
     * Run.
     */
    public void run() {
        while (true) {
            if (this.lifeLeft.getLifeLeft().getValue() == 0) {
                return;
            } else if (this.blockRemover.getRemainingBlocks().getValue() == 0) {
                this.showScore.getScore().increase(100);
                return;
            }
            this.lifeLeft.getLifeLeft().decrease(1);
            playOneTurn();
        }

    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        if ((this.blockRemover.getRemainingBlocks().getValue() == 0)
                || (this.ballRemover.getRemainingBall().getValue() == 0)) {
            return true;
        }
        return false;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        this.keyboard = gui.getKeyboardSensor();
        this.showScore.setScore(this.showScore.getScore().getValue());
        this.sprites.drawAllOn(d);
        if (this.keyboard.isPressed("p")) {
            PauseScreen pauseScreen = new PauseScreen(this.keyboard);
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", pauseScreen));
           // this.runner.run(new PauseScreen(this.keyboard));
        }
        this.sprites.notifyAllTimePassed();
    }

    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        //create the Ball
        ArrayList<Ball> ballList = new ArrayList<>();
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball tempBall = new Ball(new Point(WIDTH / 2, HEIGHT - HEIGHT_BLOCK - 10), BALLRADIUS, Color.white,
                    this.environment);
            tempBall.setBorder(WIDTH, HEIGHT);
            tempBall.setVelocity(this.level.initialBallVelocities().get(i));
            ballList.add(tempBall);
        }
        this.ballRemover.getRemainingBall().setCount(this.level.numberOfBalls());
        for (Ball tempBall : ballList) {
            tempBall.addToGame(this);
        }
    }
}