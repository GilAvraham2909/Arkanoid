package animations;

import animations.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {
    private int score;
    private boolean win;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     * @param score of game
     * @param win or not
     * @param k keyboard
     */
    public EndScreen(int score, boolean win, KeyboardSensor k) {
        this.score = score;
        this.win = win;
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * doing one frame.
     * @param d drawing surface
     */
    public void doOneFrame(DrawSurface d) {
        if (this.win) {
            d.drawText(10, d.getHeight() / 2, "You Win!\n Your score is: " + String.valueOf(this.score),
                    32);
        }
        if (!this.win) {
            d.drawText(10, d.getHeight() / 2, "Game Over.\n Your score is: " + String.valueOf(this.score),
                    32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * @return whether game is paused or not.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
