package hack.cyberspace;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Program {
    private List<Instr> preInstrs = new ArrayList<>();
    private List<Instr> instrs = new ArrayList<>();


    public Program addPreInstr(Instr instr) {
        preInstrs.add(instr);
        return this;
    }

    public Program addInst(Instr instr) {
        instrs.add(instr);
        return this;
    }

    public ProgramContext executeNextInstruction(ProgramContext context) {
        ProgramContext lastContext = context;
        for (Instr instr : preInstrs) {
            lastContext = executeInstr(context, instr);
        }

        int nextInstr = context.nextInstr();
        Instr instr = instrs.get(nextInstr++ % instrs.size());
        return executeInstr(lastContext.withNextInstr(nextInstr), instr);
    }

    private ProgramContext executeInstr(ProgramContext programContext, Instr instr) {
        InstrExecution execution = instr.execute(programContext.asInstrContext());
        if (execution.isValid()) {
            return programContext.adjustWith(execution);
        }
        throw new IllegalInstructionException(instr);
    }
}
