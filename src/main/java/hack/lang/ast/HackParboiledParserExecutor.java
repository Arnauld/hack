package hack.lang.ast;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.parserunners.TracingParseRunner;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class HackParboiledParserExecutor {
    private boolean trace = false;

    public HackParboiledParserExecutor trace(boolean trace) {
        this.trace = trace;
        return this;
    }

    public static void dumpNode(ParsingResult<Object> result) {
        System.out.println("HackParboiledParser.parse(" + ParseTreeUtils.printNodeTree(result) + ")");
    }

    public ParsingResult<Object> parse(String input) {
        HackParboiledParser parser = Parboiled.createParser(HackParboiledParser.class);
        ParsingResult<Object> result = execute(input, parser);

        if (result.hasErrors()) {
            System.err.println("HackParboiledParser.parse(" + result.parseErrors + ")");
        }
        return result;
    }

    private ParsingResult<Object> execute(String input, HackParboiledParser parser) {
        if (trace)
            return new TracingParseRunner<Object>(parser.Program()).run(input);
        else
            return new ReportingParseRunner<Object>(parser.Program()).run(input);
    }

}
