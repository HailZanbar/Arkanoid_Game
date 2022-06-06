// ID 322766353
package levels;

import collision.Velocity;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Hail Zan Bar
 * AbstractLevel Represents abstract level information.
 */
public abstract class AbstractLevel implements LevelInformation {

    private static final int INITIAL_ANGLE = 270;

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new LinkedList<>();

        // Initially angles each ball so that the balls bounce off the paddle symmetrically.
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Velocity v = Velocity.fromAngleAndSpeed(((180 / (this.numberOfBalls() + 1)) * (i + 1))
                    + INITIAL_ANGLE, this.ballsSpeed());
            velocities.add(v);
        }
        return velocities;
    }
}
