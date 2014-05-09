package hack.cyberspace.instr;

import hack.cyberspace.Address;
import hack.cyberspace.Cell;
import hack.cyberspace.Direction;
import hack.cyberspace.Grid;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class IfTest {

    private Instr instr;
    private InstrContext context;

    @Before
    public void setUp() {
        instr = mock(Instr.class);
        context = InstrHelpers.basicContext();
    }

    @Test
    public void should_invoke_delegate_if_predicate_accepts() {
        If cond = new If((InstrContext c) -> true, instr);
        cond.execute(context);

        verify(instr).execute(any(InstrContext.class));
    }

    @Test
    public void should_not_invoke_delegate_if_predicate_does_not_accept() {
        If cond = new If((InstrContext c) -> false, instr);
        cond.execute(context);

        verifyZeroInteractions(instr);
    }
}