package Model.Expressions;

import Model.ADTs.DictionaryInterface;
import Model.ADTs.HeapInterface;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.TypeInterface;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.ValueInterface;

public class RelationalExpression implements ExpressionInterface{
    private ExpressionInterface exp1;
    private ExpressionInterface exp2;
    private int op; // 1 <, 2 <=, 3 ==, 4 !=, 5 >, 6 >=

    public RelationalExpression(ExpressionInterface exp1, ExpressionInterface exp2, int op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }
    @Override
    public ValueInterface eval(DictionaryInterface<String, ValueInterface> symTable, HeapInterface heap) throws MyException {
        ValueInterface val1, val2;
        val1 = exp1.eval(symTable, heap);
        val2 = exp2.eval(symTable, heap);
        if (val1.getType().equals(new IntType()) && val2.getType().equals(new IntType())) {
            IntValue intVal1, intVal2;
            intVal1 = (IntValue) val1;
            intVal2 = (IntValue) val2;
            int x = intVal1.getValue();
            int y = intVal2.getValue();
            switch (op) {
                case 1:
                    return new BoolValue(x < y);
                case 2:
                    return new BoolValue(x <= y);
                case 3:
                    return new BoolValue(x == y);
                case 4:
                    return new BoolValue(x != y);
                case 5:
                    return new BoolValue(x > y);
                case 6:
                    return new BoolValue(x >= y);
            }
        }
        else {
            throw new MyException("At least one operand is not an integer");
        }

        return new BoolValue(false);
    }

    @Override
    public TypeInterface typecheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type1 = exp1.typecheck(typeEnv);
        TypeInterface type2 = exp2.typecheck(typeEnv);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();
            }
            else {
                throw new MyException("The second operand is not an integer in " + this.toString());
            }
        }
        else {
            throw new MyException("The first operand is not an integer in " + this.toString());
        }
    }

    @Override
    public String toString() {
        String s = "";
        switch (op) {
            case 1:
                s = "<";
                break;
            case 2:
                s = "<=";
                break;
            case 3:
                s = "==";
                break;
            case 4:
                s = "!=";
                break;
            case 5:
                s = ">";
                break;
            default:
                s = ">=";
        }
        return exp1 + s + exp2;
    }
}
