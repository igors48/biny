package unit.biny;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;

import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 02.02.13
 */
@Identifier(100)
public class ComplexObject {

    public final long longValue;
    public final String stringValue;
    public final FirstAggregate firstAggregate;
    public final List<Long> listOfValues;
    public final List<SecondAggregate> listOfAggregates;

    public ComplexObject(
            @Field("longValue") long longValue,
            @Field("stringValue") String stringValue,
            @Field("firstAggregate") FirstAggregate firstAggregate,
            @Field("listOfValues") List<Long> listOfValues,
            @Field("listOfAggregates") List<SecondAggregate> listOfAggregates) {
        this.longValue = longValue;
        this.stringValue = stringValue;
        this.firstAggregate = firstAggregate;
        this.listOfValues = listOfValues;
        this.listOfAggregates = listOfAggregates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexObject that = (ComplexObject) o;

        if (longValue != that.longValue) return false;
        if (!firstAggregate.equals(that.firstAggregate)) return false;
        if (!listOfAggregates.equals(that.listOfAggregates)) return false;
        if (!listOfValues.equals(that.listOfValues)) return false;
        if (!stringValue.equals(that.stringValue)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (longValue ^ (longValue >>> 32));
        result = 31 * result + stringValue.hashCode();
        result = 31 * result + firstAggregate.hashCode();
        result = 31 * result + listOfValues.hashCode();
        result = 31 * result + listOfAggregates.hashCode();
        return result;
    }
}
