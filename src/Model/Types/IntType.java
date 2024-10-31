package Model.Types;
import Model.Values.ValueInterface;
import Model.Values.IntValue;
public class IntType implements TypeInterface {
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;
    }
    @Override
    public String toString() {
        return "int ";
    }

    public ValueInterface defaultValue () {
        return new IntValue(0);

    }
}
