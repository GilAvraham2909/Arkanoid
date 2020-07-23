package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import tools.Counter;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Block theBlock;
    private Counter lifeLeft;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param theBlock the the block
     * @param lifeLeft the life left
     */
    public LivesIndicator(Block theBlock, Counter lifeLeft) {
        this.theBlock = theBlock;
        this.lifeLeft = lifeLeft;
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
                "Life Left: " + this.lifeLeft.getValue(), size);

    }

    /**
     * Gets life left.
     *
     * @return the life left
     */
    public Counter getLifeLeft() {
        return lifeLeft;
    }

    @Override
    public void timePassed() {
        return;
    }

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
