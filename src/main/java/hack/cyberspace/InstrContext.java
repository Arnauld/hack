package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class InstrContext {
    private final InstrSeq instrSeq;
    private final Address address;
    private final Grid grid;
    private final Direction direction;

    public InstrContext(InstrSeq instrSeq, Address address, Grid grid, Direction direction) {
        if (instrSeq == null)
            throw new IllegalArgumentException("No instrSeq defined");
        if (address == null)
            throw new IllegalArgumentException("No address defined");
        if (grid == null)
            throw new IllegalArgumentException("No grid defined");
        if (direction == null)
            throw new IllegalArgumentException("No direction defined");

        this.instrSeq = instrSeq;
        this.address = address;
        this.grid = grid;
        this.direction = direction;
    }

    public InstrSeq getInstrSeq() {
        return instrSeq;
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

    public Cell cell() {
        return grid.cellAt(address);
    }
}
