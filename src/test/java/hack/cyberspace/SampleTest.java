package hack.cyberspace;

import hack.cyberspace.cell.Wall;
import hack.cyberspace.instr.If;
import hack.cyberspace.instr.Mov;
import hack.cyberspace.instr.Rot;
import hack.cyberspace.instr.UnTag;
import org.junit.Test;

import java.util.function.Predicate;

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
        Grid grid0 = simpleGrid();
        Cell cell = grid0.cellAt(Address.get(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        Program program = new Program();
        program.addInst(new Mov());
        program.setDirection(Direction.North);
        program.setLocation(Address.get(0, 2));

        Grid grid1 = program.executeNextInstruction(grid0);
        assertThat(grid1).isNotNull();
        assertThat(program.getLocation()).isEqualTo(Address.get(0, 1));
    }

    @Test(expected = IllegalInstructionException.class)
    public void should_not_allow_to_move_on_a_wall() {
        Grid grid0 = simpleGrid();
        Cell cell = grid0.cellAt(Address.get(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        Program program = new Program();
        program.addInst(new Mov());
        program.setDirection(Direction.North);
        program.setLocation(Address.get(0, 1));

        program.executeNextInstruction(grid0);
    }

    @Test
    public void should_allow_to_move_sequentially_in_grid() {
        Grid grid0 = simpleGrid();
        Cell cell = grid0.cellAt(Address.get(0, 0));
        assertThat(cell.canMoveTo()).isFalse();

        Program program = new Program();
        program.setDirection(Direction.North);
        program.setLocation(Address.get(0, 2));
        program.addInst(new Mov()); //1
        program.addInst(new Rot(Right)); //2
        program.addInst(new Mov()); //3
        program.addInst(new Mov()); //4
        program.addInst(new Rot(Left)); //5
        program.addInst(new Mov()); //6
        program.addInst(new Rot(Right)); //7
        program.addInst(new Mov()); //8
        program.addInst(new Mov()); //9
        program.addInst(new Rot(Right)); //10
        program.addInst(new Mov()); //11
        program.addInst(new Mov()); //12

        Grid grid01 = program.executeNextInstruction(grid0);
        Grid grid02 = program.executeNextInstruction(grid01);
        Grid grid03 = program.executeNextInstruction(grid02);
        Grid grid04 = program.executeNextInstruction(grid03);
        Grid grid05 = program.executeNextInstruction(grid04);
        Grid grid06 = program.executeNextInstruction(grid05);
        Grid grid07 = program.executeNextInstruction(grid06);
        Grid grid08 = program.executeNextInstruction(grid07);
        Grid grid09 = program.executeNextInstruction(grid08);
        Grid grid10 = program.executeNextInstruction(grid09);
        Grid grid11 = program.executeNextInstruction(grid10);
        Grid grid12 = program.executeNextInstruction(grid11);

        assertThat(grid12).isNotNull();
        assertThat(program.getLocation()).isEqualTo(Address.get(4, 2));
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
        Address location = Address.get(4, 4);

        UnTag star = new UnTag("star");

        Program program = new Program();
        program.setDirection(Direction.East);
        program.setLocation(location);
        program.addPreInstr(star);
        program.addInst(MOV); //1
        program.addInst(new If(HasColor("r"), MOV)); //2
        program.addInst(new If(HasColor("g"), new Rot(Left))); //3
        program.addInst(new If(HasColor("b"), new Rot(Left))); //4
        program.addInst(new If(HasColor("b"), new Rot(Left))); //5

        Grid g = grid0;
        for (int i = 0; i < 200; i++) {
            g = program.executeNextInstruction(g);
        }
        assertThat(g).isNotNull();
        assertThat(star.untagCount()).isEqualTo(12);
        assertThat(program.getLocation()).isEqualTo(Address.get(4, 4));
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
