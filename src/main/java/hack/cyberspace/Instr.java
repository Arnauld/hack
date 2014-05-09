package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public abstract class Instr<T extends Instr<T>> {

    protected final String label;

    protected Instr(String label) {
        this.label = label;
    }

    public abstract T withLabel(String label);

    public String label() {
        return label;
    }

    public abstract InstrExecution execute(InstrContext context);

    protected InstrExecution noop(InstrContext context) {
        return new InstrExecution(context.getAddress(), context.getGrid(), context.getDirection());
    }
}
