package Model.Values;
import Model.Types.IntType;
import Model.Types.TypeInterface;


public class IntValue implements ValueInterface{
    int value;
    public IntValue(int value){
        this.value = value;
    }
    public IntValue(){
        this.value = 0;
    }
    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }
    @Override
    public TypeInterface getType() {
        return new IntType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new IntValue(value);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof IntValue i))
            return false;
        return i.value ==  value;
    }
}
