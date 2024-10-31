package Model.ADTs;
import java.util.*;
import Model.Exceptions.MyException;

public interface DictionaryInterface<K,V> {
    void add(K key, V value) throws MyException;

    //void add(StringValue key, BufferedReader value) throws MyException;
    public Map<K, V> getContent();
    public void remove(K key) throws MyException;
    public void update(K key, V value);
    boolean isDefined(K id);
    public V lookup(K key);
    String toString();

    Set<K> keySet();
}
