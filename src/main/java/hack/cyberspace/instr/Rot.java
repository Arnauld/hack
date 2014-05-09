package hack.cyberspace.instr;

import hack.cyberspace.Direction;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Rot extends Instr<Rot> {

    public static Rot left() {
        return new Rot(Angle.Left);
    }

    public static Rot right() {
        return new Rot(Angle.Right);
    }

    public enum Angle {
        Left {
            @Override
            public Direction applyOn(Direction direction) {
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
                throw new UnsupportedOperationException("Unsupported combination (" + this + ", " + direction + ")");
            }
        },
        Right {
            public Direction applyOn(Direction direction) {
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
                throw new UnsupportedOperationException("Unsupported combination (" + this + ", " + direction + ")");
            }
        };

        public abstract Direction applyOn(Direction direction);
    }

    private final Angle angle;

    public Rot(Angle angle) {
        this(angle, null);
    }

    public Rot(Angle angle, String label) {
        super(label);
        this.angle = angle;
    }

    @Override
    public Rot withLabel(String label) {
        return new Rot(angle, label);
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
