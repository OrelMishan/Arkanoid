/*
 * Orel Mishan
 * 316551092
 */

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Orel Mishan
 * main function of the game
 */
public class Ass6Game {
    /**
     * @param args from the command line
     */
    public static void main(String[] args) {
        GUI gui = new GUI("ARKANOID", GameFlow.SCREEN_WIDTH, GameFlow.SCREEN_HEIGHT);
        KeyboardSensor ks = gui.getKeyboardSensor();
        List<LevelInformation> list = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            int x;
            try {
                x = Integer.parseInt(args[i]);
                if (x == 1) {
                    list.add(new LevelOne());
                } else if (x == 2) {
                    list.add(new LevelTwo());
                } else if (x == 3) {
                    list.add(new LevelThree());
                } else if (x == 4) {
                    list.add(new LevelFour());
                }
            } catch (Exception e) {
                continue;
            }
        }
        if (list.size() == 0) {
            list.add(new LevelOne());
            list.add(new LevelTwo());
            list.add(new LevelThree());
            list.add(new LevelFour());
        }
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, 60), ks, gui);
        gameFlow.runLevels(list);
        gui.close();
    }

//        AnimationRunner runner = new AnimationRunner(gui,60);
//        Animation a1 = new WinScreen(ks,new Counter());
//        Animation a2 = new PauseScreen(ks); // also an Animation
//        Animation a1k = new KeyPressStoppableAnimation(ks,"m", a1);
//        Animation a2k = new KeyPressStoppableAnimation(ks,"m", a2);
//        runner.run(a1k);
//        runner.run(a2k);


}
