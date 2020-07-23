package tools;

/**
 * The type Counter.
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     *
     * @param count the count
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Increase :add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Decrease:subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Gets current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }

    /**
     * Sets count.
     *
     * @param num the num
     */
    public void setCount(int num) {
        this.count = num;
    }
}