package Model.Statements;

import Model.ADTs.DictionaryInterface;
import Model.ADTs.HeapInterface;
import Model.Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.Expressions.ValueExpression;
import Model.PrgState;
import Model.Types.RefType;
import Model.Types.TypeInterface;
import Model.Values.RefValue;
import Model.Values.ValueInterface;
import com.sun.jdi.Value;

public class WriteHeapStmt implements StatementInterface{
    private String variableName;
    private ExpressionInterface exp;

    public WriteHeapStmt(String variableName, ExpressionInterface exp) {
        this.variableName = variableName;
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException{
        DictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        HeapInterface heap = state.getHeap();
        if (symTable.isDefined(variableName)) {
            if (symTable.lookup(variableName).getType() instanceof RefType) {
                RefValue refVal = (RefValue) symTable.lookup(variableName);
                if (heap.contains(refVal.getAddress())) {
                    ValueInterface val = exp.eval(symTable, heap);

                    if (symTable.lookup(variableName).getType().equals(new RefType(val.getType()))) {
                        int address = refVal.getAddress();
                        heap.update(address, val);
                    }
                    else {
                        throw new MyException("The pointing variable has a different type than the evaluated expression.");
                    }
                }
                else {
                    throw new MyException("The address to which " + variableName + " points is not in the heap");
                }
            }
            else {
                throw new MyException(variableName + " is not a reference variable");
            }
        }
        else {
            throw new MyException(variableName + " is not defined");
        }

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        if (typeEnvironment.isDefined(variableName)) {
            TypeInterface variableType = typeEnvironment.lookup(variableName);
            TypeInterface expType = exp.typecheck(typeEnvironment);
            if (!(variableType instanceof RefType)) {
                throw new MyException("The file name in " + this.toString() + " is not a string");
            }
            if (!variableType.equals(new RefType(expType))) {
                throw new MyException("The right side of " + this.toString() + " has other type than the referenced type of the left side");
            }
            return typeEnvironment;
        }
        else {
            throw new MyException("The variable " + variableName + " is not defined");
        }
    }

    @Override
    public String toString() {
        return "wH(" + variableName + "," + exp + ")";
    }
}
