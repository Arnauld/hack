package hack.cyberspace;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class AddressTest {

    @Test
    public void move_should_adjust_address() {
        Address address = Address.address(5, 7);
        assertThat(address.move(-1, 2)).isEqualTo(Address.address(4, 9));
    }
}