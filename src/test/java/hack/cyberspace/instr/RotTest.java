package hack.cyberspace.instr;

import hack.cyberspace.Direction;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class RotTest {
    private InstrContext context;

    @Before
    public void setUp() {
        context = InstrHelpers.basicContext();
    }

    @Test
    public void left_should_adjust_the_direction_counter_clockwise() {
        check(Direction.North, Rot.Angle.Left,
                Direction.West,
                Direction.South,
                Direction.East,
                Direction.North);
    }

    @Test
    public void right_should_adjust_the_direction_counter_clockwise() {
        check(Direction.North, Rot.Angle.Right,
                Direction.East,
                Direction.South,
                Direction.West,
                Direction.North);

    }

    @Test
    public void rot_should_adjust_direction() {
        Rot rot = new Rot(Rot.Angle.Left);

        context = new InstrContext(InstrHelpers.ADDR_ZERO, InstrHelpers.newGrid2x2(), Direction.West);

        InstrExecution execution = rot.execute(context);
        assertThat(execution.getDirection()).isEqualTo(Direction.South);
    }

    @Test
    public void rot_should_not_affect_grid_or_address() {
        Rot rot = new Rot(Rot.Angle.Left);

        context = new InstrContext(InstrHelpers.ADDR_ZERO, InstrHelpers.newGrid2x2(), Direction.West);

        InstrExecution execution = rot.execute(context);
        assertThat(execution.getAddress()).isEqualTo(InstrHelpers.ADDR_ZERO);
        assertThat(execution.getGrid()).isEqualTo(InstrHelpers.newGrid2x2());
    }

    private void check(Direction initialDir, Rot.Angle angle, Direction... expectedDirections) {
        Direction dir = initialDir;
        for (Direction expectedDir : expectedDirections) {
            dir = angle.applyOn(dir);
            assertThat(dir).isEqualTo(expectedDir);
        }
    }
}