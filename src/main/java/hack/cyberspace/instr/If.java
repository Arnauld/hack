package hack.cyberspace.instr;

import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;

import java.util.function.Predicate;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class If extends Instr {

    private final Predicate<InstrContext> condition;
    private final Instr instr;

    public If(Predicate<InstrContext> condition, Instr instr) {
        this.condition = condition;
        this.instr = instr;
    }

    @Override
    public InstrExecution execute(InstrContext context) {
        if (condition.test(context)) {
            return instr.execute(context);
        }

        // no-op
        return noop(context);
    }

    @Override
    public String toString() {
        return "If(" + instr + ')';
    }
}
