package Model.Expressions;

import Model.ADTs.DictionaryInterface;
import Model.ADTs.HeapInterface;
import Model.Exceptions.MyException;
import Model.Types.TypeInterface;
import Model.Values.ValueInterface;

public class VarExpression implements ExpressionInterface {
    String id;

    public VarExpression(String s) {
        id = s;
    }
    @Override
    public ValueInterface eval(DictionaryInterface<String, ValueInterface> tbl, HeapInterface heap) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public TypeInterface typecheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        if(typeEnv.isDefined(id)){
            return typeEnv.lookup(id);
        }
        else{
            throw new MyException("The variable " + this.toString() + " is not defined");
        }
    }

    @Override
    public String toString(){
        return id;
    }
}
