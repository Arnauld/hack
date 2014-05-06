package hack.lang.ast;

import org.junit.Before;
import org.junit.Test;
import org.parboiled.support.IndexRange;
import org.parboiled.support.ParsingResult;

import static org.fest.assertions.Assertions.assertThat;

public class HackParboiledParserTest {

    private HackParboiledParserExecutor executor;

    @Before
    public void setUp() {
        executor = new HackParboiledParserExecutor();

    }

    @Test
    public void parse_simple_comment() {
        Object raw = executor.parse(
                "# this a comment\n"
        );

        assertThat(raw).isNotNull();
    }

    @Test
    public void parse_simple_instruction() {
        //executor.trace(true);
        ParsingResult<Object> parsingResult = executor.parse(
                "MOV 6, N\n"
        );

        Object raw = parsingResult.resultValue;
        assertThat(raw)
                .isNotNull()
                .isInstanceOf(ProgramNode.class);

        InstrNode instrNode = new InstrNode("MOV", new IndexRange(0, 3));
        instrNode.appendParam("6", new IndexRange(4, 5));
        instrNode.appendParam("N", new IndexRange(7, 8));
        ProgramNode prgm = (ProgramNode) raw;
        assertThat(prgm).containsOnly(instrNode);
    }
}