package hack.cyberspace.instr;

import hack.cyberspace.Cell;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

public class UnTagTest {
    private InstrContext context;

    @Before
    public void setUp() {
        context = InstrHelpers.basicContext();
    }

    @Test
    public void should_untag_current_cell() {
        context.cell().tag("ITaggedYou");
        UnTag untag = new UnTag("ITaggedYou");
        InstrExecution execution = untag.execute(context);

        Cell cell = execution.getGrid().cellAt(InstrHelpers.ADDR_ZERO);
        assertThat(cell.hasTag("ITaggedYou")).isFalse();
        assertThat(untag.untagCount()).isEqualTo(1);
    }


    @Test
    public void should_not_do_anything_when_no_tag_on_current_cell() {
        UnTag untag = new UnTag("ITaggedYou");
        InstrExecution execution = untag.execute(context);

        Cell cell = execution.getGrid().cellAt(InstrHelpers.ADDR_ZERO);
        assertThat(cell.hasTag("ITaggedYou")).isFalse();
        assertThat(untag.untagCount()).isEqualTo(0);
    }
}