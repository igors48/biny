package biny.core;

import biny.core.util.Assert;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 05.01.13
 */
public class ClassMetaData {

    public final int identifier;
    public final List<Field> fields;

    public ClassMetaData(int identifier, List<Field> fields) {
        Assert.greaterOrEqual(identifier, 0, "");
        this.identifier = identifier;

        Assert.notNull(fields);
        this.fields = Collections.unmodifiableList(fields);
    }

}
