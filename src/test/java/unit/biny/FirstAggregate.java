package unit.biny;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 03.02.13
 */
@Identifier(101)
public class FirstAggregate {

    public final long longValue;
    public final SecondAggregate secondAggregate;

    public FirstAggregate(
            @Field("longValue") long longValue,
            @Field("secondAggregate") SecondAggregate secondAggregate) {
        this.longValue = longValue;
        this.secondAggregate = secondAggregate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirstAggregate that = (FirstAggregate) o;

        if (longValue != that.longValue) return false;
        if (!secondAggregate.equals(that.secondAggregate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (longValue ^ (longValue >>> 32));
        result = 31 * result + secondAggregate.hashCode();
        return result;
    }

}
