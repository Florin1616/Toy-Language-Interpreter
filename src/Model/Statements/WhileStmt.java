package Model.Statements;

import Model.ADTs.DictionaryInterface;
import Model.ADTs.StackInterface;
import Model.Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.TypeInterface;
import Model.Values.BoolValue;
import Model.Values.ValueInterface;

public class WhileStmt implements StatementInterface{
    private ExpressionInterface exp;
    private StatementInterface statement;
    public WhileStmt(ExpressionInterface exp, StatementInterface statement) {
        this.exp = exp;
        this.statement = statement;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        StackInterface<StatementInterface> stk = state.getStack();
        DictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        ValueInterface val = exp.eval(symTable, state.getHeap());
        if (val.getType().equals(new BoolType())) {
            BoolValue boolVal = (BoolValue) val;
            if (boolVal.getValue()) {
                stk.push(this);
                stk.push(statement);
            }
        }
        else {
            throw new MyException("The While condition is not a boolean");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface expType = exp.typecheck(typeEnvironment);
        if (expType.equals(new BoolType())) {
            statement.typecheck(typeEnvironment);
            return typeEnvironment;
        }
        else {
            throw new MyException("The condition in " + this.toString() + " is not a boolean");
        }
    }


    @Override
    public String toString() {
        return "(while (" + exp + ") " + statement + ")";
    }
}
