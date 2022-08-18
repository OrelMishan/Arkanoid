/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * HitNotifier is object that has listeres and informs them when something happend
 */

public interface HitNotifier {
    /**
     * add listener to notifier.
     *
     * @param hl is the listener
     */
    void addHitListener(HitListener hl);

    /**
     * remove listener to notifier.
     *
     * @param hl is the listener
     */
    void removeHitListener(HitListener hl);
}