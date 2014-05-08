package hack.cyberspace;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Cell {

    private List<String> tags = new ArrayList<>();

    public boolean canMoveTo() {
        return true;
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
}
