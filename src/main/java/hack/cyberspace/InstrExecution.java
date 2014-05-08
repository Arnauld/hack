package hack.cyberspace;

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

    public InstrExecution(Address address, Grid grid, Direction direction) {
        this.address = address;
        this.grid = grid;
        this.direction = direction;
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
}
