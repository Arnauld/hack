package hack.cyberspace.cell;

import org.junit.Test;
import org.mockito.Mockito;

public class ToggleTest {


    @Test
    public void should_toggle_delegate_when_moving_in() {
        Toggle.Toggleable toggleable = Mockito.mock(Toggle.Toggleable.class);
        Toggle toggle = new Toggle(toggleable);

        toggle.onEnter();
    }
}