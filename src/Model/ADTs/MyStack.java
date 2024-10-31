package Model.ADTs;
import Model.Exceptions.MyException;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Stack;
import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T>{
//    private Stack<T> stack;

//    public MyStack(){
//        this.stack = new Stack<T>();
//    }
//    @Override
//    public T pop() throws MyException {
//        if(stack.size() == 0)
//            throw new MyException("The stack is empty!");
//        return stack.pop();
//    }
//    @Override
//    public void push(T value) {
//        stack.push(value);
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return stack.isEmpty();
//    }
//
//
//    @Override
//    public String toString() {
//        String result = "";
//        for (T el:this.stack) {
//            result += el.toString();
//        }
//        result+=" ";
//        return result.toString();
//    }
//
//    public Deque<T> getStack() {
//        return deque;
//    }
private final Deque<T> deque;

    public MyStack(){
        deque = new LinkedList<>();
    }

    @Override
    public T pop() throws MyException {
        if (deque.isEmpty())
            throw new MyException("The stack is empty!");
        return deque.pop();
    }

    public T peek() throws MyException {
        if (deque.isEmpty())
            throw new MyException("STACK ERROR: Stack is empty");
        return deque.peek();
    }

    @Override
    public void push(T v) {
        deque.push(v);
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public Iterator<T> iterator() {
        return deque.iterator();
    }

    public Deque<T> getStack() {
        return deque;
    }

}
