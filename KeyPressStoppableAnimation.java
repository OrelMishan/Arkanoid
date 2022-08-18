/*
 * Orel Mishan
 * 316551092
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Orel Mishan
 * KeyPressStoppableAnimation is a decorator of animation that add stop key to the animation
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean stop = false;

    /**
     * constructor.
     *
     * @param sensor is the keyboard sensor


     * @param key is the stop key
     * @param animation to add him the stop key
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.ks.isPressed(key)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
