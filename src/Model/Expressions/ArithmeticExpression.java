package Model.Expressions;
import Model.ADTs.DictionaryInterface;
import Model.ADTs.HeapInterface;
import Model.Exceptions.MyException;
import Model.Types.TypeInterface;
import Model.Values.ValueInterface;
import Model.Values.IntValue;
import Model.Types.IntType;

public class ArithmeticExpression implements ExpressionInterface {
    ExpressionInterface e1,e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide


    @Override
    public String toString() {
        switch (op) {
            case 1:
                return e1.toString() + "+" + e2.toString();
            case 2:
                return e1.toString() + "-" + e2.toString();
            case 3:
                return e1.toString() + "*" + e2.toString();
            case 4:
                return e1.toString() + '/' + e2.toString();
            default:
                return "";
        }
    }
    public ArithmeticExpression(char op,ExpressionInterface expr1, ExpressionInterface expr2) {
        this.e1 = expr1;
        this.e2 = expr2;
        if(op == '+')
            this.op = 1;
        if(op == '-')
            this.op = 2;
        if(op == '*')
            this.op = 3;
        if(op == '/')
            this.op = 4;
    }

    @Override
    public ValueInterface eval(DictionaryInterface<String, ValueInterface> table, HeapInterface heap) throws MyException {
        ValueInterface val1,val2;
        val1 = e1.eval(table,heap);
        if(val1.getType().equals(new IntType())){
            val2 = e2.eval(table,heap);
            if(val2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)val1;
                IntValue i2 = (IntValue) val2;
                int n1 = i1.getValue();
                int n2 = i2.getValue();
                if (op==1) return new IntValue(n1+n2);
                if (op==2) return new IntValue(n1-n2);
                if(op==3) return new IntValue(n1*n2);
                if(op==4) {
                    if (n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
                }
            }else throw new MyException("Second operand is not an integer!");
        }else throw new MyException("First operand is not an integer!");
        return null;
}

    @Override
    public TypeInterface typecheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type1 = e1.typecheck(typeEnv);
        TypeInterface type2 = e2.typecheck(typeEnv);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            }
            else {
                throw new MyException("The second operand is not an integer in " + this.toString());
            }
        }
        else {
            throw new MyException("The first operand is not an integer in " + this.toString());
        }
    }
}

