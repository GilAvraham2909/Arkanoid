package animations;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.*;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int countFramesTillStop = 0;

    //constractor
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    public void doOneFrame(DrawSurface d) {
        // Draw all the sprites.
        this.gameScreen.drawAllOn(d);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // Set the color of the count down.
        d.setColor(new Color(167, 126, 255));
        if (countFrom != 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 70);
        } else d.drawText(d.getWidth() / 2 - 35, d.getHeight() / 2, "Go!", 70);

        // Skip the first waiting.
        if (countFramesTillStop == 0) {
            countFramesTillStop++;
        } else {
            sleeper.sleepFor((int) numOfSeconds * 1000 / 4);
            countFrom--;
        }
    }

    public boolean shouldStop() {
        if (this.countFrom < -1) {
            return true;
        } else return false;
    }
}