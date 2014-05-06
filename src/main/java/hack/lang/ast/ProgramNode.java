package hack.lang.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class ProgramNode implements Iterable<InstrNode> {

    private List<InstrNode> nodes = new ArrayList<InstrNode>();

    public boolean add(InstrNode instrNode) {
        nodes.add(instrNode);
        return true;
    }

    @Override
    public Iterator<InstrNode> iterator() {
        return nodes.iterator();
    }
}
