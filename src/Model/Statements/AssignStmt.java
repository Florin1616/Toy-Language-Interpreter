package Model.Statements;

import Model.Exceptions.MyException;
import Model.PrgState;
import Model.ADTs.DictionaryInterface;
import Model.Values.ValueInterface;
import Model.Types.TypeInterface;
import Model.Expressions.ExpressionInterface;

public class AssignStmt implements StatementInterface {
    String id;
    ExpressionInterface exp;

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    public AssignStmt(String s, ExpressionInterface expr1) {
        id = s;
        exp = expr1;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        ValueInterface value = exp.eval(symTable, state.getHeap());
        if (symTable.isDefined(id)) {
            TypeInterface type = (symTable.lookup(id)).getType();
            if (value.getType().equals(type)) {
                symTable.update(id, value);
            } else {
                throw new MyException("The declared type of variable" + id + "and the type of the assigned expression do not match!");

            }
        } else {
            throw new MyException("The used variable" + id + "was not declared before!");
        }
        state.setSymTable(symTable);
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        if (!typeEnvironment.isDefined(id)) {
            throw new MyException("The variable " + id + " is not defined in the assignment statement " + this.toString());
        }
        else {
            TypeInterface variableType = typeEnvironment.lookup(id);
            TypeInterface expType = exp.typecheck(typeEnvironment);
            if (variableType.equals(expType)) {
                return typeEnvironment;
            }
            else {
                throw new MyException("The right side and left side of the assignment " + this.toString() + " have different types");
            }
        }
    }


}