package Model.Statements;
import Model.ADTs.DictionaryInterface;
import Model.Exceptions.MyException;
import Model.PrgState;
import Model.ADTs.StackInterface;
import Model.Expressions.ExpressionInterface;
import Model.Types.TypeInterface;
import Model.Values.ValueInterface;
import Model.Values.BoolValue;
import Model.Types.BoolType;

public class IfStmt implements StatementInterface{
    private ExpressionInterface expression;
    private StatementInterface thenStatement;
    private StatementInterface elseStatement;

    public IfStmt(ExpressionInterface e, StatementInterface t, StatementInterface el) {
        expression = e;
        thenStatement = t;
        elseStatement = el;

    }

    public String toString() {
        return "if (" + expression + ") then {" + thenStatement + "} else {" + elseStatement + "}";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        StackInterface<StatementInterface> stack = state.getStack();
        ValueInterface cond = expression.eval(state.getSymTable(), state.getHeap());
        if(!cond.getType().equals(new BoolType())){
            throw new MyException("Condition is not boolean!");
        }
        if(((BoolValue) cond).getValue()){
            stack.push(thenStatement);
        }else {
            stack.push(elseStatement);
        }
        state.setExeStack(stack);
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface expType = expression.typecheck(typeEnvironment);
        if (expType.equals(new BoolType())) {
            thenStatement.typecheck(typeEnvironment);
            elseStatement.typecheck(typeEnvironment);
            return typeEnvironment;
        }
        else {
            throw new MyException("The condition in " + this.toString() + " statement is not a boolean");
        }
    }
}
