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

    public final long longValue;
    public final SimpleObject simpleObject;

    public ObjectWithAggregate(@Field("longValue") long longValue,
                               @Field("simpleObject") SimpleObject simpleObject) {
        this.longValue = longValue;

        Assert.notNull(simpleObject);
        this.simpleObject = simpleObject;
    }

}
