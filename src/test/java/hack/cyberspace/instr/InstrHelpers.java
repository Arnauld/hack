package hack.cyberspace.instr;

import hack.cyberspace.Address;
import hack.cyberspace.Cell;
import hack.cyberspace.Direction;
import hack.cyberspace.Grid;
import hack.cyberspace.InstrContext;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class InstrHelpers {
    public static final Address ADDR_ZERO = Address.address(0, 0);

    public static Grid newGrid2x2() {
        return new Grid(new Cell[][]{
                {new Cell(), new Cell()},
                {new Cell(), new Cell()}});
    }

    public static InstrContext basicContext() {
        return new InstrContext(ADDR_ZERO, newGrid2x2(), Direction.North);
    }
}
