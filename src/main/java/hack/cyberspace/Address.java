package hack.cyberspace;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Address {

    public static Address get(int x, int y) {
        return new Address(x, y);
    }

    private final int x;
    private final int y;

    public Address(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Address address = (Address) o;
        return x == address.x && y == address.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "#{" + x + ", " + y + "}";
    }

    public Address move(int dx, int dy) {
        return Address.get(x + dx, y + dy);
    }
}
