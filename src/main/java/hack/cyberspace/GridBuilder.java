package hack.cyberspace;

import hack.cyberspace.cell.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class GridBuilder {
    private final List<Cell[]> rows = new ArrayList<>();

    public GridBuilder appendRow(Cell... cells) {
        rows.add(cells);
        return this;
    }

    public Grid create() {
        return new Grid(rows.toArray(new Cell[rows.size()][]));
    }

    public GridBuilder parse(String grid) {
        String[] rows = grid.split("\n");
        for (String row : rows) {
            Cell[] cells = new Cell[row.length()];
            for (int i = 0; i < row.length(); i++) {
                char c = row.charAt(i);
                if (Character.isLetter(c)) {
                    cells[i] = new Cell();
                    if (Character.isUpperCase(c)) {
                        cells[i].tag("star");
                    }
                    cells[i].tag("color:" + Character.toLowerCase(c));
                } else {
                    cells[i] = new Wall();
                }
            }
            appendRow(cells);
        }
        return this;
    }
}
