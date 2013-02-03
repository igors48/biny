package unit.biny;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 03.02.13
 */
@Identifier(103)
public class SecondAggregate {

    public final String stringValue;

    public SecondAggregate(
            @Field("stringValue") String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecondAggregate that = (SecondAggregate) o;

        if (!stringValue.equals(that.stringValue)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stringValue.hashCode();
    }
}
