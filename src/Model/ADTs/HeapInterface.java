package Model.ADTs;

import java.util.Map;
import Model.Values.ValueInterface;
import com.sun.jdi.Value;

public interface HeapInterface {
    int allocate(ValueInterface value);
    void update(int address, ValueInterface value);
    ValueInterface get(int address);
    void deallocate(int address);
    boolean contains(int address);
    Map<Integer, ValueInterface> getContent();
    void setContent(Map<Integer, ValueInterface> content);
    public void add(Integer id, ValueInterface value) throws Exception;
}
