package hack.cyberspace;

import java.util.Optional;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public interface InstrSeq {
    Optional<Integer> findInstrIndex(String dstLabel);
}
