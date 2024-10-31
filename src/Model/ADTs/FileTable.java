package Model.ADTs;

import Model.Exceptions.MyException;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileTable implements DictionaryInterface<StringValue, BufferedReader>{
    Map<StringValue, BufferedReader> fileTable;

    public FileTable(){
        this.fileTable = new HashMap<StringValue, BufferedReader>();
    }

    @Override
    public void add(StringValue key, BufferedReader value) throws MyException {
        if (fileTable.containsKey(key))
            throw new MyException("Element already exists!");
        fileTable.put(key,value);
    }

    @Override
    public Map<StringValue, BufferedReader> getContent() {
        return null;
    }

    @Override
    public void remove(StringValue key) throws MyException {
        if(!fileTable.containsKey(key)){
            throw new MyException("The key does not exist in the dictionary!");
        }
        fileTable.remove(key);
    }

    @Override
    public void update(StringValue key, BufferedReader value) {
        fileTable.put(key,value);
    }

    @Override
    public boolean isDefined(StringValue id) {
        return fileTable.containsKey(id);
    }

    @Override
    public BufferedReader lookup(StringValue key) {
        return fileTable.get(key);
    }

    public String toString() {
        String s = "";
        for (Map.Entry<StringValue, BufferedReader> h : this.fileTable.entrySet()) {
            s += "FileName: " + h.getKey() + ", FileDescriptor:" + h.getValue().toString() + "\n";
        }
        return s;
    }

    @Override
    public Set<StringValue> keySet() {
        return null;
    }

}
