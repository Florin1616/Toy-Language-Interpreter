package Model;
import Model.ADTs.*;
import Model.Exceptions.MyException;
import Model.Statements.StatementInterface;
import Model.Values.StringValue;
import Model.Values.ValueInterface;
import java.io.BufferedReader;

public class PrgState {
    private StackInterface<StatementInterface> executionStack;
    private DictionaryInterface<String, ValueInterface> symbolTable;
    private ListInterface<ValueInterface> outputList;
    private StatementInterface originalProgram;
    private DictionaryInterface<StringValue, BufferedReader> fileTable;
    private HeapInterface heap;

    private TypeTable typeTable;

    public int stateID;
    private static int freeID = 0;


    public static synchronized int getNewPrgStateID() {
        ++freeID;
        return freeID;
    }

    public PrgState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String, ValueInterface> symbolTable, ListInterface<ValueInterface> outputConsole, DictionaryInterface<StringValue, BufferedReader> fileTable, HeapInterface heapTable, StatementInterface originalProgram, TypeTable typeTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputList = outputConsole;
        this.fileTable = fileTable;
        this.heap = heapTable;
        this.originalProgram = originalProgram;
        this.typeTable = typeTable;
        stateID = getNewPrgStateID();

        this.executionStack.push(originalProgram);

    }

    public PrgState(StatementInterface originalProgram) throws MyException {
        this.executionStack = new MyStack<>();
        this.symbolTable = new MyDictionary<>();
        this.outputList = new MyList<>();
        this.originalProgram = originalProgram;
        this.fileTable = new MyDictionary<>();
        this.heap = new MyHeap();
        typeTable = new TypeTable();
        this.originalProgram.typecheck(typeTable);

        this.stateID = getNewPrgStateID();

        this.executionStack.push(originalProgram);
    }

    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if (executionStack.isEmpty())
            throw new MyException("Stack empty");
        StatementInterface currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public TypeTable getTypeTable(){
        return typeTable;
    }

    public StackInterface<StatementInterface> getStack(){
        return executionStack;
    }

    public DictionaryInterface<String, ValueInterface> getSymTable(){
        return symbolTable;
    }

    public ListInterface<ValueInterface> getOut(){
        return outputList;
    }

    public DictionaryInterface<StringValue, BufferedReader> getFileTable() {return fileTable; }

    public HeapInterface getHeap(){
        return heap;
    }

    //get output list
    public ListInterface<ValueInterface> getOutputList(){
        return outputList;
    }

    public void setExeStack(StackInterface<StatementInterface> stack){
        executionStack = stack;
    }

    public void setSymTable(DictionaryInterface<String, ValueInterface> symtbl){
        symbolTable = symtbl;
    }

    public void setOut(ListInterface<ValueInterface> outConsole){
        outputList = outConsole;
    }

    public void setFileTable(DictionaryInterface<StringValue, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }

    public void setHeap(HeapInterface gHeap){
        heap = gHeap;
    }


    public StatementInterface getOriginalProgram(){
        return originalProgram;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Thread number id: ").append(stateID).append("\n");
        str.append("Program state\n");
        str.append("File Table: ").append(fileTable).append(" \n");
        str.append("Exe Stack: ").append(executionStack).append(" \n");
        str.append("Sym Table: ").append(symbolTable).append(" \n");
        str.append("Heap: ").append(heap).append(" \n");
        str.append("Output Console: ").append(outputList).append(" \n");
        str.append("\n");
        return str.toString();
    }

}
