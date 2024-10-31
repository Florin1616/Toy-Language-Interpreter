package Model.Statements;

import Model.ADTs.DictionaryInterface;
import Model.Exceptions.MyException;
import Model.Expressions.ExpressionInterface;
import Model.PrgState;
import Model.Types.StringType;
import Model.Types.TypeInterface;
import Model.Values.StringValue;
import Model.Values.ValueInterface;
import Repository.Repository;

import javax.annotation.processing.Filer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.Reader;

public class OpenRFileStmt implements StatementInterface{
    private ExpressionInterface exp;

    public OpenRFileStmt(ExpressionInterface exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symTable = state.getSymTable();
        ValueInterface val = exp.eval(symTable, state.getHeap());
        if(val.getType().equals(new StringType())){
            DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();
            StringValue strVal = (StringValue)val;
            if(!fileTable.isDefined(strVal)){
                try {

                    Reader reader = new FileReader(strVal.getValue());
                    BufferedReader buff = new BufferedReader(reader);
                    fileTable.update(strVal, buff);
                }
                catch (Exception e){
                    throw new MyException("File does not exist!");
                }
            }
            else
                throw new MyException("File already opened!");
        }
        else{
            throw new MyException("Expression is not a string!");
        }
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typecheck(DictionaryInterface<String, TypeInterface> typeEnvironment) throws MyException {
        TypeInterface expType = exp.typecheck(typeEnvironment);
        if (expType.equals(new StringType())) {
            return typeEnvironment;
        }
        else {
            throw new MyException("The close file expression " + this.toString() + " is not a string");
        }
    }

    @Override
    public String toString(){
        return "Open(" + exp.toString() + ")";
    }
}
