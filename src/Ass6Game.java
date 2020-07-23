import animations.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.LevelInformation;
import levels.FirstLevel;
import levels.ForthLevel;
import levels.SecondLevel;
import levels.TherdLevel;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FRAMESPERSECOND = 60;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arcanoid", WIDTH, HEIGHT);
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0) {
            levels.add(new FirstLevel());
            levels.add(new SecondLevel());
            levels.add(new TherdLevel());
            levels.add(new ForthLevel());
        } else {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("1")) {
                    levels.add(new FirstLevel());
                }
                if (args[i].equals("2")) {
                    levels.add(new SecondLevel());
                }
                if (args[i].equals("3")) {
                    levels.add(new TherdLevel());
                }
                if (args[i].equals("4")) {
                    levels.add(new ForthLevel());
                }
            }
        }
        GameFlow gf = new GameFlow(new AnimationRunner(gui, FRAMESPERSECOND), gui.getKeyboardSensor(), 7);
        gf.runLevels(levels);
    }
}
