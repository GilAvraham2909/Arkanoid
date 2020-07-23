package sprites;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spiritListCopy = new ArrayList<Sprite>(this.spriteList);
        for (Sprite object : spiritListCopy) {
            object.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite object : this.spriteList) {
            object.drawOn(d);
        }
    }
}