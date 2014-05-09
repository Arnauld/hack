package hack.cyberspace.instr;

import hack.cyberspace.Cell;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;

public class TagTest {
    private InstrContext context;

    @Before
    public void setUp() {
        context = InstrHelpers.basicContext();
    }

    @Test
    public void should_tag_current_cell() {
        Tag tag = new Tag("ITaggedYou");
        InstrExecution execution = tag.execute(context);

        Cell cell = execution.getGrid().cellAt(InstrHelpers.ADDR_ZERO);
        assertThat(cell.hasTag("ITaggedYou")).isTrue();
    }

    @Test
    public void should_not_tag_twice_current_cell() {
        Tag tag = new Tag("ITaggedYou");
        context.cell().tag("ITaggedYou");

        InstrExecution execution = tag.execute(context);

        Cell cell = execution.getGrid().cellAt(InstrHelpers.ADDR_ZERO);
        assertThat(cell.hasTag("ITaggedYou")).isTrue();
        assertThat(cell.tags().collect(Collectors.toList())).containsExactly("ITaggedYou");
    }
}