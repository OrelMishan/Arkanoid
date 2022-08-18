/*
 * Orel Mishan
 * 316551092
 */

/**
 * @author Orel Mishan
 * counter is object that count things.
 */
public class Counter {
    private int count = 0;

    /**
     * @param number that need add to count
     */
    void increase(int number) {
        this.count += number;
    }

    /**
     * @param number to subtract from count
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * @return the count
     */
    int getValue() {
        return this.count;
    }
}