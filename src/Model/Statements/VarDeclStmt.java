package Model.Statements;
import Model.*;
import Model.ADTs.DictionaryInterface;
import Model.ADTs.StackInterface;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.TypeInterface;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.ValueInterface;

public class VarDeclStmt implements StatementInterface {
    String name;
    TypeInterface type;

    public VarDeclStmt(String s, TypeInterface type) {
        name = s;
        this.type = type;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        StackInterface<StatementInterface> stack = state.getStack();
        DictionaryInterface<String, ValueInterface> table = state.getSymTable();
        if(table.isDefined(name)){
            throw new MyException("Variable is already declared!");
        }else {
//            if (type.equals(new IntType())) {
//                table.add(name, new IntValue());
//            } else if (type.equals(new BoolType())) {
//                table.add(name, new BoolValue());
//            } else if (type.equals(new StringType())) {
//                table.add(name, new StringValue());
//            }
//
//            else {
//                throw new MyException("Type is not defined!");
//            }
            table.add(name, type.defaultValue());
        }
        state.setSymTable(table);
        state.setExeStack(stack);
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        if (typeEnvironment.isDefined(name)) {
            throw new MyException("The variable " + name + " already defined");
        }
        typeEnvironment.update(name, type);
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return type + "" + name;
    }
}
