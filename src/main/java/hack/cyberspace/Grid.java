package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Grid {
    private final Cell[][] cells;

    public Grid(Cell[][] cells) {
        if (cells == null)
            throw new IllegalArgumentException("Cells cannot be null");
        for (Cell[] rows : cells) {
            if (rows == null) {
                throw new IllegalArgumentException("Cells cannot contain null row");
            }
        }

        this.cells = cells;
    }

    public Cell cellAt(Address address) {
        int y = address.y();
        if (y >= 0 && y < cells.length) {
            Cell[] row = cells[y];
            int x = address.x();
            if (x >= 0 && x < row.length)
                return row[x];
        }
        throw new SegFaultException(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Grid g = (Grid) o;
        if (g.cells.length != cells.length)
            return false;

        for (int i = 0; i < cells.length; i++) {
            Cell[] row0 = cells[i];
            Cell[] row1 = g.cells[i];
            if (row0.length != row1.length)
                return false;

            for (int j = 0; j < row0.length; j++) {
                Cell c0 = row0[j];
                Cell c1 = row1[j];
                if (!c0.equals(c1))
                    return false;
            }
        }

        // still there ;)
        return true;
    }

    @Override
    public int hashCode() {
        int h = 31;
        for (Cell[] row : cells) {
            for (Cell c : row) {
                h = 17 * h + c.hashCode();
            }
        }
        return h;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("[");
        for (int i = 0; i < cells.length; i++) {
            Cell[] row = cells[i];

            if (i > 0)
                b.append(", ");

            b.append("[");
            for (int j = 0; j < row.length; j++) {
                Cell cell = row[j];
                if (j > 0)
                    b.append(", ");
                b.append(cell);
            }
            b.append("]");
        }
        b.append("]");

        return b.toString();
    }
}