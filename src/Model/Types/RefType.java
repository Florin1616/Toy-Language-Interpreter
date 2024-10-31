package Model.Types;

import Model.Values.ValueInterface;

import Model.Values.RefValue;
import java.lang.reflect.Type;
import java.sql.Ref;
import java.util.DoubleSummaryStatistics;
import java.util.Objects;

public class RefType implements TypeInterface{
    private TypeInterface inner;

    public RefType(TypeInterface inner){
        this.inner = inner;
    }

    public TypeInterface getInner(){
        return inner;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        RefType refType = (RefType) o;
        return Objects.equals(inner, refType.inner);
    }

    @Override
    public ValueInterface defaultValue() {
        return new RefValue(inner, 0);
    }

    @Override
    public String toString(){
        return "Ref(" + inner + ")";
    }
}
