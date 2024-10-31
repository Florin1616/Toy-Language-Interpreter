package Model.ADTs;
import java.util.ArrayList;
import java.util.Deque;
import java.util.NavigableMap;

import Model.Exceptions.MyException;
import Model.Statements.StatementInterface;

//public interface StackInterface<T> {
//    T pop() throws MyException;
//    void push(T value);
//    boolean isEmpty();
//    String toString();
//
////    StatementInterface[] getStack();
//}


//package Model.ADTs;
public interface StackInterface<T> extends Iterable<T>{
    T pop() throws MyException;
    T peek() throws MyException;
    void push(T v);
    boolean isEmpty();
    Deque<T> getStack();
}