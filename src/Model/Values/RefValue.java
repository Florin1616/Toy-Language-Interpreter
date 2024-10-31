package Model.Values;

import Model.PrgState;
import Model.Types.RefType;
import Model.Types.TypeInterface;

import java.lang.reflect.Type;

public class RefValue implements ValueInterface{
    private TypeInterface locationType;
    private int address;

    public RefValue(TypeInterface locationType, int address){
        this.locationType = locationType;
        this.address = address;
    }

    public TypeInterface getLocationType(){
        return locationType;
    }
    public int getAddress(){
        return address;
    }

    @Override
    public TypeInterface getType() {
        return new RefType(locationType);
    }

    @Override
    public ValueInterface deepCopy() {
        return new RefValue(locationType, address);
    }

    @Override
    public String toString(){
        return "(" + address + ", " + locationType + ")";
    }
}
