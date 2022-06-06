// ID 322766353
package collision;

/**
 * @author Hail Zan Bar
 * An interface that represents objects that notify when they are hit.
 */
public interface HitNotifier {

    /**
     * Add the given hit listener as a listener to hit events.
     * @param hl Hit Listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove the given hit listener from the list of listeners to hit events.
     * @param hl Hit Listener
     */
    void removeHitListener(HitListener hl);
}
