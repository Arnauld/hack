package hack.cyberspace.instr;

import hack.cyberspace.*;

import java.util.Optional;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class InstrHelpers {
    public static final Address ADDR_ZERO = Address.address(0, 0);

    public static Grid newGrid2x2() {
        return new Grid(new Cell[][]{
                {new Cell(), new Cell()},
                {new Cell(), new Cell()}});
    }

    public static InstrSeq emptyInstrSeq() {
        return dstLabel -> Optional.empty();
    }

    public static InstrSeq instrSeq(final Instr<?>... instrs) {
        return dstLabel -> {
            for (int i = 0; i < instrs.length; i++) {
                Instr<?> instr = instrs[i];
                if (dstLabel.equals(instr.label()))
                    return Optional.of(i);
            }
            return Optional.empty();
        };
    }

    public static InstrContext basicContext() {
        return basicContext(emptyInstrSeq());
    }

    public static InstrContext basicContext(InstrSeq seq) {
        return new InstrContext(seq, ADDR_ZERO, newGrid2x2(), Direction.North);
    }
}
