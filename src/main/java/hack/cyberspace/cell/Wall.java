package hack.cyberspace.cell;

import hack.cyberspace.Cell;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Wall extends Cell {
    @Override
    public boolean canMoveTo() {
        return false;
    }
}
