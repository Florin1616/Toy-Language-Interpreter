package Model.Expressions;

import Model.ADTs.DictionaryInterface;
import Model.ADTs.HeapInterface;
import Model.Exceptions.MyException;
import Model.Types.TypeInterface;
import Model.Values.ValueInterface;
import Model.Types.BoolType;
import Model.Values.BoolValue;
public class LogicExpression implements ExpressionInterface {
    ExpressionInterface e1,e2;
    int op;

    public LogicExpression(ExpressionInterface expr1, ExpressionInterface expr2, int op1) {
        e1 = expr1;
        e2 = expr2;
        op = op1;
    }

    @Override
    public ValueInterface eval(DictionaryInterface<String, ValueInterface> tbl, HeapInterface heap) throws MyException {
        ValueInterface val1,val2;
        val1 = e1.eval(tbl,heap);
        if(val1.getType().equals(new BoolType())){
            val2 = e2.eval(tbl,heap);
            if(val2.getType().equals(new BoolType())) {
                BoolValue i1 = (BoolValue) val1;
                BoolValue i2 = (BoolValue) val2;
                boolean x = i1.getValue();
                boolean y = i2.getValue();
                if (op == 1) {
                    return new BoolValue(x && y);
                } else if (op == 2) {
                    return new BoolValue(x || y);
                }
            } else{
                throw new MyException("Second operand is not a boolean");
            }
        } else{
            throw new MyException("First operand is not a boolean");
        }
        return new BoolValue(false);
    }

    @Override
    public TypeInterface typecheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type1 = e1.typecheck(typeEnv);
        TypeInterface type2 = e2.typecheck(typeEnv);

        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            }
            else {
                throw new MyException("The second operand is not a boolean in " + this.toString());
            }
        }
        else {
            throw new MyException("The first operand is not a boolean in " + this.toString());
        }
    }

}
