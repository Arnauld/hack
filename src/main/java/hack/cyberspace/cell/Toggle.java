package hack.cyberspace.cell;

import hack.cyberspace.Cell;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Toggle extends Cell {
    public interface Toggleable {
        void toggle();

        boolean isToggled();
    }

    private final Toggleable toggleable;

    public Toggle(@NotNull Toggleable toggleable) {
        this.toggleable = toggleable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Toggle toggle = (Toggle) o;
        return toggleable.equals(toggle.toggleable);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + toggleable.hashCode();
        return result;
    }
}
