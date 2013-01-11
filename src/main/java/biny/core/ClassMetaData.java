package biny.core;

import biny.core.meta.AbstractMetaData;
import biny.core.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class ClassMetaData {

    public final int identifier;
    public final Class clazz;
    public final List<AbstractMetaData> fields;

    public ClassMetaData(int identifier, Class clazz, List<AbstractMetaData> fields) {
        Assert.greaterOrEqual(identifier, 0, "");
        this.identifier = identifier;

        Assert.notNull(clazz);
        this.clazz = clazz;

        Assert.notNull(fields);
        this.fields = Collections.unmodifiableList(fields);
    }

}
