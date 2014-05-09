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
public class Mov extends Instr<Mov> {

    public static Mov mov() {
        return new Mov();
    }

    public Mov() {
        this(null);
    }

    public Mov(String label) {
        super(label);
    }

    @Override
    public Mov withLabel(String label) {
        return new Mov(label);
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
