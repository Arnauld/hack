package hack.cyberspace.instr;

import hack.cyberspace.Address;
import hack.cyberspace.Grid;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Tag extends Instr {

    private final String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    @Override
    public InstrExecution execute(InstrContext context) {
        context.cell().tag(tag);
        return noop(context);
    }

    @Override
    public String toString() {
        return "Tag(" + tag + ')';
    }
}
