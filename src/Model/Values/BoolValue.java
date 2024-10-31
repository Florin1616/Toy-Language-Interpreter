package Model.Values;

import Model.Types.TypeInterface;
import Model.Types.BoolType;
public class BoolValue implements ValueInterface{
    boolean value;
    public BoolValue(boolean value){
        this.value = value;
    }
    public BoolValue(){
        this.value = false;
    }
    public boolean getValue(){
        return value;
    }
    @Override
    public String toString(){
        return String.valueOf(this.value);
    }
    @Override
    public TypeInterface getType(){
        return new BoolType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new BoolValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(!(obj instanceof BoolValue))
            return false;
        BoolValue v = (BoolValue) obj;
        return v.value == value;
    }
}
