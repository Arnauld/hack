package hack.cyberspace;

import hack.cyberspace.cell.Wall;
import hack.cyberspace.instr.If;
import hack.cyberspace.instr.Mov;
import hack.cyberspace.instr.Rot;
import hack.cyberspace.instr.UnTag;
import org.junit.Test;

import java.util.function.Predicate;

import static hack.cyberspace.Address.address;
import static hack.cyberspace.Direction.East;
import static hack.cyberspace.Direction.North;
import static hack.cyberspace.instr.Rot.Angle.Left;
import static hack.cyberspace.instr.Rot.Angle.Right;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class SampleTest {

    public static final Mov MOV = new Mov();
    private static final String NL = "\n";

    @Test
    public void should_allow_to_move_on_free_cell() {
        Grid grid = simpleGrid();
        Cell cell = grid.cellAt(address(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        ProgramContext programContext = new ProgramContext(0, address(0, 2), North, grid);

        Program program = new Program();
        program.addInst(MOV);

        ProgramContext newContext = program.executeNextInstruction(programContext);
        assertThat(newContext).isNotNull();
        assertThat(newContext.programAddress()).isEqualTo(address(0, 1));
    }

    @Test(expected = IllegalInstructionException.class)
    public void should_not_allow_to_move_on_a_wall() {
        Grid grid0 = simpleGrid();
        Cell cell = grid0.cellAt(address(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        ProgramContext programContext = new ProgramContext(0, address(0, 1), North, grid0);

        Program program = new Program();
        program.addInst(MOV);

        program.executeNextInstruction(programContext);
    }

    @Test
    public void should_allow_to_move_sequentially_in_grid() {
        Grid grid0 = simpleGrid();
        Cell cell = grid0.cellAt(address(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        ProgramContext context00 = new ProgramContext(0, address(0, 2), North, grid0);

        Program program = new Program();
        program.addInst(MOV); //1
        program.addInst(new Rot(Right)); //2
        program.addInst(MOV); //3
        program.addInst(MOV); //4
        program.addInst(new Rot(Left)); //5
        program.addInst(MOV); //6
        program.addInst(new Rot(Right)); //7
        program.addInst(MOV); //8
        program.addInst(MOV); //9
        program.addInst(new Rot(Right)); //10
        program.addInst(MOV); //11
        program.addInst(MOV); //12

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

        ProgramContext context00 = new ProgramContext(0, location, East, grid0);

        Program program = new Program();
        program.addPreInstr(star);
        program.addInst(MOV); //1
        program.addInst(new If(HasColor("r"), MOV)); //2
        program.addInst(new If(HasColor("g"), new Rot(Left))); //3
        program.addInst(new If(HasColor("b"), new Rot(Left))); //4
        program.addInst(new If(HasColor("b"), new Rot(Left))); //5

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

    public static Wall wall() {
        return new Wall();
    }

    public static Cell cell() {
        return new Cell();
    }
}
