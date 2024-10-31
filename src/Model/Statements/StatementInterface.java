package Model.Statements;
import Model.ADTs.DictionaryInterface;
import Model.PrgState;
import Model.Exceptions.MyException;
import Model.Types.TypeInterface;

public interface StatementInterface {
    PrgState execute(PrgState state) throws MyException;

//    StatementInterface deepCopy();
    DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException;

}
