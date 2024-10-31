package Model.Statements;

import Model.ADTs.DictionaryInterface;
import Model.Exceptions.MyException;
import Model.PrgState;
import Model.ADTs.StackInterface;
import Model.Types.TypeInterface;

public class CompStmt implements StatementInterface{
    private StatementInterface first;
    private StatementInterface second;

    public CompStmt(StatementInterface first, StatementInterface second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        StackInterface<StatementInterface> stack = state.getStack();
        stack.push(second);
        stack.push(first);
        state.setExeStack(stack);
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return second.typecheck(first.typecheck(typeEnvironment));

    }

    @Override
    public String toString(){
        return first.toString() + " | " + second.toString();
    }
}
