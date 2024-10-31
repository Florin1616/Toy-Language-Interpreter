package Model.Statements;

import Model.ADTs.DictionaryInterface;
import Model.Exceptions.MyException;
import Model.PrgState;
import Model.Types.TypeInterface;

public class NopStmt implements StatementInterface{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        return typeEnvironment;

    }
}
