package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    // If the key has been pressed.
    private boolean keyHasBeenPressed = false;
    // If the key is pressed right now.
    private boolean isAlreadyPressed = true;

    /**
     * @param sensor    - the function gets the keyboard of the game.
     * @param key       - the function gets the key which should press to exit.
     * @param animation - the function holds animation member.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * @param d  - the function gets drawsurface to draw on the screen one frame.
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (sensor.isPressed(key) && !isAlreadyPressed) {
            keyHasBeenPressed = true;
            isAlreadyPressed = false;
        }
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
            keyHasBeenPressed = false;
        }

    }

    /**
     * @return - returns true if the animation should stop and false otherwise.
     */
    public boolean shouldStop() {
        if (keyHasBeenPressed) {
            // For next generation.
            keyHasBeenPressed = false;
            return true;
        }
        return animation.shouldStop();
    }
}


