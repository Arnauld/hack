package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public enum Direction {
    North("N"),
    South("S"),
    West("W"),
    East("E");

    private final String mnemonic;

    Direction(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public Address move(Address address) {
        switch (this) {
            case North:
                return address.move(0, -1);
            case South:
                return address.move(0, +1);
            case West:
                return address.move(-1, 0);
            case East:
                return address.move(+1, 0);
            default:
                throw new UnsupportedOperationException("Unsupported direction '" + this + "'");
        }
    }

    public String mnemonic() {
        return mnemonic;
    }
}
