package hack.cyberspace.instr;

import hack.cyberspace.Cell;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class UnTag extends Instr<UnTag> {

    private final String tag;
    private final AtomicInteger untagCount = new AtomicInteger();

    public UnTag(String tag) {
        this(tag, null);
    }

    public UnTag(String tag, String label) {
        super(label);
        this.tag = tag;
    }

    @Override
    public UnTag withLabel(String label) {
        return new UnTag(tag, label);
    }

    public int untagCount() {
        return untagCount.get();
    }

    @Override
    public InstrExecution execute(InstrContext context) {
        Cell cell = context.cell();
        if (cell.unTag(tag)) {
            untagCount.incrementAndGet();
        }
        return noop(context);
    }

    @Override
    public String toString() {
        return "UnTag(" + tag + ')';
    }
}
