package hack.lang.ast;

import org.parboiled.support.IndexRange;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class InstrNode {
    private final IndexedValue code;
    private List<IndexedValue> params;

    public InstrNode(String code, IndexRange indexRange) {
        this.code = new IndexedValue(code, indexRange);
    }

    public boolean appendParam(String value, IndexRange indexRange) {
        if (params == null)
            params = new ArrayList<IndexedValue>(2);
        params.add(new IndexedValue(value, indexRange));
        return true;
    }

    @Override
    public String toString() {
        return "Instr{" + code + ", " + params + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstrNode instrNode = (InstrNode) o;
        return code.equals(instrNode.code) && params.equals(instrNode.params);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + params.hashCode();
        return result;
    }

    public static class IndexedValue {
        private final String value;
        private final IndexRange range;

        public IndexedValue(String value, IndexRange range) {
            this.value = value.toLowerCase();
            this.range = range;
        }

        @Override
        public String toString() {
            return "{'" + value + "' " + range.start + "->" + range.end + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IndexedValue that = (IndexedValue) o;
            return range.equals(that.range) && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            int result = value.hashCode();
            result = 31 * result + range.hashCode();
            return result;
        }

        public String value() {
            return value;
        }

        public IndexRange range() {
            return range;
        }
    }
}
