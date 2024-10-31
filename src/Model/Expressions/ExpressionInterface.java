package Model.Expressions;
import Model.ADTs.HeapInterface;
import Model.Exceptions.MyException;
import Model.Types.TypeInterface;
import Model.Values.ValueInterface;
import Model.ADTs.DictionaryInterface;
public interface ExpressionInterface {
     public ValueInterface eval(DictionaryInterface<String,ValueInterface> tbl, HeapInterface heap) throws MyException;
     TypeInterface typecheck(DictionaryInterface<String,TypeInterface> typeEnv) throws MyException;

}
