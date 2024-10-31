package Model.Values;

import Model.Types.StringType;
import Model.Types.TypeInterface;

public class StringValue implements ValueInterface{
    String string;
    public StringValue(){
        string = "";
    }

    public StringValue(String str){
        this.string = str;
    }

    public String getValue(){
        return string;
    }

    @Override
    public TypeInterface getType(){
        return new StringType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new StringValue(string);
    }

    @Override
    public String toString(){
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (!(obj instanceof StringValue))
            return false;
        StringValue otherStringValue = (StringValue) obj;
        return this.string.equals(otherStringValue.string);
    }
}
