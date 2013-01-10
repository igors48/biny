package unit.reflector;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;
import biny.core.util.Assert;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
@Identifier(43)
public class ObjectWithAggregate {

    public static final ObjectWithAggregate OBJECT_WITH_AGGREGATE = new ObjectWithAggregate(52L, SimpleObject.SIMPLE_OBJECT);

    public final long longValue;
    public final SimpleObject simpleObject;

    public ObjectWithAggregate(@Field("longValue") long longValue,
                               @Field("simpleObject") SimpleObject simpleObject) {
        this.longValue = longValue;

        Assert.notNull(simpleObject);
        this.simpleObject = simpleObject;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ObjectWithAggregate that = (ObjectWithAggregate) o;

        if (longValue != that.longValue) return false;

        if (!simpleObject.equals(that.simpleObject)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (longValue ^ (longValue >>> 32));
        result = 31 * result + simpleObject.hashCode();

        return result;
    }

}
