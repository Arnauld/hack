package hack.lang;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class ParserTest {

    @Test
    public void parse_simple_mov() {
        Parser parser = new Parser();
        Program program = parser.parse(
                "# this a comment\n" +
                        "MOV 6, N\n"
        );

        assertThat(program).isNotNull();

        ExecutionContext context = new ExecutionContext();
        context.setProgramAddress(Address.get(10, 10));
        program.execute(context);

        assertThat(context.getProgramAddress()).isEqualTo(Address.get(4, 10));
    }
}
