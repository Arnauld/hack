package hack.cyberspace;

import java.util.Optional;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class InstrExecution {

    public static InstrExecution invalidExecution() {
        return new InstrExecution(null, null, null);
    }

    private final Address address;
    private final Grid grid;
    private final Direction direction;
    private final Optional<Integer> nextInstr;

    public InstrExecution(Address address, Grid grid, Direction direction) {
        this(address, grid, direction, Optional.empty());
    }

    public InstrExecution(Address address, Grid grid, Direction direction, Optional<Integer> nextInstr) {
        this.address = address;
        this.grid = grid;
        this.direction = direction;
        this.nextInstr = nextInstr;
    }

    public boolean isValid() {
        return address != null && grid != null && direction != null;
    }

    public Address getAddress() {
        return address;
    }

    public Grid getGrid() {
        return grid;
    }

    public Direction getDirection() {
        return direction;
    }

    public Optional<Integer> getNextInstr() {
        return nextInstr;
    }
}
