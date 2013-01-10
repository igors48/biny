package unit.reflector;

import biny.core.annotation.Field;
import biny.core.annotation.Identifier;
import biny.core.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 07.01.13
 */
@Identifier(44)
public class ObjectWithList {

    public static final ObjectWithList OBJECT_WITH_LIST;

    static {
        List<Long> list = new ArrayList<Long>();

        list.add(1L);
        list.add(2L);
        list.add(3L);

        OBJECT_WITH_LIST = new ObjectWithList(list);
    }

    public final List<Long> list;

    public ObjectWithList(@Field("list") List<Long> list) {
        Assert.notNull(list);
        this.list = Collections.unmodifiableList(list);
    }

}
