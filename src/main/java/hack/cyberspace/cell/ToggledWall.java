package hack.cyberspace.cell;

import hack.cyberspace.Cell;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class ToggledWall extends Cell implements Toggle.Toggleable {

    private boolean toggled;

    @Override
    public void toggle() {
        this.toggled = !toggled;
    }

    @Override
    public boolean isToggled() {
        return toggled;
    }
}
