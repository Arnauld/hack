package hack.lang.ast;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.annotations.SuppressNode;
import org.parboiled.annotations.SuppressSubnodes;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
@BuildParseTree
public class HackParboiledParser extends BaseParser<Object> {


    public Rule Program() {
        return ZeroOrMore(
                push(new ProgramNode()),
                Sequence(
                        ProgramLine(),
                        EOI_OR_NL
                )
        );
    }

    public Rule ProgramLine() {
        return FirstOf(
                Comment(),
                Instruction());
    }

    @SuppressSubnodes
    public Rule Comment() {
        return Sequence(
                Sp(), COMMENT, ZeroOrMore(NotNewline(), BaseParser.ANY));
    }

    public Rule Instruction() {
        return Sequence(
                Sp(),
                InstrCode(),
                push(new InstrNode(match(), matchRange())),
                Optional(InstrParams()), Sp(),
                ((ProgramNode) peek(1)).add((InstrNode) pop())
        );
    }

    public Rule InstrParams() {
        return Sequence(
                AtLeastOneSpace(),
                KValue(),
                ((InstrNode) peek()).appendParam(match(), matchRange()),
                ZeroOrMore(
                        Sp(), COMMA, Sp(), KValue(), ((InstrNode) peek()).appendParam(match(), matchRange())
                )
        );
    }

    @SuppressSubnodes
    public Rule InstrCode() {
        return OneOrMore(NoneOf(" ,\t\r\n"));
    }

    @SuppressSubnodes
    public Rule KValue() {
        return OneOrMore(NoneOf(" ,\t\r\n"));
    }

    // --- MISC

    @SuppressNode
    public Rule Sp() {
        return ZeroOrMore(Spacechar());
    }

    @SuppressNode
    public Rule AtLeastOneSpace() {
        return OneOrMore(Spacechar());
    }

    @SuppressSubnodes
    public Rule Spacechar() {
        return AnyOf(" \t");
    }

    @SuppressSubnodes
    public Rule Newline() {
        return FirstOf('\n', Sequence('\r', Optional('\n')));
    }

    @SuppressSubnodes
    public Rule NotNewline() {
        return TestNot(AnyOf("\n\r"));
    }

    final Rule COMMA = String(",");
    final Rule COMMENT = String("#");
    final Rule EOI_OR_NL = FirstOf(Newline(), EOI);

}
