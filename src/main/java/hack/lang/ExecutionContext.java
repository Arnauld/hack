package hack.lang;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class ExecutionContext {
    private Address programAddress;

    public void setProgramAddress(Address programAddress) {
        this.programAddress = programAddress;
    }

    public Address getProgramAddress() {
        return programAddress;
    }
}
