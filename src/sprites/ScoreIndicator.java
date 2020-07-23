package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import tools.Counter;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Block theBlock;
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param theBlock the the bolck
     * @param score    the score
     */
    public ScoreIndicator(Block theBlock, int score) {
        this.theBlock = theBlock;
        this.score = new Counter(score);
    }

    /**
     * Sets score.
     *
     * @param newScore the score
     */
    public void setScore(int newScore) {
        this.score.setCount(newScore);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(167, 126, 255));
        int size = 20;
        int x = (int) this.theBlock.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.theBlock.getCollisionRectangle().getUpperLeft().getY();
        d.fillRectangle(x, y, (int) this.theBlock.getCollisionRectangle().getWidth(),
                (int) this.theBlock.getCollisionRectangle().getHeight());
        d.setColor(Color.black);
        d.drawRectangle(x, y, (int) this.theBlock.getCollisionRectangle().getWidth(),
                (int) this.theBlock.getCollisionRectangle().getHeight());
        d.setColor(Color.WHITE);
        d.drawText((x + (int) (this.theBlock.getCollisionRectangle().getWidth() / 3) - size / 4),
                (y + (int) (this.theBlock.getCollisionRectangle().getHeight() / 3) + size / 2),
                "Score: " + this.score.getValue(), size);

    }

    @Override
    public void timePassed() {
        return;
    }

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    public Counter getScore() {
        return this.score;
    }
}
