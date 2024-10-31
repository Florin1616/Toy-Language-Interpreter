package Model.ADTs;

import Model.Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K,V> implements DictionaryInterface<K,V> {
    private Map<K,V> map;
    public MyDictionary(){
        map = new HashMap<K,V>();
    }
    @Override
    public void add(K id,V value) throws MyException {
        if (map.containsKey(id))
            throw new MyException("Element already exists!");
        map.put(id,value);
    }

    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public void remove(K key) throws MyException {
        if(!map.containsKey(key)){
            throw new MyException("The key does not exist in the dictionary!");
        }
        map.remove(key);
    }

    @Override
    public void update(K key, V value) {
        map.put(key,value);
    }

    @Override
    public boolean isDefined(K id) {
        return map.containsKey(id);
    }

    @Override
    public Object lookup(Object key) {
        return map.get(key);
    }

//    @Override
//    public String toString() {
//        String result ="{";
//        for (K key : map.keySet())
//            result+= key.toString() + " -> " + map.get(key).toString() + ";";
//        result+="}";
//        return result;
//    }
    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<K, V> h : this.map.entrySet()) {
            s += h.getKey() + "->" + h.getValue() + " | ";
        }
        return s;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

//    public SymbolTable deepCopy()
//    {
//        SymbolTable dict = new SymbolTable();
//
//        for (Map.Entry<String, IValue> pair : dictionary.entrySet())
//            dict.update(pair.getKey(), pair.getValue().deepCopy());
//
//        return dict;
//    }
    //can you write the function from above for my code?
//    public MyDictionary<K,V> deepCopy()
//    {
//        MyDictionary<K,V> dict = new MyDictionary<K,V>();
//
//        for (Map.Entry<K, V> pair : map.entrySet())
//            dict.update(pair.getKey(), pair.getValue().deepCopy());
//
//        return dict;
//    }


}
