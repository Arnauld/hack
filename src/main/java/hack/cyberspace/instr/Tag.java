package hack.cyberspace.instr;

import hack.cyberspace.Cell;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Tag extends Instr<Tag> {

    private static final int NOLIMIT = -1;
    private final String tag;
    private final int max;

    public Tag(String tag) {
        this(tag, null);
    }

    public Tag(String tag, String label) {
        this(tag, 1, label);
    }

    public Tag(String tag, int max, String label) {
        super(label);
        this.tag = tag;
        this.max = max;
    }

    @Override
    public Tag withLabel(String label) {
        return new Tag(tag, max, label);
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
