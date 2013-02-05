package biny.core.metadata;

import biny.core.util.Assert;

import java.lang.reflect.Field;

/**
 * Author : Igor Usenko ( igors48@gmail.com )
 * Date : 10.01.13
 */
public abstract class AbstractField {

    public final Type type;
    public final Field field;

    protected AbstractField(Type type, Field field) {
        Assert.notNull(type);
        this.type = type;

        Assert.notNull(field);
        this.field = field;
    }

    public String getName() {
        return this.field.getName();
    }

}
