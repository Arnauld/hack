package hack.cyberspace.instr;

import hack.cyberspace.Address;
import hack.cyberspace.Cell;
import hack.cyberspace.Grid;
import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class UnTag extends Instr {

    private final String tag;
    private final AtomicInteger untagCount = new AtomicInteger();

    public UnTag(String tag) {
        this.tag = tag;
    }

    public int untagCount() {
        return untagCount.get();
    }

    @Override
    public InstrExecution execute(InstrContext context) {
        Cell cell = context.cell();
        if(cell.unTag(tag)) {
            untagCount.incrementAndGet();
        }
        return noop(context);
    }

    @Override
    public String toString() {
        return "UnTag(" + tag + ')';
    }
}
