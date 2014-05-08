package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public abstract class Instr {
    public abstract InstrExecution execute(InstrContext context);

    protected InstrExecution noop(InstrContext context) {
        return new InstrExecution(context.getAddress(), context.getGrid(), context.getDirection());
    }
}
