package game;

import animations.AnimationRunner;
import animations.EndScreen;
import biuoop.KeyboardSensor;
import tools.Counter;


import java.util.List;


public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    // Count the score in the game.
    private Counter gameScore = new Counter(0);
    // Count the life of the player.
    private Counter numOfLife;

    public GameFlow(AnimationRunner ar,KeyboardSensor keyboardSensor, int numLife) {
        //create the GUI
        this.animationRunner = ar;
        this.keyboardSensor = keyboardSensor;
        this.numOfLife = new Counter(numLife);
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,this.animationRunner.getGui(),this.gameScore.getValue(),
                    this.numOfLife.getValue(), this.keyboardSensor);
            level.initialize();
            while(!level.shouldStop()){
                level.run();
            }
            this.numOfLife = new Counter(level.getLifeLeft());
            this.gameScore = new Counter(level.getScore());
            if (this.numOfLife.getValue() == 0) {
                this.animationRunner.run(new EndScreen(this.gameScore.getValue(), false, this.keyboardSensor));
                this.animationRunner.getGui().close();
                break;
            }
        }
        this.animationRunner.run(new EndScreen(this.gameScore.getValue(), true, this.keyboardSensor));
        this.animationRunner.getGui().close();
    }
}
