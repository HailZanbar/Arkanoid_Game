// ID 322766353
package game;

/**
 * @author Hail Zan Bar
 * A counter that supports increase, decrease and returning count actions.
 */
public class Counter {

    // the number in the counter
    private int count;

    /**
     * Constructor of the counter.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     * @param number the number we want to add to the count.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * ubtract number from current count.
     * @param number the number we want to reduce from the count.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     * @return the current count.
     */
    public int getValue() {
        return this.count;
    }
}
