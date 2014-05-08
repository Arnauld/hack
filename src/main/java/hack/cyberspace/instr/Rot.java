package hack.cyberspace.instr;

import hack.cyberspace.Direction;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Rot extends Instr {

    public enum Angle {
        Left,
        Right;

        public Direction applyOn(Direction direction) {
            switch (this) {
                case Left:
                    switch (direction) {
                        case North:
                            return Direction.West;
                        case South:
                            return Direction.East;
                        case West:
                            return Direction.South;
                        case East:
                            return Direction.North;
                    }
                case Right:
                    switch (direction) {
                        case North:
                            return Direction.East;
                        case South:
                            return Direction.West;
                        case West:
                            return Direction.North;
                        case East:
                            return Direction.South;
                    }
            }
            throw new UnsupportedOperationException("Unsupported combination (" + this + ", " + direction + ")");
        }
    }

    private final Angle angle;

    public Rot(Angle angle) {
        this.angle = angle;
    }

    @Override
    public InstrExecution execute(InstrContext context) {
        Direction direction = context.getDirection();
        Direction newDirection = angle.applyOn(direction);
        return new InstrExecution(context.getAddress(), context.getGrid(), newDirection);
    }

    @Override
    public String toString() {
        return "Rot(" + angle + ")";
    }
}
