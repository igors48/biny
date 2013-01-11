package biny.core.meta;

import biny.core.util.Assert;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public abstract class AbstractMetaData {

    public final Field field;

    protected AbstractMetaData(Field field) {
        Assert.notNull(field);
        this.field = field;
    }

}
