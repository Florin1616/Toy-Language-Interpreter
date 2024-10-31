package Model.ADTs;
import Model.Exceptions.MyException;
import Model.Values.ValueInterface;

import java.util.*;
public class MyList<T> implements ListInterface<T> {
    private final List<T> list;
    public MyList(){
        list = new ArrayList<>();
    }
    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public void remove(T item) throws MyException{
        try{
            list.remove(item);}
        catch (Exception e){
            throw new MyException("No such element: " + item);
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T get(int index) throws MyException {
        try {
            return list.get(index);
        }
        catch (IndexOutOfBoundsException e){
            throw new MyException("Index out of bounds!");
        }
    }

    @Override
    public String toString(){
        String result = "{";

        for (T el:this.list) {
            result+= el.toString() + " ";
        }
        result+="}";
        return result.toString();
    }

    @Override
    public List<T> getList() {
        return list;
    }

}

