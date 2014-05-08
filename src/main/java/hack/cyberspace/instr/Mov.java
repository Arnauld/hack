package hack.cyberspace.instr;

import hack.cyberspace.Address;
import hack.cyberspace.Direction;
import hack.cyberspace.Grid;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Mov extends Instr {

    public Mov() {
    }

    @Override
    public InstrExecution execute(InstrContext context) {
        Address address = context.getAddress();
        Grid grid = context.getGrid();
        Direction direction = context.getDirection();

        Address next = direction.move(address);
        if (grid.cellAt(next).canMoveTo())
            return new InstrExecution(next, grid, direction);
        else
            return InstrExecution.invalidExecution();
    }

    @Override
    public String toString() {
        return "Mov";
    }
}
