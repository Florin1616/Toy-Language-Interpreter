package Model.ADTs;

import Model.Exceptions.MyException;
import Model.Types.TypeInterface;
import java.util.Map;

import java.util.HashMap;
import java.util.Set;

public class TypeTable implements DictionaryInterface<String, TypeInterface> {
    Map<String, TypeInterface> dictionary = new HashMap<>();

    @Override
    public boolean isDefined(String id) {
        return dictionary.containsKey(id);
    }

    @Override
    public void add(String key, TypeInterface value) throws MyException {

    }

    @Override
    public Map<String, TypeInterface> getContent() {
        return dictionary;
    }

    @Override
    public TypeInterface lookup(String id) {
        return dictionary.get(id);
    }

    @Override
    public void update(String id, TypeInterface val) {
        dictionary.put(id, val);
    }

    public Iterable<Map.Entry<String, TypeInterface>> getIterableSet() {
        return dictionary.entrySet();
    }

    @Override
    public void remove(String id) {
        dictionary.remove(id);
    }


    @Override
    public String toString() {
        String string = "";
        for (Map.Entry<String, TypeInterface> s : dictionary.entrySet()) {
            string += (s.getKey()) + "\n";
        }
        return string;
    }

    @Override
    public Set<String> keySet() {
        return null;
    }
}
