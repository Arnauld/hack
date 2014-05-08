package hack.cyberspace.cell;

import hack.cyberspace.Cell;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Toggle extends Cell {
    public interface Toggleable {
        void toggle();

        boolean isToggled();
    }

    private final Toggleable toggleable;

    public Toggle(Toggleable toggleable) {
        this.toggleable = toggleable;
    }

}
