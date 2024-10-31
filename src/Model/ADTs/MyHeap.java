package Model.ADTs;

import Model.Exceptions.MyException;
import Model.Values.ValueInterface;
import com.sun.jdi.Value;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements HeapInterface{
    private Map<Integer, ValueInterface> heap;
    private Integer nextFreeLocation;

    public MyHeap(){
        this.heap = new HashMap<>();
        this.nextFreeLocation = 1;
    }
    @Override
    public int allocate(ValueInterface value) {
        this.heap.put(this.nextFreeLocation, value);
        return this.nextFreeLocation++;
    }

    @Override
    public void update(int address, ValueInterface value) {
        this.heap.put(address, value);
    }

    @Override
    public ValueInterface get(int address) {
        return this.heap.get(address);
    }

    @Override
    public void deallocate(int address) {
        heap.remove(address);
    }

    @Override
    public boolean contains(int address) {
        return heap.containsKey(address);
    }

    @Override
    public Map<Integer, ValueInterface> getContent() {
        return heap;
    }

    @Override
    public void setContent(Map<Integer, ValueInterface> content) {
        heap = content;
    }

    @Override
    public void add(Integer id, ValueInterface value) throws Exception {
        if(heap.containsKey(id)){
            throw new MyException("Element already exists!");
        }
        heap.put(id, value);
    }

    public String toString(){
        if (this.heap.isEmpty())
            return "Empty heap";

        String string = "";

        for (Map.Entry<Integer, ValueInterface> pair : this.heap.entrySet())
            string += pair.getKey() + "->" + pair.getValue().toString() + " | ";

        return string;
    }
}
