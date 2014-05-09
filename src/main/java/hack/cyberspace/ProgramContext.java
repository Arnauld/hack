package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class ProgramContext {
    private final int nextInstr;
    private final Address location;
    private final Direction direction;
    private final Grid grid;


    public ProgramContext(Address location, Grid grid) {
        this(0, location, grid, Direction.North);
    }

    public ProgramContext(int nextInstr, Address location, Grid grid, Direction direction) {
        this.nextInstr = nextInstr;
        this.location = location;
        this.direction = direction;
        this.grid = grid;
    }

    public int nextInstr() {
        return nextInstr;
    }

    public Address programAddress() {
        return location;
    }

    public Direction direction() {
        return direction;
    }

    public Grid grid() {
        return grid;
    }

    public ProgramContext withNextInstr(int nextInstr) {
        return new ProgramContext(nextInstr, programAddress(), grid(), direction());
    }

    public InstrContext asInstrContext(Program program) {
        return new InstrContext(program, programAddress(), grid(), direction());
    }

    public ProgramContext adjustWith(InstrExecution execution) {
        Address oldLocation = programAddress();
        Address newLocation = execution.getAddress();
        if (!oldLocation.equals(newLocation)) {
            grid.cellAt(oldLocation).onLeave();
            grid.cellAt(newLocation).onEnter();
        }
        return new ProgramContext(
                execution.getNextInstr().orElse(nextInstr),
                newLocation,
                execution.getGrid(), execution.getDirection()
        );
    }
}
