package Model.ADTs;
import Model.Exceptions.MyException;
import com.sun.jdi.Value;

import java.util.List;

public interface ListInterface<T> {
    void add(T item);
    void remove(T item) throws MyException;
    int size();
    T get(int index) throws MyException;
    String toString();

    List<T> getList();
}
