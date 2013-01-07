package unit.reflector;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;
import biny.core.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
@Identifier(44)
public class ObjectWithList {

    public final List<Long> list;

    public ObjectWithList(@Field("list") List<Long> list) {
        Assert.notNull(list);
        this.list = Collections.unmodifiableList(list);
    }

}
