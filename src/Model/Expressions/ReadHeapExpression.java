package Model.Expressions;

import Model.ADTs.DictionaryInterface;
import Model.ADTs.HeapInterface;
import Model.Exceptions.MyException;
import Model.Types.RefType;
import Model.Types.TypeInterface;
import Model.Values.RefValue;
import Model.Values.ValueInterface;

public class ReadHeapExpression implements ExpressionInterface{
    private ExpressionInterface exp;

    public ReadHeapExpression(ExpressionInterface exp) {
        this.exp = exp;
    }
    @Override
    public ValueInterface eval(DictionaryInterface<String, ValueInterface> symTable, HeapInterface heap) throws MyException {
        ValueInterface val = exp.eval(symTable, heap);
        if (val instanceof RefValue) {
            RefValue refVal = (RefValue) val;
            if (heap.contains(refVal.getAddress())) {
                return heap.get(refVal.getAddress());
            } else {
                throw new MyException("The address doesn't exist in the heap");
            }

        } else {
            throw new MyException("The expression could not be evaluated to a RefValue");
        }
    }

    @Override
    public TypeInterface typecheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface type = exp.typecheck(typeEnv);
        if (type instanceof RefType) {
            RefType refType = (RefType)type;
            return refType.getInner();
        }
        else {
            throw new MyException("The expression is not of a reference type in " + this.toString());
        }
    }

    @Override
    public String toString() {
        return "rH(" + exp + ")";
    }
}
