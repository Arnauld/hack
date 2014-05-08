package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Grid {
    private final Cell[][] cells;

    public Grid(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell cellAt(Address address) {
        if (address.y() < cells.length) {
            Cell[] row = cells[address.y()];
            if (address.x() < row.length)
                return row[address.x()];
        }
        throw new SegFaultException(address);
    }
}
