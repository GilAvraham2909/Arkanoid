package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import tools.Counter;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LevelIndicator implements Sprite {
    private Block theBlock;
    private String name;

    /**
     * Instantiates a new Level indicator.
     *
     * @param theBlock the the block
     * @param name     the name
     */
    public LevelIndicator(Block theBlock, String name) {
        this.theBlock = theBlock;
        this.name = name;
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
        d.drawText((x + (int) (this.theBlock.getCollisionRectangle().getWidth() / 3) -size*2),
                (y + (int) (this.theBlock.getCollisionRectangle().getHeight() / 3) + size / 2),
                "Level: " + this.name, size);

    }

    @Override
    public void timePassed() {
        return;
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
