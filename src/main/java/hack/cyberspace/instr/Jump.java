package hack.cyberspace.instr;

import hack.cyberspace.Instr;
import hack.cyberspace.InstrContext;
import hack.cyberspace.InstrExecution;
import hack.cyberspace.InstrSeq;

import java.util.Optional;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Jump extends Instr<Jump> {

    public static Jump jump(String dstLabel) {
        return new Jump(dstLabel);
    }

    private final String dstLabel;

    public Jump(String dstLabel) {
        this(dstLabel, null);
    }

    public Jump(String dstLabel, String label) {
        super(label);
        this.dstLabel = dstLabel;
    }

    @Override
    public Jump withLabel(String label) {
        return new Jump(dstLabel, label);
    }

    @Override
    public InstrExecution execute(InstrContext context) {
        InstrSeq instrSeq = context.getInstrSeq();
        Optional<Integer> nextInstr = instrSeq.findInstrIndex(dstLabel);
        if (nextInstr.isPresent()) {
            return new InstrExecution(
                    context.getAddress(),
                    context.getGrid(),
                    context.getDirection(),
                    nextInstr);
        }
        return InstrExecution.invalidExecution();
    }

    @Override
    public String toString() {
        return "Jump(" + dstLabel + ")";
    }

}
