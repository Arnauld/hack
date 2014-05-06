package hack.lang.ast;

import org.junit.Test;
import org.parboiled.support.IndexRange;

import static org.fest.assertions.Assertions.assertThat;


public class InstrNodeTest {

    private IndexRange range = new IndexRange(1, 3);

    @Test
    public void indexedValue_equals_should_be_case_insensitive() {
        InstrNode.IndexedValue moV0 = new InstrNode.IndexedValue("MoV", range);
        InstrNode.IndexedValue moV1 = new InstrNode.IndexedValue("MOV", range);
        InstrNode.IndexedValue moV2 = new InstrNode.IndexedValue("mov", range);

        assertThat(moV0).isEqualTo(moV1);
        assertThat(moV1).isEqualTo(moV2);
        assertThat(moV2).isEqualTo(moV0);
    }

    @Test
    public void indexedValue_value_should_be_case_lowered() {
        assertThat(new InstrNode.IndexedValue("MoV", range).value()).isEqualTo("mov");
        assertThat(new InstrNode.IndexedValue("MOV", range).value()).isEqualTo("mov");
        assertThat(new InstrNode.IndexedValue("mov", range).value()).isEqualTo("mov");
    }
}