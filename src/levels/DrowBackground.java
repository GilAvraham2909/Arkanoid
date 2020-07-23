package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.util.List;

/**
 * The type Drow background.
 */
public class DrowBackground implements Sprite {
    private List<Sprite> objectsList;

    public DrowBackground(List<Sprite> objectsList) {
        this.objectsList = objectsList;
    }

    @Override
    public void drawOn(DrawSurface d) {
        for(Sprite object : this.objectsList){
            object.drawOn(d);
        }
    }

    @Override
    public void timePassed() {
        return;
    }
}
