package hack.cyberspace;

import hack.cyberspace.instr.If;
import hack.cyberspace.instr.UnTag;
import org.junit.Test;

import java.util.function.Predicate;

import static hack.cyberspace.Address.address;
import static hack.cyberspace.Cell.cell;
import static hack.cyberspace.Direction.East;
import static hack.cyberspace.Direction.North;
import static hack.cyberspace.cell.Wall.wall;
import static hack.cyberspace.instr.Mov.mov;
import static hack.cyberspace.instr.Rot.left;
import static hack.cyberspace.instr.Rot.right;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class SampleTest {

    private static final String NL = "\n";

    @Test
    public void should_allow_to_move_on_free_cell() {
        Grid grid = simpleGrid();
        Cell cell = grid.cellAt(address(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        ProgramContext programContext = new ProgramContext(0, address(0, 2), grid, North);

        Program program = new Program();
        program.addInst(mov());

        ProgramContext newContext = program.executeNextInstruction(programContext);
        assertThat(newContext).isNotNull();
        assertThat(newContext.programAddress()).isEqualTo(address(0, 1));
    }

    @Test(expected = IllegalInstructionException.class)
    public void should_not_allow_to_move_on_a_wall() {
        Grid grid0 = simpleGrid();
        Cell cell = grid0.cellAt(address(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        ProgramContext programContext = new ProgramContext(0, address(0, 1), grid0, North);

        Program program = new Program();
        program.addInst(mov());

        program.executeNextInstruction(programContext);
    }

    @Test
    public void should_allow_to_move_sequentially_in_grid() {
        Grid grid0 = simpleGrid();
        Cell cell = grid0.cellAt(address(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        ProgramContext context00 = new ProgramContext(0, address(0, 2), grid0, North);

        Program program = new Program();
        program.addInst(mov()); //1
        program.addInst(right()); //2
        program.addInst(mov()); //3
        program.addInst(mov()); //4
        program.addInst(left()); //5
        program.addInst(mov()); //6
        program.addInst(right()); //7
        program.addInst(mov()); //8
        program.addInst(mov()); //9
        program.addInst(right()); //10
        program.addInst(mov()); //11
        program.addInst(mov()); //12

        ProgramContext context01 = program.executeNextInstruction(context00);
        ProgramContext context02 = program.executeNextInstruction(context01);
        ProgramContext context03 = program.executeNextInstruction(context02);
        ProgramContext context04 = program.executeNextInstruction(context03);
        ProgramContext context05 = program.executeNextInstruction(context04);
        ProgramContext context06 = program.executeNextInstruction(context05);
        ProgramContext context07 = program.executeNextInstruction(context06);
        ProgramContext context08 = program.executeNextInstruction(context07);
        ProgramContext context09 = program.executeNextInstruction(context08);
        ProgramContext context10 = program.executeNextInstruction(context09);
        ProgramContext context11 = program.executeNextInstruction(context10);
        ProgramContext context12 = program.executeNextInstruction(context11);

        assertThat(context12).isNotNull();
        assertThat(context12.programAddress()).isEqualTo(address(4, 2));
    }

    @Test
    public void cross() {
        String grid = "" + //
                "....B...." + NL + //
                "...BgB..." + NL + //
                "....r...." + NL + //
                ".B..r..B." + NL + //
                "BgrrgrrgB" + NL + //
                ".B..r..B." + NL + //
                "....r...." + NL + //
                "...BgB..." + NL + //
                "....B...." + //
                "";
        Grid grid0 = new GridBuilder().parse(grid).create();
        Address location = address(4, 4);

        UnTag star = new UnTag("star");

        ProgramContext context00 = new ProgramContext(0, location, grid0, East);

        Program program = new Program();
        program.addPreInstr(star);
        program.addInst(mov()); //1
        program.addInst(new If(HasColor("r"), mov())); //2
        program.addInst(new If(HasColor("g"), left())); //3
        program.addInst(new If(HasColor("b"), left())); //4
        program.addInst(new If(HasColor("b"), left())); //5

        ProgramContext context = context00;
        for (int i = 0; i < 200; i++) {
            context = program.executeNextInstruction(context);
        }
        assertThat(context).isNotNull();
        assertThat(star.untagCount()).isEqualTo(12);
        assertThat(context.programAddress()).isEqualTo(address(4, 4));
    }

    private Predicate<InstrContext> HasColor(String color) {
        return HasTag("color:" + color);
    }

    private Predicate<InstrContext> HasTag(String tag) {
        return (InstrContext c) -> c.cell().hasTag(tag);
    }

    private Grid simpleGrid() {
        GridBuilder builder = new GridBuilder();
        builder.appendRow(wall(), wall(), cell(), cell(), cell());
        builder.appendRow(cell(), cell(), cell(), wall(), cell());
        builder.appendRow(cell(), wall(), wall(), wall(), cell());

        return builder.create();
    }

}
