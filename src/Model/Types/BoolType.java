package Model.Types;

import Model.Values.BoolValue;
import Model.Values.ValueInterface;
public class BoolType implements TypeInterface {
    @Override
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool ";
    }

    public ValueInterface defaultValue() {
        return new BoolValue(false);
    }
}
