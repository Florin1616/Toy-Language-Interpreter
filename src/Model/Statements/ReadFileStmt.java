package Model.Statements;

import Model.ADTs.DictionaryInterface;
import Model.Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.TypeInterface;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements StatementInterface{
    private String varName;
    private String fileName;
    private ExpressionInterface exp;
    public ReadFileStmt(ExpressionInterface exp, String varName){
        this.varName = varName;
        this.exp = exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(symTable.isDefined(varName)){
            if(symTable.lookup(varName).getType().equals(new IntType())){
                ValueInterface val = exp.eval(symTable, state.getHeap());
                if(val.getType().equals(new StringType())) {
                    StringValue stringVal = (StringValue) val;
                    if(fileTable.isDefined(stringVal)){
                        BufferedReader bufferedReader = fileTable.lookup(stringVal);
                        try {
                            String line = bufferedReader.readLine();
                            ValueInterface intVal;
                            IntType type = new IntType();
                            if (line == null){
                                intVal = type.defaultValue();
                            }
                            else {
                                intVal = new IntValue(Integer.parseInt(line));
                            }
                            symTable.update(varName, intVal);
                        }
                        catch (IOException e){
                            throw new MyException("The file is empty!");
                        }
                    }
                    else {
                        throw new MyException("The file " + stringVal.getValue() + "is not in the File Table!");
                    }
                }
                else{
                    throw new MyException("The value could not be evaluated to a string value!");
                }
            }
            else {
                throw new MyException(varName + "is not of type int!");
            }
        }
        else {
            throw new MyException(varName + "is not defined in the SymTable!");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        if (typeEnvironment.isDefined(varName)) {
            TypeInterface variableType = typeEnvironment.lookup(varName);
            TypeInterface expType = exp.typecheck(typeEnvironment);
            if (!variableType.equals(new IntType())) {
                throw new MyException("The variable in " + this.toString() + " is not an integer");
            }
            if (!expType.equals(new StringType())) {
                throw new MyException("The file name in " + this.toString() + " is not a string");
            }
            return typeEnvironment;
        }
        else {
            throw new MyException("The variable " + varName + " is not defined");
        }
    }

    public String toString(){
    return "Read from " + exp + " into " + varName;
}

}