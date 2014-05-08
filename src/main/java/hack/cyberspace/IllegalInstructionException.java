package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class IllegalInstructionException extends RuntimeException {
    private final Instr instr;

    public IllegalInstructionException(Instr instr) {
        super("Illegal instruction '" + instr + "'");
        this.instr = instr;
    }
}
