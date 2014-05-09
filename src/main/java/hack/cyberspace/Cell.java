package hack.cyberspace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Cell {

    private int capacity = 1;
    private int occupation = 0;
    private List<String> tags = new ArrayList<>();

    public boolean canMoveTo() {
        return occupation < capacity;
    }

    public void tag(String tag) {
        tags.add(tag);
    }

    public Stream<String> tags() {
        return tags.stream();
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public boolean unTag(String tag) {
        return tags.remove(tag);
    }

    @Override
    public String toString() {
        return "Cell{" + tags + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;
        return tags.equals(cell.tags);
    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }

    public void onEnter() {
        if(!canMoveTo())
            throw new IllegalStateException("Cell is either a wall or already full");
        occupation++;
    }

    public void onLeave() {
        occupation--;
    }
}
