package Model.Types;

import Model.Values.StringValue;
import Model.Values.ValueInterface;

import javax.management.StringValueExp;

public class StringType implements TypeInterface{

    @Override
    public ValueInterface defaultValue() {
        return new StringValue("");
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public String toString() {
        return "string ";
    }

}
