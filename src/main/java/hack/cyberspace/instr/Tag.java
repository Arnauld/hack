package hack.cyberspace.instr;

import hack.cyberspace.Cell;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Tag extends Instr {

    private static final int NOLIMIT = -1;
    private final String tag;
    private final int max;

    public Tag(String tag) {
        this(tag, 1);
    }

    public Tag(String tag, int max) {
        this.tag = tag;
        this.max = max;
    }

    @Override
    public InstrExecution execute(InstrContext context) {
        Cell cell = context.cell();
        if (max == NOLIMIT || cell.tags().filter(t -> t.equalsIgnoreCase(tag)).count() < max) {
            cell.tag(tag);
        }
        return noop(context);
    }

    @Override
    public String toString() {
        return "Tag(" + tag + ')';
    }
}
