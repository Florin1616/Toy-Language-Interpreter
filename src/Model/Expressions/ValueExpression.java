package Model.Expressions;

import Model.ADTs.DictionaryInterface;
import Model.ADTs.HeapInterface;
import Model.Exceptions.MyException;
import Model.Types.TypeInterface;
import Model.Values.IntValue;
import Model.Values.ValueInterface;

public class ValueExpression implements ExpressionInterface {
    ValueInterface e;

    public ValueExpression(ValueInterface deepCopy) {
        e = deepCopy;
    }

    @Override
    public String toString() {
        return e.toString();
    }

    @Override
    public ValueInterface eval(DictionaryInterface<String, ValueInterface> tbl, HeapInterface heap) throws MyException {
        return e;
    }

    @Override
    public TypeInterface typecheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return e.getType();
    }

}
