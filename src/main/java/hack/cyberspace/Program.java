package hack.cyberspace;

import hack.cyberspace.instr.UnTag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Program {
    private List<Instr> preInstrs = new ArrayList<>();
    private List<Instr> instrs = new ArrayList<>();
    private int nextInstr = 0;
    private Address location;
    private Direction direction = Direction.North;


    public Program addPreInstr(Instr instr) {
        preInstrs.add(instr);
        return this;
    }

    public Program addInst(Instr instr) {
        instrs.add(instr);
        return this;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Grid executeNextInstruction(Grid grid) {
        Grid lastGrid = grid;
        for(Instr instr : preInstrs) {
            lastGrid = executeInstr(lastGrid, instr);
        }

        Instr instr = instrs.get(nextInstr++ % instrs.size());
        return executeInstr(lastGrid, instr);
    }

    private Grid executeInstr(Grid grid, Instr instr) {
        InstrExecution execution = instr.execute(new InstrContext(location, grid, direction));
        if (execution.isValid()) {
            this.location = execution.getAddress();
            this.direction = execution.getDirection();
            return execution.getGrid();
        }
        throw new IllegalInstructionException(instr);
    }

    @Override
    public String toString() {
        return "Program{" +
                "instr=" + nextInstr +
                ", location=" + location +
                ", direction=" + direction +
                '}';
    }

}
