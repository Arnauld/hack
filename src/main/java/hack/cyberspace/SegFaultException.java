package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class SegFaultException extends RuntimeException {
    private final Address address;

    public SegFaultException(Address address) {
        super("Invalid address access " + address);
        this.address = address;
    }
}
