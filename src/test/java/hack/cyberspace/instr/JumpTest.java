package hack.cyberspace.instr;

import hack.cyberspace.Direction;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;
import hack.cyberspace.InstrSeq;
import org.junit.Before;
import org.junit.Test;

import static hack.cyberspace.Direction.North;
import static hack.cyberspace.instr.InstrHelpers.ADDR_ZERO;
import static hack.cyberspace.instr.InstrHelpers.instrSeq;
import static hack.cyberspace.instr.InstrHelpers.newGrid2x2;
import static hack.cyberspace.instr.Mov.mov;
import static hack.cyberspace.instr.Rot.left;
import static org.fest.assertions.Assertions.assertThat;

public class JumpTest {

    private InstrSeq instrSeq;

    @Before
    public void setUp() {
        instrSeq = instrSeq(mov(), mov(), mov().withLabel("uh"), left(), mov());
    }

    @Test
    public void should_adjust_next_instr_according_to_label() {
        Jump jump = new Jump("uh");
        InstrExecution instrExecution = jump.execute(new InstrContext(instrSeq, ADDR_ZERO, newGrid2x2(), North));
        assertThat(instrExecution.isValid()).isTrue();
        assertThat(instrExecution.getNextInstr()).isNotNull();
        assertThat(instrExecution.getNextInstr().isPresent()).isTrue();
        assertThat(instrExecution.getNextInstr().get()).isEqualTo(2);
    }

    @Test
    public void should_result_in_invalid_execution_when_label_is_not_found() {
        Jump jump = new Jump("yuk");
        InstrExecution instrExecution = jump.execute(new InstrContext(instrSeq, ADDR_ZERO, newGrid2x2(), North));
        assertThat(instrExecution.isValid()).isFalse();
        assertThat(instrExecution.getNextInstr()).isNotNull();
        assertThat(instrExecution.getNextInstr().isPresent()).isFalse();
    }

}